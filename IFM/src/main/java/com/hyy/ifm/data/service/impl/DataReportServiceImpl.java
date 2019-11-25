package com.hyy.ifm.data.service.impl;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.data.dao.DataReportDao;
import com.hyy.ifm.data.pojo.Cnds;
import com.hyy.ifm.data.pojo.DcProductReport;
import com.hyy.ifm.data.service.DataReportService;
import com.hyy.ifm.product.dao.ProductDao;
import com.hyy.ifm.product.pojo.DcProduct;
import com.hyy.ifm.sys.dao.UserDao;
import com.hyy.ifm.sys.pojo.IfmSysLoginLog;
import com.hyy.ifm.sys.pojo.IfmUser;
import com.hyy.ifm.sys.service.impl.SqlUtils;
import com.hyy.ifm.util.StringUtil;

import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-19 15:19
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "dataReportService")
public class DataReportServiceImpl extends BaseService implements DataReportService {

    @Inject
    private DataReportDao dataReportDao;

    @Inject
    private ProductDao productDao;

    @Inject
    private UserDao userDao;

    @Override
    public CallBackBean qryDataReportList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        //加载当前时间前一天数据
        cnds = SqlUtils.apCnd4(cnds);
        List<Record> res = dataReportDao.qryDataReportList(cnds);
        int count = dataReportDao.countDataReportList(cnds);
        for(Record record : res) {
            if(StringUtil.isBlank(record.getString("settle_money"))) {
                record.set("settle_money", "0.00元");
            } else {
                record.set("settle_money", new DecimalFormat("#,##0.00").format(Double.parseDouble(record.getString("settle_money"))) + "元");
            }
        }
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
        public CallBackBean qryDataFinanceReportList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class,json);
        cnds = SqlUtils.apCnd8(cnds);

        List<Record> records = dataReportDao.qryDataFinanceReportList(cnds);
        int count = dataReportDao.countDataReportList(cnds);
        for(Record record : records){
            if(StringUtil.isBlank(record.getString("settle_money"))){
                record.set("settle_money","0.00元");
            }else {
                record.set("settle_money",new DecimalFormat("#,##0.00").format(Double.parseDouble(record.getString("settle_money")))+"元");
            }
        }
        return this.joinformateJson(json, "success",count,records);
    }

    @Override
    public CallBackBean countProductInfo(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            int reportId = StringUtil.parseInt(cnds.getRows().getReport_id());
            DcProductReport dcProductReport = dataReportDao.fetchProductReportById(reportId);
            if(null == dcProductReport) {
                return this.joinformateJson(json, "统计错误， 原因：该产品不存在", "");
            }

            DcProduct dcProduct = productDao.qryProductById(StringUtil.parseInt(dcProductReport.getProductId()));
            productDao.updateProduct(dcProduct);
            dcProductReport.setProductId(StringUtil.parseInt(dcProductReport.getProductId()));
            dcProductReport.setVisitTime(dcProductReport.getVisitTime());
            dcProductReport.setVisitCount(dcProductReport.getVisitCount());

            DecimalFormat df = new DecimalFormat("#.0000");
            /*dcProductReport.setChangeRate(StringUtil.parseDouble(df.format(StringUtil.parseDouble(cnds.getRows().getReg_count_form()) / dcProductReport.getVisitCount())));*/
            if(StringUtil.parseDouble(dcProductReport.getVisitCount()) != 0){
                dcProductReport.setChangeRate(StringUtil.parseDouble(StringUtil.parseDouble(dcProductReport.getRegCount())/StringUtil.parseDouble(dcProductReport.getVisitCount())));
            }else {
                dcProductReport.setChangeRate(dcProductReport.getChangeRate());
            }
            if(dcProductReport.getSettleMoney() == null){
                dcProductReport.setSettleMoney(0.00);
            }

            //计算应结算金额
            if(dcProduct.getSettleWayCpa() != null && dcProduct.getSettleWayCpa() != "" && cnds.getRows().getReg_count_form() != "" && cnds.getRows().getReg_count_form() != ""){
                double temp = StringUtil.parseDouble(dcProduct.getSettleWayCpa())*StringUtil.parseDouble(cnds.getRows().getReg_count_form());
                dcProductReport.setSettleMoney(dcProductReport.getSettleMoney()+temp);
            }
            if(dcProduct.getSettleWayCps() != null && dcProduct.getSettleWayCps() != "" && cnds.getRows().getLoan_count_form() != null && cnds.getRows().getLoan_count_form() != ""){
                double temp = StringUtil.parseDouble(dcProduct.getSettleWayCps())*StringUtil.parseDouble(cnds.getRows().getLoan_count_form());
//                double temp = StringUtil.parseDouble(dcProduct.getSettleWayCps())*StringUtil.parseDouble(cnds.getRows().getVisit_count_form());
                dcProductReport.setSettleMoney(dcProductReport.getSettleMoney()+temp);
            }

            if(StringUtil.nvl(cnds.getRows().getReg_count_form()) != null || StringUtil.nvl(cnds.getRows().getReg_count_form()) != ""){
                dcProductReport.setRegCount(StringUtil.parseInteger(cnds.getRows().getReg_count_form()));
            }

            if(StringUtil.nvl(cnds.getRows().getLoan_count_form()) != null || StringUtil.nvl(cnds.getRows().getLoan_count_form()) != ""){
                dcProductReport.setLoanCount(StringUtil.parseInteger(cnds.getRows().getLoan_count_form()));

//                dcProductReport.setVisitCount(StringUtil.parseInteger(cnds.getRows().getVisit_count_form()));
            }
            dcProductReport.setSettleWay(dcProductReport.getSettleWay());
            dcProductReport.setSettleState(dcProductReport.getSettleState());
            dcProductReport.setRemark(StringUtil.nvl(cnds.getRows().getRemark_form()));
            dcProductReport.setSerialNumber(dcProductReport.getSerialNumber());
            dcProductReport.setSettleCertificatec(dcProductReport.getSettleCertificatec());
            dcProductReport.setSettleTime(dcProductReport.getSettleTime());
            dataReportDao.updateProductReport(dcProductReport);
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            return this.joinformateJson(json, "统计错误， 请联系管理员", "");
        }
    }

    @Override
    public CallBackBean settleProductInfo(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            int reportId = StringUtil.parseInt(cnds.getRows().getReport_id());
            DcProductReport dcProductReport = dataReportDao.fetchProductReportById(reportId);
            if(null == dcProductReport) {
                return this.joinformateJson(json, "统计错误， 原因：该产品不存在", "");
            }

            dcProductReport.setSettleMoney(StringUtil.parseDouble(cnds.getRows().getSettle_money_form()));
            dcProductReport.setRemark(StringUtil.nvl(cnds.getRows().getRemark_form()));
            dcProductReport.setSerialNumber(StringUtil.nvl(cnds.getRows().getSerial_number()));
            dcProductReport.setSettleCertificatec(StringUtil.nvl(cnds.getRows().getFile_uri()));
            dcProductReport.setSettleTime(dcProductReport.getSettleTime());
            dcProductReport.setSettleState("1");

            dataReportDao.updateProductReport(dcProductReport);
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            return this.joinformateJson(json, "统计错误， 请联系管理员", "");
        }
    }

    @Override
    public CallBackBean settleProduct(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            int reportId = StringUtil.parseInt(cnds.getRows().getReport_id());
            DcProductReport dcProductReport = dataReportDao.fetchProductReportById(reportId);
            if(null == dcProductReport) {
                return this.joinformateJson(json, "结算错误， 原因：该产品不存在", "");
            }
            if("1".equals(dcProductReport.getSettleState())) {
                return this.joinformateJson(json, "该产品已经完成结算，请刷新后查看", "");
            }
            dcProductReport.setSettleState("1");
            dataReportDao.updateProductReport(dcProductReport);
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            return this.joinformateJson(json, "结算错误， 请联系管理员", "");
        }
    }

    @Override
    public CallBackBean exportDataProductReportList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd4(cnds);
        List<Record> res = dataReportDao.exportDataProductReportList(cnds);
        for(Record record : res) {
            if(StringUtil.isBlank(record.getString("settle_money"))) {
                record.set("settle_money", "0.00元");
            } else {
                record.set("settle_money", new DecimalFormat("#,##0.00").format(Double.parseDouble(record.getString("settle_money"))) + "元");
            }
        }
        return this.joinformateJson(json, "success", res);
    }

    @Override
    public CallBackBean exportDataFinanceReportList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd8(cnds);
        List<Record> records = dataReportDao.exportDataFinanceReportList(cnds);
        for (Record record : records){
            if(StringUtil.isBlank(record.getString("settle_money"))) {
                record.set("settle_money", "0.00元");
            } else {
                record.set("settle_money", new DecimalFormat("#,##0.00").format(Double.parseDouble(record.getString("settle_money"))) + "元");
            }
        }
        return this.joinformateJson(json,"success",records);
    }

    @Override
    public CallBackBean queryDataProductReportTotal(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd4(cnds);
        List<Record> dcProductReports = dataReportDao.queryDataProductReportTotal(cnds);

        Record res = new Record();
        //已结算金额
        double settle_money = 0.00;
        //注册总数
        int regCount = 0;
        //点击总数
        int clickCount = 0;
        //待结算金额
        double waitMoney = 0.00;
        //总结算金额
        double totalMoney = 0.00;
        for(Record record : dcProductReports) {
            regCount += StringUtil.parseInt(record.getString("reg_count"));
            clickCount += StringUtil.parseInt(record.getString("visit_count"));

            //待结算
            if(record.getString("settle_state").equals("0")) {
                if(StringUtil.isNotBlank(record.getString("settle_money"))) {
                    waitMoney += StringUtil.parseDouble(record.getString("settle_money"));
                }
            }
            if(record.getString("settle_state").equals("1") && StringUtil.isNotBlank(record.getString("settle_money"))) {
                settle_money += StringUtil.parseDouble(record.getString("settle_money"));
            }
        }

        totalMoney = waitMoney + settle_money;
        res.set("regCount", regCount);
        res.set("clickCount", clickCount);
        res.set("settleMoney", new DecimalFormat("#,##0.00").format(settle_money) + "元");
        res.set("waitMoney", new DecimalFormat("#,##0.00").format(waitMoney) + "元");
        res.set("totalMoney", new DecimalFormat("#,##0.00").format(totalMoney) + "元");
        return this.joinformateJson(json, "success", res);
    }

    @Override
    public CallBackBean qryCountProductInfo(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        DcProductReport dcProductReport = dataReportDao.qryCountProductInfo(cnds.getRows().getId());
        DcProduct dcProduct = productDao.qryProductById(dcProductReport.getProductId());

        if(dcProductReport.getSettleMoney() == null){
            dcProductReport.setSettleMoney(0.00);
        }
        HashMap<String,Object> dataProducts = new HashMap<String,Object>();
        dataProducts.put("dcProductReport",dcProductReport);
        dataProducts.put("dcProduct",dcProduct);
        return this.joinformateJson(json, "success", dataProducts);
    }

    @Override
    public CallBackBean qrySettleProductInfo(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        DcProductReport dcProductReport = dataReportDao.qryCountProductInfo(cnds.getRows().getId());
        dcProductReport.setSettleTime(new Date());
//        if (dcProductReport.getUserId() == "" || dcProductReport.getUserId() == null){
//            dcProductReport.setUserId(cnds.getUserCode());
//        }

//        IfmUser user = userDao.fetchUserByLngid(dcProductReport.getUserId());

        IfmUser user = userDao.fetchUserByLngid(cnds.getUserCode());

        HashMap<String,Object> dataproduct = new HashMap<String,Object>();
        dataproduct.put("dcProductReport",dcProductReport);
        dataproduct.put("user",user);
        return this.joinformateJson(json, "success", dataproduct);
    }

    @Override
    public CallBackBean qryProductSettleWayAll(String json) {
        List<Record> record = dataReportDao.qryProductSettleWayAll(json);
        return this.joinformateJson(json,"success",record);
    }
}
