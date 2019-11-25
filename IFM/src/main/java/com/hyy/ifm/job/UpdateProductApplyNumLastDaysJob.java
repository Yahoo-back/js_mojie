package com.hyy.ifm.job;

import com.alibaba.fastjson.JSONObject;
import com.hyy.ifm.config.dao.ConfigDictDao;
import com.hyy.ifm.config.pojo.DcDict;
import com.hyy.ifm.customer.dao.CustomerDao;
import com.hyy.ifm.product.dao.UserBankcardDao;
import com.hyy.ifm.product.pojo.UserBankcard;
import com.hyy.ifm.product.pojo.UserOrder;
import com.hyy.ifm.product.service.KqService;
import com.hyy.ifm.util.ZfSignUtil;
import com.hyy.ifm.util.kq.util.GetIpAddressUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nutz.integration.quartz.annotation.Scheduled;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.*;

@IocBean
@Scheduled(cron = "0 0 9,18 * * ?")
public class UpdateProductApplyNumLastDaysJob implements Job {


    private Log log = LogFactory.getLog(UpdateProductApplyNumLastDaysJob.class);

    @Inject
    private KqService kqService;

    @Inject
    private ConfigDictDao configDictDao;

    @Inject
    private CustomerDao customerDao;

    @Inject
    private UserBankcardDao userBankcardDao;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("开始每天的定时扣款");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            Date today = sdf.parse(sdf.format(date));
            Date date1 ;
            Date beforeDay ;
            List<UserOrder> mapList = customerDao.qryUserOrderByStatus();
            String ip = GetIpAddressUtils.getLinuxLocalIp();//获取本机的IP地址
            JSONObject payJson = new JSONObject();
            JSONObject detailJson = null;
            List<DcDict> payMoney = configDictDao.qryDictByDataType("PAY_MONEY");
            String [] str = payMoney.get(0).getItemValue().split(",");
            int countMoney = 0;
            for (int i = 0; i < mapList.size(); i++) {
                date1 = mapList.get(i).getApplyTime();
                beforeDay = sdf.parse(sdf.format(date1));
                if (today.after(beforeDay)) {
                    Date changeDate = today;
                    today = beforeDay;
                    beforeDay = changeDate;
                }
                int count = (int) ((beforeDay.getTime() - today.getTime()) / (1000*3600*24));
                today = new Date();
                if(count <= 14 && count >= 10){
                    Map<String, Object> args1 = new HashMap<>();
                    args1.put("userId", mapList.get(i).getUserId());
                    UserBankcard userBankcard = userBankcardDao.selectByUserId(args1);
                    payJson.put("uuid", mapList.get(i).getUserId());
                    payJson.put("ip", ip);
                    payJson.put("money",mapList.get(i).getRepayAmt());
                    if("1".equals(userBankcard.getAuthFrom())){
                        detailJson = kqService.kqBindCardPay(payJson);
                        if(detailJson.containsKey("code")){
                            if("-1".equals(detailJson.get("code")) || "2".equals(detailJson.get("code"))){
                                for (int j = 0; j < str.length; j++) {
                                    if(Double.parseDouble(ZfSignUtil.sub(Double.parseDouble(detailJson.get("money").toString()),Double.parseDouble(str[j])))
                                            >= 0){
                                        payJson.put("money", str[j]);
                                        detailJson = kqService.kqBindCardPay(payJson);
                                        if("0".equals(detailJson.get("code")) || "1".equals(detailJson.get("code"))){
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }else if("2".equals(userBankcard.getAuthFrom())){
                        detailJson = kqService.cjBindCardPay(payJson);
                        if(detailJson.containsKey("code")){
                            if("-1".equals(detailJson.get("code")) || "2".equals(detailJson.get("code"))){
                                for (int j = 0; j < str.length; j++) {
                                    if(Double.parseDouble(ZfSignUtil.sub(Double.parseDouble(detailJson.get("money").toString()),Double.parseDouble(str[j])))
                                            >= 0){
                                        payJson.put("money", str[j]);
                                        detailJson = kqService.cjBindCardPay(payJson);
                                        if("0".equals(detailJson.get("code")) || "1".equals(detailJson.get("code"))){
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }else if("3".equals(userBankcard.getAuthFrom())){
                        detailJson = kqService.xfBindCardPay(payJson);
                        if(detailJson.containsKey("code")){
                            if("-1".equals(detailJson.get("code")) || "2".equals(detailJson.get("code"))){
                                for (int j = 0; j < str.length; j++) {
                                    if(Double.parseDouble(ZfSignUtil.sub(Double.parseDouble(detailJson.get("money").toString()),Double.parseDouble(str[j])))
                                            >= 0){
                                        payJson.put("money", str[j]);
                                        detailJson = kqService.xfBindCardPay(payJson);
                                        if("0".equals(detailJson.get("code")) || "1".equals(detailJson.get("code"))){
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    countMoney++;
                }
            }
            log.info("10-14天人数："+ mapList.size());
            log.info("每天的定时扣款结束！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public static List<String>  getStr(String money,String [] strs){
        ArrayList<String> str = new ArrayList<>();
        for (String s : strs) {
            if (Integer.parseInt(money) > Integer.parseInt(s)){
                str.add(s);
            }
        }
        return str;
    }

    public static void main(String[] args) {
        String money = "50";
        String [] strs = new String[]{"299","199","100","50"};
        List<String> str = getStr(money, strs);
        for (String s : str) {
            System.out.println(s);
        }
    }
}