package com.hyy.ifm.customer.service.impl;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.config.dao.ConfigDictDao;
import com.hyy.ifm.config.pojo.DcDict;
import com.hyy.ifm.customer.dao.CustomerDao;
import com.hyy.ifm.customer.pojo.*;
import com.hyy.ifm.customer.service.CustomerService;
import com.hyy.ifm.sys.dao.UserDao;
import com.hyy.ifm.sys.pojo.IfmLogin;
import com.hyy.ifm.sys.pojo.IfmRole;
import com.hyy.ifm.sys.pojo.IfmUserOperate;
import com.hyy.ifm.sys.service.impl.SqlUtils;
import com.hyy.ifm.util.Constants;
import com.hyy.ifm.util.StringUtil;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author 毛椅俊
 * @Date 2018-03-15 10:30
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "customerService")
public class CustomerServiceImpl extends BaseService implements CustomerService {

    @Inject
    private CustomerDao customerDao;
    @Inject
    private UserDao userDao;
    @Inject
    private ConfigDictDao configDictDao;

    @Override
    public CallBackBean qryCustomerList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        List<Record> res = new ArrayList<>();
        int count = 0;
        if (cnds.getRows() !=null){
            if (cnds.getRows().getUser_auth_cnd()!=null && cnds.getRows().getUser_auth_cnd().equals("0")){
                res = customerDao.qryCustomerListNone(cnds);
                 count = customerDao.countCustomerListNone(cnds);
            }else {
                res = customerDao.qryCustomerList(cnds);
                 count = customerDao.countCustomerList(cnds);
            }
        }
       // int count = customerDao.countCustomerList(cnds);
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean qryCustomerMoneyAll(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        //总金额
        List<Record> res = customerDao.qryCustomerMoneyAll(cnds);
        int count = customerDao.countCustomerList(cnds);
        //当日支付金额
        StringBuffer cnd = new StringBuffer();
        if(!"".equals(StringUtil.nvl(cnds.getRows().getPay_time_FROM_cnd()))) {
            cnd.append(" and create_time >= '"+ StringUtil.nvl(cnds.getRows().getPay_time_FROM_cnd()) +"' ");
        }else {
            String from  = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date());
            cnd.append(" and create_time >= '"+ from +"' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getPay_time_TO_cnd()))) {
            cnd.append(" and create_time <= '"+ StringUtil.nvl(cnds.getRows().getPay_time_TO_cnd()) +"' ");
        }else {
            String to  = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(new Date());
            cnd.append(" and create_time <= '"+ to +"' ");
        }
            cnds.setFuzzyCnd(cnd.toString());
        List<DcDict> payOrderList = configDictDao.qryDictByDataType("PAYORDER_LIST");
        double amt = 0;
        for (Record re : res) {
            for (DcDict dcDict : payOrderList) {
                String[] split = dcDict.getItemValue().split(",");
                for (String s : split) {
                    List<Record> payAmt =  customerDao.qryPayAmt(cnds,s);
                    for (Record record : payAmt) {
                       amt = amt + Double.parseDouble(record.get("amt").toString());
                    }
                }
            }
            re.put("pay_time",amt);
        }

        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean qryCustomerMoneyAllSys(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        boolean f = null != cnds.getRows().getName_cnd() ? true : false;
        List<IfmLogin> userList = userDao.qryUserList2(cnds.getRows().getName_cnd(),f);
        List<Map<String,Object>> res = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            Record record = customerDao.qrySysNameAllMoney(userList.get(i).getUserCode());
            Map<String,Object> map = new HashMap<>();
            map.put("name",userList.get(i).getUserCode());
            map.put("pay_amt",record.get("pay_amt"));
            res.add(map);
        }
        // List<Record> res = customerDao.qryCustomerMoneyAll(cnds);
        int count = customerDao.countCustomerList(cnds);
        return this.joinformateJson(json, "success", count, res);
    }


    @Override
    public CallBackBean exportCustomerList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        List<Record> res = customerDao.exportCustomerList(cnds);
        return this.joinformateJson(json, "success", res);
    }




    @Override
    public CallBackBean qryCustomerById(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        Record record = customerDao.qryCustomerById(StringUtil.parseInt(cnds.getRows().getId()));
        List<DcUserAttach> dcUserAttaches =  customerDao.qryCustomerAttachById(StringUtil.parseInt(cnds.getRows().getId()));
        record.set("attach", dcUserAttaches);
        return this.joinformateJson(json, "success", record);
    }

    @Override
    public CallBackBean updateCustomerById(String json) {
        try {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        List<DcUserInfo> dcUserInfo = customerDao.qryCustomerInfoById(StringUtil.parseInt(cnds.getRows().getId()));
        String userName = dcUserInfo.get(0).getUserName();
        String idCard = dcUserInfo.get(0).getIdCard();
        dcUserInfo.get(0).setUserName(StringUtil.nvl(cnds.getRows().getUser_name()));
        dcUserInfo.get(0).setIdCard(StringUtil.nvl(cnds.getRows().getId_card()));
        customerDao.updateCustomerInfo(dcUserInfo.get(0));
        IfmUserOperate ifmUserOperate = new IfmUserOperate();
        ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
        ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
        ifmUserOperate.setOperate_date(new Date());
        ifmUserOperate.setOperate("修改用户详情:原名："+userName+"身份证："+idCard+"修改后："+dcUserInfo.get(0).getUserName()+"身份证："+dcUserInfo.get(0).getIdCard());
        userDao.insertOperate(ifmUserOperate);
        return this.joinformateJson(json, "success", "");
    } catch (Exception e) {
        e.printStackTrace();
        return this.joinformateJson(json, "修改用户失败", "");
    }

    }

    @Override
    public CallBackBean updateCustomer(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            DcUser dcUser = customerDao.qryCustomerById2(StringUtil.parseInt(cnds.getRows().getId()));

            if (null != dcUser) {
                if("1".equals(StringUtil.nvl(cnds.getRows().getStatus()))) {
                    if("1".equals(dcUser.getStatus())) {
                        return this.joinformateJson(json, "该用户已被禁用，请刷新后重试", "");
                    }
                } else if("0".equals(StringUtil.nvl(cnds.getRows().getStatus()))) {
                    if("0".equals(dcUser.getStatus())) {
                        return this.joinformateJson(json, "该用户已被恢复，请刷新后重试", "");
                    }
                }
                dcUser.setStatus(StringUtil.nvl(cnds.getRows().getStatus()));
                customerDao.updateCustomer(dcUser);

                IfmUserOperate ifmUserOperate = new IfmUserOperate();
                ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
                ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("修改用户");
                userDao.insertOperate(ifmUserOperate);
                return this.joinformateJson(json, "success", "");
            }
            return this.joinformateJson(json, "禁用或恢复用户失败", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "禁用或恢复用户失败", "");
        }
    }

}
