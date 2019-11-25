package com.hyy.ifm.product.service;

import com.alibaba.fastjson.JSONObject;
import com.hyy.ifm.common.pojo.CallBackBean;

import java.util.Map;

public interface KqService {


    // 绑卡支付
    JSONObject kqBindCardPay(JSONObject param);

    // 绑卡支付
    JSONObject cjBindCardPay(JSONObject param);


    // 绑卡支付
    JSONObject xfBindCardPay(JSONObject param);

    //代发
    public CallBackBean thirdPay(String json);






}
