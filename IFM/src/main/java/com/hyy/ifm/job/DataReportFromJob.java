package com.hyy.ifm.job;

import com.hyy.ifm.config.dao.ConfigDictDao;
import com.hyy.ifm.customer.dao.CustomerDao;
import com.hyy.ifm.data.dao.DataReportDao;
import com.hyy.ifm.data.pojo.DcProductReport;
import com.hyy.ifm.product.dao.ProductDao;
import com.hyy.ifm.product.pojo.DcProduct;
import com.hyy.ifm.product.service.KqService;
import com.hyy.ifm.util.DateUtil;
import com.hyy.ifm.util.StringUtil;
import com.sun.prism.impl.Disposer;
import org.nutz.dao.entity.Record;
import org.nutz.integration.quartz.annotation.Scheduled;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-19 14:44
 * @Description 生成每日报表定时任务
 */
@IocBean
@Scheduled(cron="0 0 1 * * ? ")
public class DataReportFromJob implements Job {

    @Inject
    private ProductDao productDao;
    @Inject
    private DataReportDao dataReportDao;

    @Inject
    private ConfigDictDao configDictDao;

    @Inject
    private CustomerDao customerDao;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<DcProduct> dcProducts = productDao.qryProductListWithOutDelete();
        for(DcProduct dcProduct : dcProducts) {
            try {
                DcProductReport dcProductReport = new DcProductReport();
                dcProductReport.setProductId(dcProduct.getId());

                //设置当前时间前一天 并保证00:00:00
                String date = DateUtil.getDate(DateUtil.addDay(new Date(), -1));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dcProductReport.setVisitTime(simpleDateFormat.parse(date + " 00:00:00"));

                Record record = dataReportDao.countDayOfVisitCountByProduct(dcProduct.getId());
                dcProductReport.setVisitCount(StringUtil.parseInt(record.getString("count")));

                dcProductReport.setRegCount(null);
                dcProductReport.setChangeRate(null);
                dcProductReport.setLoanCount(null);
                if(dcProduct.getSettleWayCpa() == null || dcProduct.getSettleWayCpa() == ""){
                    dcProduct.setSettleWayCpa("");
                }
                if(dcProduct.getSettleWayCps() == null || dcProduct.getSettleWayCps() == ""){
                    dcProduct.setSettleWayCps("");
                }
                if(dcProduct.getSettleWayCpa() == "" && dcProduct.getSettleWayCps() == ""){
                    dcProductReport.setSettleWay(dcProduct.getSettleCycle());
                }else if(dcProduct.getSettleWayCpa() != "" && dcProduct.getSettleWayCps() != ""){
                    dcProductReport.setSettleWay(dcProduct.getSettleCycle()+ "CPA" + dcProduct.getSettleWayCpa() + "CPS" + dcProduct.getSettleWayCps());
                }else if(dcProduct.getSettleWayCpa() != "" && dcProduct.getSettleWayCps() == ""){
                    dcProductReport.setSettleWay(dcProduct.getSettleCycle()+ "CPA" + dcProduct.getSettleWayCpa());
                }else if(dcProduct.getSettleWayCpa() == "" && dcProduct.getSettleWayCps() != ""){
                    dcProductReport.setSettleWay(dcProduct.getSettleCycle() + "CPS" + dcProduct.getSettleWayCps());
                }
                dcProductReport.setSettleState("0");
                dcProductReport.setSettleMoney(null);
                dcProductReport.setRemark("");
                dataReportDao.insetProductReport(dcProductReport);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
