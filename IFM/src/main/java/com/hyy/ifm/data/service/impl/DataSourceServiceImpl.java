package com.hyy.ifm.data.service.impl;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.config.dao.ConfigDictDao;
import com.hyy.ifm.config.pojo.DcDict;
import com.hyy.ifm.customer.dao.CustomerDao;
import com.hyy.ifm.data.dao.DataSourceDao;
import com.hyy.ifm.data.pojo.Cnds;
import com.hyy.ifm.data.service.DataSourceService;
import com.hyy.ifm.sys.dao.UserDao;
import com.hyy.ifm.sys.pojo.IfmLogin;
import com.hyy.ifm.sys.pojo.IfmSysLoginLog;
import com.hyy.ifm.sys.pojo.IfmUser;
import com.hyy.ifm.sys.service.impl.SqlUtils;
import com.hyy.ifm.util.DateUtil;
import com.hyy.ifm.util.StringUtil;
import com.hyy.ifm.util.ZfSignUtil;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author 毛椅俊
 * @Date 2018-03-27 11:50
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "dataSourceService")
public class DataSourceServiceImpl extends BaseService implements DataSourceService {

    @Inject
    private DataSourceDao dataSourceDao;


    @Override
    public CallBackBean qryDataSourceList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        List<Record> res = null;
        if (cnds.getRows().getSource_cnd() !=null && !cnds.getRows().getSource_cnd().equals("")){
            res = dataSourceDao.qryDataRoorList(cnds , "AND dvl.source = '" + cnds.getRows().getSource_cnd() + "'"," and du.create_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()) +"' "+" and du.create_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()) +"' ");
        }else {
            res = dataSourceDao.qryDataRoorList(cnds," and du.create_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()) +"' "+" and du.create_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()) +"' ");
        }
        int count  =  dataSourceDao.qrySourceAll().size();
        return this.joinformateJson(json, "success", count, res);
    }

    @Inject
    private UserDao userDao;

    @Inject
    private CustomerDao customerDao;

    @Inject
    private ConfigDictDao configDictDao;

    @Override
    public CallBackBean qryQuDaoDataSourceList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd5(cnds);
        List<Map<String, Object>> qDaoCount = dropList(cnds);
        //分页
        List<Map<String,Object>> pageList= new ArrayList<>();
        int size=qDaoCount.size();
        int pageStart=cnds.getPageNum()==1?0:(cnds.getPageNum()-1)*10;
        int pageEnd=size<cnds.getPageNum()*10?size:cnds.getPageNum()*10;
        if(size>pageStart){
            pageList =qDaoCount.subList(pageStart, pageEnd);
        }
        return this.joinformateJson(json, "success", qDaoCount.size(), pageList);
    }

    @Override
    public CallBackBean qryQuDaoDataSourceSum(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd5(cnds);
        List<Map<String, Object>> qDaoCountList = dropList(cnds);
        IfmLogin ifmLogin = userDao.fetchLogin(cnds.getUserCode());
        int countCount = 0;
        int countUvCount = 0;
        int uvCount = 0;
        int noneCount = 0;
        int faceCount = 0;
        int perCount = 0;
        int bankCount = 0;
        int concatCount = 0;
        int yunCount = 0;
        for (Map<String, Object> map : qDaoCountList) {
            countCount = Integer.parseInt(map.get("count").toString()) + countCount;
            uvCount = Integer.parseInt(map.get("uv").toString()) + uvCount;
            countUvCount = Integer.parseInt(map.get("count_uv").toString()) + countUvCount;
            noneCount = Integer.parseInt(map.get("none").toString()) + noneCount;
            faceCount = Integer.parseInt(map.get("face").toString()) + faceCount;
            perCount = Integer.parseInt(map.get("per").toString()) + perCount;
            bankCount = Integer.parseInt(map.get("bank").toString()) + bankCount;
            concatCount = Integer.parseInt(map.get("concat").toString()) + concatCount;
            yunCount = Integer.parseInt(map.get("yun").toString()) + yunCount;
        }

        Map<String,Object> mapCount = new HashMap<>();
        List<Map<String,Object>> qDaoCount = new ArrayList<>();
        mapCount.put("count",countCount);
        mapCount.put("uv",uvCount);
        mapCount.put("count_uv",countUvCount);
        mapCount.put("none",noneCount);
        mapCount.put("face",faceCount);
        mapCount.put("per",perCount);
        mapCount.put("bank",bankCount);
        mapCount.put("concat",concatCount);
        mapCount.put("yun",yunCount);
        IfmUser ifmUser = userDao.fetchUserByLngid(cnds.getUserCode());
        if(null == ifmUser.getLoanRate() || "".equals(ifmUser.getLoanRate())){
            ifmUser.setLoanRate("0.05");
        }
        List<Record> qDaoloanList = loanList(cnds);
        if(0 == yunCount){
            mapCount.put("pay",0);
        }else if(ZfSignUtil.div(Double.parseDouble(qDaoloanList.get(0).get("count").toString()),countCount) > Double.parseDouble(ifmUser.getLoanRate())){//放款/注册
            mapCount.put("pay",(int)ZfSignUtil.mul(yunCount, Double.parseDouble(ifmUser.getLoanRate())));
        }else{
            mapCount.put("pay",qDaoloanList.get(0).get("count"));
        }
        qDaoCount.add(mapCount);
        int countss = dataSourceDao.countDataSourceList(cnds);
        return this.joinformateJson(json, "success", countss, qDaoCount);
    }


