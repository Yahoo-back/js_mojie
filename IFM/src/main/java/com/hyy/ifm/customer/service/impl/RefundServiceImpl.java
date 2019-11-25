package com.hyy.ifm.customer.service.impl;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.customer.dao.CustomerDao;
import com.hyy.ifm.customer.dao.RefundDao;
import com.hyy.ifm.customer.pojo.Cnds;
import com.hyy.ifm.customer.pojo.DcUser;
import com.hyy.ifm.customer.pojo.DcUserAttach;
import com.hyy.ifm.customer.pojo.DcUserRefund;
import com.hyy.ifm.customer.service.CustomerService;
import com.hyy.ifm.customer.service.RefundService;
import com.hyy.ifm.product.dao.UserOrderDao;
import com.hyy.ifm.product.pojo.DcProduct;
import com.hyy.ifm.product.pojo.UserOrder;
import com.hyy.ifm.sys.dao.UserDao;
import com.hyy.ifm.sys.pojo.IfmLogin;
import com.hyy.ifm.sys.pojo.IfmUserOperate;
import com.hyy.ifm.sys.service.impl.SqlUtils;
import com.hyy.ifm.util.StringUtil;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import java.math.BigDecimal;
import java.util.*;

@IocBean(name = "refundService")
public class RefundServiceImpl extends BaseService implements RefundService {

    @Inject
    private RefundDao refundDao;
    @Inject
    private UserOrderDao userOrderDao;
    @Inject
    private UserDao userDao;
    @Inject
    private CustomerDao customerDao;

    @Override
    public CallBackBean qryRefund(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd3(cnds);
        List<Record> res = refundDao.qryRefund(cnds);
        return this.joinformateJson(json, "success", res.size(),res);
    }

    @Override
    public CallBackBean updateOrderAuditStatus(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            UserOrder userOrder = userOrderDao.findByPrimary(StringUtil.parseInt(cnds.getRows().getId()));

            if (null != userOrder) {
                if("0".equals(StringUtil.nvl(cnds.getRows().getStatus()))) {
                    if ("5".equals(userOrder.getUserAuth())) {
                        return this.joinformateJson(json, "该用户已申请退款", "");
                    }
                    userOrder.setStatus("1");
                    userOrder.setUserAuth("5");
                    userOrder.setSysUserId(StringUtil.parseInt(cnds.getUserCode()));
                } else if("1".equals(StringUtil.nvl(cnds.getRows().getStatus()))) {
                    if(userOrder.getPayAmt() >= userOrder.getApplyAmt()){
                        userOrder.setStatus("1");
                    }else{
                        userOrder.setStatus("0");
                    }
                    userOrder.setUserAuth("0");
                    userOrder.setSysUserId(null);
                }
                refundDao.updateOrderAuditStatus(userOrder);

                IfmUserOperate ifmUserOperate = new IfmUserOperate();
                ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
                ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("修改跟单");
                userDao.insertOperate(ifmUserOperate);
                return this.joinformateJson(json, "success", "");
            }
            return this.joinformateJson(json, "跟单或取消跟单成功", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "跟单或取消跟单失败", "");
        }
    }

    @Override
    public CallBackBean qryMyRefund(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        List<Record> res = refundDao.qryMyRefund(cnds);
        int count = refundDao.qryMyRefundCount(cnds);
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean saveRefund(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            UserOrder userOrder = userOrderDao.findByPrimary(StringUtil.parseInt(cnds.getRows().getId()));

            DcUserRefund dcUserRefund = new DcUserRefund();
            dcUserRefund.setUserId(StringUtil.parseInt(cnds.getRows().getId()));
            dcUserRefund.setPath(StringUtil.nvl(cnds.getRows().getFile_uri()));
            dcUserRefund.setCreateTime(new Date());

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
            ifmUserOperate.setOperate_date(new Date());

            if(null != cnds.getRows().getMoney()){
                userOrder.setUserAuth("1");
                userOrder.setRefundAmt( new BigDecimal(cnds.getRows().getMoney()).doubleValue());
                userOrder.setRefundTime(new Date());
                dcUserRefund.setType("8");
                ifmUserOperate.setOperate("保存退款");
            }else{
                userOrder.setUserAuth("0");
                if(userOrder.getApplyAmt() > userOrder.getPayAmt()){
                    userOrder.setStatus("0");
                }
                userOrder.setRefundTime(new Date());
                dcUserRefund.setType("9");
                ifmUserOperate.setOperate("驳回退款");
            }
            userOrderDao.updateSelective(userOrder);
            refundDao.insertUserRefund(dcUserRefund);
            userDao.insertOperate(ifmUserOperate);

            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }

    @Override
    public CallBackBean qryRefundAttachById(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        Record record = customerDao.qryCustomerById(StringUtil.parseInt(cnds.getRows().getId()));
        List<DcUserRefund> dcUserRefund =  refundDao.qryRefundAttachById(StringUtil.parseInt(cnds.getRows().getId()));
        List<DcUserAttach> dcUserAttaches =  customerDao.qryCustomerAttachById(StringUtil.parseInt(cnds.getRows().getId()));
        record.set("refund", dcUserRefund);
        record.set("attach", dcUserAttaches);
        return this.joinformateJson(json, "success", record);
    }

    @Override
    public CallBackBean qryRefundList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        List<Record> res = refundDao.qryRefundList(cnds);
        int count = refundDao.qryRefundListCount(cnds);
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean listStatistics(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd4(cnds);
        List<Record> res = refundDao.listStatistics(cnds);
        int count = refundDao.listStatisticsCount(cnds);
        return this.joinformateJson(json, "success",count, res);
    }

    @Override
    public CallBackBean mailRefundList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd4(cnds);
        List<Record> res = refundDao.
                mailRefundList(cnds);
        int count = refundDao.mailRefundListCount(cnds);
        return this.joinformateJson(json, "success", count, res);
    }
}
