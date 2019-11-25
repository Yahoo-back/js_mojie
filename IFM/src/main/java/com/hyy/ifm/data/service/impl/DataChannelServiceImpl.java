package com.hyy.ifm.data.service.impl;

import com.alibaba.druid.sql.visitor.functions.If;
import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.config.dao.ConfigDictDao;
import com.hyy.ifm.config.pojo.DcDict;
import com.hyy.ifm.data.dao.DataClannelDao;
import com.hyy.ifm.data.dao.DataProductDao;
import com.hyy.ifm.data.dao.DataSourceDao;
import com.hyy.ifm.data.pojo.Cnds;
import com.hyy.ifm.data.service.DataChannelService;
import com.hyy.ifm.sys.dao.UserDao;
import com.hyy.ifm.sys.pojo.IfmLogin;
import com.hyy.ifm.sys.service.impl.SqlUtils;
import com.hyy.ifm.util.DateUtil;
import com.hyy.ifm.util.StringUtil;
import com.hyy.ifm.util.ZfSignUtil;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import static com.hyy.ifm.common.dao.BaseDao.getPageNum;
import static com.hyy.ifm.data.service.impl.DataSourceServiceImpl.getInt;
import static javax.swing.UIManager.getInt;

/**
 * @Author 毛椅俊
 * @Date 2018-03-27 11:50
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "dataChannelService")
public class DataChannelServiceImpl extends BaseService implements DataChannelService {
    @Inject
    DataSourceDao  dataSourceDao;

    @Inject
    DataChannelService  dataChannelService;
    @Inject
    ConfigDictDao configDictDao;
    @Inject
    DataClannelDao dataClannelDao;

    @Inject
    UserDao userDao;

    @Override
    public CallBackBean qryDataFinanceReportList(String json) {

        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.clCnd1(cnds);
        List<Record> sourceAll = new ArrayList<>();
        Record record = Record.create();
        if (!"".equals(cnds.getRows().getSource_cnd())&& null!=cnds.getRows().getSource_cnd()){
            record.put("source",cnds.getRows().getSource_cnd());
            sourceAll.add(record);
        }else {
            sourceAll =  dataSourceDao.qrySourceAll();
        }

        List<Map<String,Object>> list = new ArrayList<>();

        for (Record source : sourceAll) {
            cnds.getRows().setSource_cnd(source.get("source").toString());
            Map<String,Object> map = new HashMap<>();
            map.put("source",source.get("source")); //渠道来源
            String withholdPay = findWithholdPayBySource(cnds);
            map.put("withholdPay",withholdPay); // 已扣款金额
            double clickPay = 0.00;
            List<Record> clickList = findClickCpaPayBySource(cnds);
            for (Record click : clickList) {
                clickPay = clickPay +  Double.parseDouble(click.get("count").toString()) * Double.parseDouble(click.get("cpa").toString());
            }
            map.put("clickPay",clickPay);  //点击cpa金额
            String count =  findUserBySource(cnds); //渠道应扣款注册数
            int oughtPay = Integer.parseInt(count) * 299; //应扣款金额
            String payPercent=new DecimalFormat("0.00").format(Double.parseDouble(withholdPay)/Double.parseDouble(String.valueOf(oughtPay)));//设置保留位数
            map.put("payPercent", oughtPay == 0? 0.00 :payPercent);  //扣款转化率
            Map<String, Object> mapCount = dropList(cnds,source.get("source").toString());
            Record payCpa = findPayCpaPayBySource(cnds);
            double uvcpaPay = 0;
            double cpaPay = 0;
            if (payCpa.get("cpaType").toString().equals("0")){
                uvcpaPay = Double.parseDouble(mapCount.get("uv").toString()) * Double.parseDouble(payCpa.get("cpa").toString());
                String cpaPercent=new DecimalFormat("0.00").format(uvcpaPay/Double.parseDouble(withholdPay));//设置保留位数
                map.put("cpaPercent",Double.parseDouble(withholdPay) == 0 ?0.00:cpaPercent);  //cpa转化率
            }
            if (payCpa.get("cpaType").toString().equals("1")){
                cpaPay = Double.parseDouble(mapCount.get("count").toString()) * Double.parseDouble(payCpa.get("cpa").toString());
                String cpaPercent=new DecimalFormat("0.00").format(cpaPay/Double.parseDouble(withholdPay));//设置保留位数
                map.put("cpaPercent",Double.parseDouble(withholdPay) == 0 ?0.00:cpaPercent);  //cpa转化率
            }
            map.put("uvPay",uvcpaPay);  //支付cpa金额
            map.put("cpaPay",cpaPay);  //支付cpa金额
            double profit = clickPay + Double.parseDouble(withholdPay) - cpaPay - uvcpaPay ;
            map.put("profit",profit);  //利润
            list.add(map);
        }
        //分页
        List<Map<String,Object>> pageList= new ArrayList<>();
        int size=list.size();
        int pageStart=cnds.getPageNum()==1?0:(cnds.getPageNum()-1)*10;
        int pageEnd=size<cnds.getPageNum()*10?size:cnds.getPageNum()*10;
        if(size>pageStart){
            pageList =list.subList(pageStart, pageEnd);
        }
        return this.joinformateJson(json, "success", list.size(), pageList);
    }

    @Override
    public CallBackBean qryMyChannelList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.clCnd1(cnds);
        int pageNum = cnds.getPageNum();
        List<Record> sourceAll = new ArrayList<>();
        Record record = Record.create();
        if (!"".equals(cnds.getRows().getSource_cnd())&& null!=cnds.getRows().getSource_cnd()){
            record.put("source",cnds.getRows().getSource_cnd());
            sourceAll.add(record);
        }else {
            sourceAll = dataClannelDao.fetchOpenLogin(cnds);
        }
        List<Map<String,Object>> list = new ArrayList<>();
        for (Record source : sourceAll) {
            cnds.getRows().setSource_cnd(source.get("source").toString());
            Map<String,Object> map = new HashMap<>();
            String withholdPay = findWithholdPayBySource(cnds);



            Record payCpa = findPayCpaPayBySource(cnds);
            Map<String, Object> mapCount = dropList(cnds,source.get("source").toString());
            double cpaPay = 0;
            int dropUv = Integer.parseInt(mapCount.get("uv").toString());
            int dropCount = Integer.parseInt(mapCount.get("count").toString());

            if (payCpa.get("cpaType").toString().equals("0")){
                cpaPay = Double.parseDouble(String.valueOf(dropUv)) * Double.parseDouble(payCpa.get("cpa").toString());
            }
            if (payCpa.get("cpaType").toString().equals("1")){
                cpaPay = Double.parseDouble(String.valueOf(dropCount)) * Double.parseDouble(payCpa.get("cpa").toString());
            }
            cnds = SqlUtils.apCnd1(cnds);

            cnds.setPageNum(1);
            List<Record> res = dataSourceDao.qryDataRoorList(cnds , "AND dvl.source = '" + source.get("source").toString() + "'"," and du.create_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()) +"' "+" and du.create_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()) +"' ");
            String uv ="0";
            String count= "0";
            int order = 0;
            if (res.size()> 0){
                uv = res.get(0).get("uv").toString();
                count = res.get(0).get("count").toString();
                order = Integer.parseInt(res.get(0).get("bank").toString()) + Integer.parseInt(res.get(0).get("concat").toString()) + Integer.parseInt(res.get(0).get("yun").toString());
            }
            String orderLead=Integer.parseInt(count)==0?"0":new DecimalFormat("0.00").format(order/Double.parseDouble(count));//设置保留位数
            map.put("source",source.get("source")); //渠道来源
            map.put("uv",uv); //实际点击数
            map.put("count",count); //实际注册数
            map.put("dropUv",dropUv); //缩量点击数
            map.put("dropCount",dropCount); //缩量注册数
            map.put("cpaPay",cpaPay); //cpa金额
            map.put("withholdPay",withholdPay); // 扣款金额
            map.put("order",order); // 认证数
            map.put("orderLead",orderLead); // 认证率
            list.add(map);
        }
        //分页
        List<Map<String,Object>> pageList= new ArrayList<>();
        int size=list.size();
        int pageStart=pageNum==1?0:(pageNum-1)*10;
        int pageEnd=size<pageNum*10?size:pageNum*10;
        if(size>pageStart){
            pageList =list.subList(pageStart, pageEnd);
        }
        return this.joinformateJson(json, "success", list.size(), pageList);
    }


    public Record  findPayCpaPayBySource(Cnds cnds) {

        if (cnds.getRows().getSource_cnd() !=null && !cnds.getRows().getSource_cnd().equals("")){
            cnds.getRows().setSource("and sl.USER_CODE = '" + cnds.getRows().getSource_cnd() +"' "  );
        }
        Record payCpa = dataClannelDao.findPayCpaPayBySource(cnds);
        return payCpa;
    }

    public String  findUserBySource(Cnds cnds) {

        StringBuffer cnd = new StringBuffer();
        if (cnds.getRows().getSource_cnd() !=null && !cnds.getRows().getSource_cnd().equals("")){
            cnds.getRows().setSource("and du.source = '" + cnds.getRows().getSource_cnd() +"' "  );
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()))) {
            cnd.append(" and du.create_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd())+ "' " );
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()))) {
            cnd.append(" and du.create_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd())+ "' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        String count =  String.valueOf(dataClannelDao.findUserBySource(cnds).size());
        return count;
    }

    public List<Record>  findClickCpaPayBySource(Cnds cnds) {

        StringBuffer cnd = new StringBuffer();
        if (cnds.getRows().getSource_cnd() !=null && !cnds.getRows().getSource_cnd().equals("")){
            cnds.getRows().setSource("and du.source = '" + cnds.getRows().getSource_cnd() +"' "  );
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()))) {
            cnd.append(" and du.create_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd())+ "' " );
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()))) {
            cnd.append(" and du.create_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd())+ "' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        List<Record> clickList = dataClannelDao.findClickCpaPayBySource(cnds);
        return clickList;
    }

    public String findWithholdPayBySource(Cnds cnds) {

        StringBuffer cnd = new StringBuffer();
        if (cnds.getRows().getSource_cnd() !=null && !cnds.getRows().getSource_cnd().equals("")){
            cnds.getRows().setSource("and source = '" + cnds.getRows().getSource_cnd() +"' "  );
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()))) {
            cnd.append(" and du.create_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd())+ "' " );
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()))) {
            cnd.append(" and du.create_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd())+ "' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        Record withholdPayBySource = dataClannelDao.findWithholdPayBySource(cnds);
        String withholdPay= "";
        if (null!=withholdPayBySource.get("pay")&&""!=withholdPayBySource.get("pay")){
           withholdPay = dataClannelDao.findWithholdPayBySource(cnds).get("pay").toString();
        }
        return withholdPay;
    }



    public Map<String,Object> dropList(Cnds cnds,String source){
        List<Record> qdaoSls = null;
        String quantity = "";
        qdaoSls = dataSourceDao.qryQuDaoSl(cnds, source);
        List<Map<String, Object>> dateQdaoList = DateUtil.getDateQdaoList(qdaoSls,cnds);
        Collections.reverse(dateQdaoList);
        List<DcDict> payOrderList = configDictDao.qryDictByDataType("QUANTITY");
        for (DcDict dcDict : payOrderList) {
            quantity = dcDict.getItemValue();
        }
        int countCount = 0;
        int countUvCount = 0;
        int uvCount = 0;
        int countCount2 = 0;
        int countUvCount2 = 0;
        int uvCount2 = 0;
        List<Map<String,Object>> qDaoCount = new ArrayList<>();
        List<Record> dataSumAlls= dataSourceDao.qryDataSumList(cnds,source,"2000-00-00 00:00:00","2200-00-00 00:00:00");
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
            dataSum = dataSourceDao.qryDataSumList(cnds,source,map.get("startTime").toString(),map.get("endTime").toString());
            Collections.reverse(dataSum);
            for (Record record : dataSum) {

                if("2000-00-00 00:00:00".equals(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd())) && "2200-00-00 00:00:00".equals(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd())) ) {
                    if (Integer.parseInt(record.get("uv").toString())<Integer.parseInt(quantity)&&uvCount2<Integer.parseInt(quantity)
                            &&Integer.parseInt(record.get("uv").toString())+uvCount2<Integer.parseInt(quantity)){
                        countCount =  Integer.parseInt(record.get("count").toString());
                        countUvCount =  Integer.parseInt(record.get("count_uv").toString());
                        uvCount =  Integer.parseInt(record.get("uv").toString());

                    }else if (uvCount2>Integer.parseInt(quantity)){
                        countCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count").toString()),Double.parseDouble(map.get("sl").toString())));
                        countUvCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count_uv").toString()),Double.parseDouble( map.get("sl").toString())));
                        uvCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("uv").toString()),Double.parseDouble( map.get("sl").toString())));
                    }else if(uvCount2<Integer.parseInt(quantity) &&Integer.parseInt(record.get("uv").toString())+uvCount2>Integer.parseInt(quantity)){
                        int sum = Integer.parseInt(record.get("uv").toString()) + uvCount2;
                        int i = sum - Integer.parseInt(quantity);//需要扣量
                        int i1 = Integer.parseInt(record.get("uv").toString()) - i; // 不需要扣量
                        uvCount =  i1+ getInt(ZfSignUtil.mul(i,Double.parseDouble( map.get("sl").toString())));

                        double iv =  new BigDecimal((float)uvCount/Integer.parseInt(record.get("uv").toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        countCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count").toString()),iv));
                        countUvCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count_uv").toString()),iv));

                    }
                    countCount2 = countCount+countCount2;
                    countUvCount2 = countUvCount+countUvCount2;
                    uvCount2 = uvCount+uvCount2;


                }else {
                    for (Record record1 : dataSumAll) {
                        if (record1.get("visit_time").equals(record.get("visit_time"))){
                            double vi = 1;
                            uvCount =  Integer.parseInt(record.get("uv").toString())-Integer.parseInt(record1.get("uv").toString())+ getInt(ZfSignUtil.mul(Double.parseDouble(record1.get("uv").toString()),Double.parseDouble(record1.get("vi").toString())));
                            if (!record1.get("uv").toString().equals("0") ){
                                vi = new BigDecimal((float) (uvCount / Integer.parseInt(record1.get("uv").toString()))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            }

                            countCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count").toString()),vi));
                            countUvCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count_uv").toString()),vi));

                        }else {
                            countCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count").toString()),Double.parseDouble(map.get("sl").toString())));
                            countUvCount =  getInt(ZfSignUtil.mul(Double.parseDouble(record.get("count_uv").toString()),Double.parseDouble( map.get("sl").toString())));
                            uvCount = getInt(ZfSignUtil.mul(Double.parseDouble(record.get("uv").toString()),Double.parseDouble( map.get("sl").toString())));
                        }
                    }

                }
                Map<String,Object> mapCount = new HashMap<>();
                mapCount.put("count",countCount);
                mapCount.put("source",record.get("source"));
                mapCount.put("visit_time",String.valueOf(record.get("visit_time")));
                mapCount.put("uv",uvCount);
                mapCount.put("count_uv",countUvCount);
                qDaoCount.add(mapCount);
            }
        }



        int countCountSum = 0;
        int uvCountSum = 0;
        for (Map<String, Object> map : qDaoCount) {
            countCountSum = Integer.parseInt(map.get("count").toString()) + countCountSum;
            uvCountSum = Integer.parseInt(map.get("uv").toString()) + uvCountSum;
        }
        Map<String,Object> mapCount = new HashMap<>();
        mapCount.put("count",countCountSum);
        mapCount.put("uv",uvCountSum);
        return  mapCount;
    }



}