    @Override
    public CallBackBean qryQuDaoSl(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd7(cnds);
        List<Record> qdaoSls = dataSourceDao.qryQuDaoSlAllSource(cnds, cnds.getUserCode());
        int countss = dataSourceDao.counQuDaoSlAllSource(cnds);
        return this.joinformateJson(json, "success", countss, qdaoSls);
    }


    @Override
    public CallBackBean qryDataSourceSum(String json) {
        int noneCount = 0;
        int faceCount = 0;
        int perCount = 0;
        int bankCount = 0;
        int concatCount = 0;
        int yunCount = 0;
        int uvCount = 0;
        int countCount = 0;
        int countUvCount = 0;
        Double money = 0.00;
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd5(cnds);

        List<Record> records = dataSourceDao.qryRegisterAndUvCount(cnds);
        List<Map<String,Object>> resCount = new ArrayList<>();
        Map<String,Object> mapCount = new HashMap<>();
        for (Record record : records) {
            countCount =  record.get("countcount")==null? 0 : Integer.parseInt(record.get("countcount").toString());
            countUvCount = record.get("countuvcount")==null? 0 :  Integer.parseInt(record.get("countuvcount").toString());
            uvCount =  record.get("countuvcount")==null? 0 : Integer.parseInt(record.get("uvcount").toString());
        }
        int count = 0;
        com.hyy.ifm.customer.pojo.Cnds cnds1 = Json.fromJson(com.hyy.ifm.customer.pojo.Cnds.class, json);
        cnds1 = SqlUtils.apCnd1(cnds1);
        List<Record> res = customerDao.qryCustomerMoneyAll(cnds1);
        for (Record re : res) {
            money =  Double.parseDouble(re.get("pay_amt").toString());
        }
        for (int i = 0; i < 6; i++) {
            cnds1.getRows().setUser_auth_cnd(String.valueOf(i));
            cnds1 = SqlUtils.apCnd1(cnds1);
            if (cnds.getRows() !=null){
                if (cnds1.getRows().getUser_auth_cnd()!=null && cnds1.getRows().getUser_auth_cnd().equals("0")){
                    noneCount = customerDao.countCustomerListNone(cnds1);
                }else {
                    if (i == 1){
                        faceCount = customerDao.countCustomerList(cnds1);
                    }else if(i == 2){
                        perCount = customerDao.countCustomerList(cnds1);
                    }else if(i == 3){
                        bankCount = customerDao.countCustomerList(cnds1);
                    } else if(i == 4){
                        concatCount = customerDao.countCustomerList(cnds1);
                    }else if(i == 5){
                        yunCount = customerDao.countCustomerList(cnds1);
                    }
                }
            }
        }

        mapCount.put("countCount",countCount);
        mapCount.put("countUvCount",countUvCount);
        mapCount.put("noneCount",noneCount);
        mapCount.put("faceCount",faceCount);
        mapCount.put("perCount",perCount);
        mapCount.put("bankCount",bankCount);
        mapCount.put("concatCount",concatCount);
        mapCount.put("yunCount",yunCount);
        mapCount.put("uvCount",uvCount);
        mapCount.put("pay",money);
        resCount.add(mapCount);
        // int count = dataSourceDao.countDataSourceList(cnds);
        return this.joinformateJson(json, "success", 1, resCount);
    }

