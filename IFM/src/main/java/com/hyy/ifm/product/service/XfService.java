package com.hyy.ifm.product.service;

import com.alibaba.fastjson.JSONObject;
import com.hyy.ifm.common.pojo.CallBackBean;

import java.util.Map;

public interface  XfService {


    /**
     * 支付订单查询
     * @param merOrderId 商户订单号
     * @param serialNo 讯联智付交易流水号
     * @return
     */
    Map<String, String> xfQueryPay(String  merOrderId,String  serialNo);

    /**
     * 退款订单查询
     * @param merOrderId 商户订单号
     * @param serialNo 讯联智付交易流水号
     * @return
     */
    Map<String, String> xfQueryCharge(String  merOrderId, String  serialNo);

}