    @Override

    public CallBackBean exportDataSourceList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd5(cnds);
        List<Record> res = dataSourceDao.exportDataSourceList(cnds);
        return this.joinformateJson(json, "success", res);
    }

    @Override
    public CallBackBean qrySourceAll(String json) {
        List<Record> records =  dataSourceDao.qrySourceAll();
        return this.joinformateJson(json, "success", records);
    }

    @Override
    public CallBackBean qryMySourceAll(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        List<Record> records =  dataSourceDao.qryMySource(cnds.getUserCode());
        return this.joinformateJson(json, "success", records);
    }

    @Override
    public CallBackBean qryDataEchartsSourceBySource(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            String source = StringUtil.nvl(cnds.getRows().getSource());
            String daysFrom = StringUtil.nvl(cnds.getRows().getDaysFrom());
            String daysTo = StringUtil.nvl(cnds.getRows().getDaysTo());
            if(StringUtil.isNotBlank(daysFrom) && StringUtil.isNotBlank(daysTo)) {
                if(DateUtil.StringToDate(daysTo + ":00:00").getTime() < DateUtil.StringToDate(daysFrom + ":00:00").getTime()) {
                    return this.joinformateJson(json, "起始时间不能小于终止时间", "");
                }
            }

            cnds = SqlUtils.apCnd3(cnds);
            List<Record> records = dataSourceDao.qryDataEchartsSourceBySource(cnds, "AND dvl.source = '" + source +"'");
            for(Record record : records) {
                record.set("source", source);
            }
            return this.joinformateJson(json, "success", records);
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "查询失败", "");
        }
    }

    @Override
    public CallBackBean exportDataSourceCharts(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            String source = StringUtil.nvl(cnds.getRows().getSource());
            String daysFrom = StringUtil.nvl(cnds.getRows().getDaysFrom());
            String daysTo = StringUtil.nvl(cnds.getRows().getDaysTo());
            if(StringUtil.isNotBlank(daysFrom) && StringUtil.isNotBlank(daysTo)) {
                if(DateUtil.StringToDate(daysTo + ":00:00").getTime() < DateUtil.StringToDate(daysFrom + ":00:00").getTime()) {
                    return this.joinformateJson(json, "起始时间不能小于终止时间", "");
                }
            }
            String[] sourceArray = null;
            if(StringUtil.isNotBlank(source)) {
                sourceArray = source.split(",");
            } else {
                List<Record> records = dataSourceDao.qrySourceAll();
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < records.size(); i++) {
                    temp.append(records.get(i).getString("source"));
                    if (i != records.size() - 1) {
                        temp.append(",");
                    }
                }
                sourceArray = temp.toString().split(",");
            }

            cnds = SqlUtils.apCnd3(cnds);
            List<Record> res = new ArrayList<Record>();
            int count = 0;
            for (String source1 : sourceArray) {
                count++;
                List<Record> records1 = dataSourceDao.qryDataEchartsSourceBySource(cnds, "AND dvl.source = '" + source1 + "'");
                //先将日期存入 只存入一次
                for (int i = 0; i < records1.size(); i++) {
                    if (count == 1) {
                        Record record1 = new Record();
                        record1.set("click_date", records1.get(i).getString("click_date"));
                        res.add(record1);
                    }
                }

                //数据与日期符合 存入数据
                for (Record record : res) {
                    for (Record t : records1) {
                        if (record.getString("click_date").equals(t.getString("click_date"))) {
                            record.set("source_" + source1, t.getString("count"));
                        }
                    }
                }
            }
            return this.joinformateJson(json, "success", res);
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "导出失败", "");
        }
    }
    public  List<Map<String,Object>> dropList(Cnds cnds){
        IfmLogin ifmLogin = userDao.fetchLogin(cnds.getUserCode());
        List<Record> qdaoSls = null;
        String quantity = "";
        qdaoSls = dataSourceDao.qryQuDaoSl(cnds, ifmLogin.getUserCode());
        List<Map<String, Object>> dateQdaoList = DateUtil.getDateQdaoList(qdaoSls,cnds);
        Collections.reverse(dateQdaoList);
        List<DcDict> payOrderList = configDictDao.qryDictByDataType("QUANTITY");
        for (DcDict dcDict : payOrderList) {
            quantity = dcDict.getItemValue();
        }
        int countCount = 0;
        int countUvCount = 0;
        int uvCount = 0;
        int noneCount = 0;
        int faceCount = 0;
        int perCount = 0;
        int bankCount = 0;
        int concatCount = 0;
        int yunCount = 0;


        int countCount2 = 0;
        int countUvCount2 = 0;
        int uvCount2 = 0;
        int noneCount2 = 0;
        int faceCount2 = 0;
        int perCount2 = 0;
        int bankCount2 = 0;
        int concatCount2 = 0;
        int yunCount2 = 0;
        List<Map<String,Object>> qDaoCount = new ArrayList<>();
        List<Record> dataSumAlls= dataSourceDao.qryDataSumList(cnds,ifmLogin.getUserCode(),"2000-00-00 00:00:00","2200-00-00 00:00:00");
        int uvCountAll = 0;
        List<Record> dataSumAll = new ArrayList<>();
        Collections.reverse(dataSumAlls);
        for (Record record : dataSumAlls) {
            Record  re = Record.create();
            uvCountAll =  uvCountAll + Integer.parseInt(record.get("uv").toString());
            re.put("visit_time",record.get("visit_time"));
            if (uvCountAll > Integer.parseInt(quantity) ){
                re.put("uv",uvCountAll-Integer.parseInt(quantity));
                re.put("vi",new BigDecimal((float)(uvCountAll-Integer.parseInt(quantity))/Integer.parseInt(record.get("uv").toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                dataSumAll.add(re);
                break;
            }else {
                re.put("uv",record.get("uv"));
                re.put("vi","1");
                dataSumAll.add(re);
            }
        }
        for (Map<String, Object> map : dateQdaoList) {
            List<Record> dataSum = null;
            dataSum = dataSourceDao.qryDataSumList(cnds,ifmLogin.getUserCode(),map.get("startTime").toString(),map.get("endTime").toString());
            Collections.reverse(dataSum);
            for (Record record : dataSum) {

                if("2000-00-00 00:00:00".equals(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd())) && "2200-00-00 00:00:00".equals(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd())) ) {
                    if (Integer.parseInt(record.get("uv").toString())<Integer.parseInt(quantity)&&uvCount2<Integer.parseInt(quantity)
                            &&Integer.parseInt(record.get("uv").toString())+uvCount2<Integer.parseInt(quantity)){
                        countCount =  Integer.parseInt(record.get("count").toString());
                        countUvCount =  Integer.parseInt(record.get("count_uv").toString());
                        uvCount =  Integer.parseInt(record.get("uv").toString());
                        noneCount =  Integer.parseInt(record.get("none").toString());
                        faceCount =  Integer.parseInt(record.get("face").toString());
                        perCount =  Integer.parseInt(record.get("per").toString());
                        bankCount =  Integer.parseInt(record.get("bank").toString());
                        concatCount =  Integer.parseInt(record.get("concat").toString());
                        yunCount =  Integer.parseInt(record.get("yun").toString());
                    }else if (uvCount2>Integer.parseInt(quantity)){
                        countCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count").toString()),Double.parseDouble(map.get("sl").toString())));
                        countUvCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count_uv").toString()),Double.parseDouble( map.get("sl").toString())));
                        uvCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("uv").toString()),Double.parseDouble( map.get("sl").toString())));
                        noneCount =getInt(ZfSignUtil.mul(Double.parseDouble(record.get("none").toString()),Double.parseDouble( map.get("sl").toString())));
                        faceCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("face").toString()),Double.parseDouble( map.get("sl").toString())));
                        perCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("per").toString()),Double.parseDouble( map.get("sl").toString())));
                        bankCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("bank").toString()),Double.parseDouble( map.get("sl").toString())));
                        concatCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("concat").toString()),Double.parseDouble( map.get("sl").toString())));
                        yunCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("yun").toString()),Double.parseDouble( map.get("sl").toString())));
                    }else if(uvCount2<Integer.parseInt(quantity) &&Integer.parseInt(record.get("uv").toString())+uvCount2>Integer.parseInt(quantity)){
                        int sum = Integer.parseInt(record.get("uv").toString()) + uvCount2;
                        int i = sum - Integer.parseInt(quantity);//需要扣量
                        int i1 = Integer.parseInt(record.get("uv").toString()) - i; // 不需要扣量
                        uvCount =  i1+ getInt(ZfSignUtil.mul(i,Double.parseDouble( map.get("sl").toString())));

                        double iv =  new BigDecimal((float)uvCount/Integer.parseInt(record.get("uv").toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        countCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count").toString()),iv));
                        countUvCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count_uv").toString()),iv));
                        noneCount =getInt(ZfSignUtil.mul(Double.parseDouble(record.get("none").toString()),iv));
                        faceCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("face").toString()),iv));
                        perCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("per").toString()),iv));
                        bankCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("bank").toString()),iv));
                        concatCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("concat").toString()),iv));
                        yunCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("yun").toString()),iv));
                    }
                    countCount2 = countCount+countCount2;
                    countUvCount2 = countUvCount+countUvCount2;
                    uvCount2 = uvCount+uvCount2;
                    noneCount2 = noneCount+noneCount2;
                    faceCount2 = faceCount+faceCount2;
                    perCount2 = perCount+perCount2;
                    bankCount2 = bankCount+bankCount2;
                    concatCount2 = concatCount+concatCount2;
                    yunCount2 = yunCount+yunCount2;


                }else {
                    for (Record record1 : dataSumAll) {
                        if (record1.get("visit_time").equals(record.get("visit_time"))){
                            uvCount =  Integer.parseInt(record.get("uv").toString())-Integer.parseInt(record1.get("uv").toString())+ getInt(ZfSignUtil.mul(Double.parseDouble(record1.get("uv").toString()),Double.parseDouble(record1.get("vi").toString())));
                            double vi = new BigDecimal((float) (uvCount / Integer.parseInt(record1.get("uv").toString()))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            countCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count").toString()),vi));
                            countUvCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count_uv").toString()),vi));
                            noneCount =getInt(ZfSignUtil.mul(Double.parseDouble(record.get("none").toString()),vi));
                            faceCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("face").toString()),vi));
                            perCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("per").toString()),vi));
                            bankCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("bank").toString()),vi));
                            concatCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("concat").toString()),vi));
                            yunCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("yun").toString()),vi));
                        }else {
                            countCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count").toString()),Double.parseDouble(map.get("sl").toString())));
                            countUvCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count_uv").toString()),Double.parseDouble( map.get("sl").toString())));
                            uvCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("uv").toString()),Double.parseDouble( map.get("sl").toString())));
                            noneCount =getInt(ZfSignUtil.mul(Double.parseDouble(record.get("none").toString()),Double.parseDouble( map.get("sl").toString())));
                            faceCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("face").toString()),Double.parseDouble( map.get("sl").toString())));
                            perCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("per").toString()),Double.parseDouble( map.get("sl").toString())));
                            bankCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("bank").toString()),Double.parseDouble( map.get("sl").toString())));
                            concatCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("concat").toString()),Double.parseDouble( map.get("sl").toString())));
                            yunCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("yun").toString()),Double.parseDouble( map.get("sl").toString())));
                        }
                    }

                }

                Map<String,Object> mapCount = new HashMap<>();
                mapCount.put("count",countCount);
                mapCount.put("source",record.get("source"));
                mapCount.put("visit_time",String.valueOf(record.get("visit_time")));
                mapCount.put("uv",uvCount);
                mapCount.put("count_uv",countUvCount);
                mapCount.put("none",noneCount);
                mapCount.put("face",faceCount);
                mapCount.put("per",perCount);
                mapCount.put("bank",bankCount);
                mapCount.put("concat",concatCount);
                mapCount.put("yun",yunCount);
                qDaoCount.add(mapCount);
            }
        }
        return  qDaoCount ;
    }

    public  List<Record> loanList(Cnds cnds){
        String s = " and du.create_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()) +"'";
        String h = " and du.create_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()) +"' ";
        IfmLogin ifmLogin = userDao.fetchLogin(cnds.getUserCode());
        List<Record> qdaoSls = dataSourceDao.qryQuDaoLoan(cnds,ifmLogin.getUserCode(),s,h);
        return  qdaoSls ;
    }
    public static int getInt(double number){
        BigDecimal bd=new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
        return Integer.parseInt(bd.toString());
    }

}
