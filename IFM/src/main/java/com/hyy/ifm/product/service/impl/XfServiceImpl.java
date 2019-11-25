package com.hyy.ifm.product.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bill99.asap.exception.CryptoException;
import com.bill99.asap.service.ICryptoService;
import com.bill99.asap.service.impl.CryptoServiceFactory;
import com.bill99.schema.asap.commons.Mpf;
import com.bill99.schema.asap.data.SealedData;
import com.bill99.schema.asap.data.UnsealedData;
import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.config.dao.ConfigDictDao;
import com.hyy.ifm.config.pojo.DcDict;
import com.hyy.ifm.customer.dao.CustomerDao;
import com.hyy.ifm.customer.pojo.DcUser;
import com.hyy.ifm.customer.pojo.DcUserInfo;
import com.hyy.ifm.product.dao.*;
import com.hyy.ifm.product.pojo.*;
import com.hyy.ifm.product.service.KqService;
import com.hyy.ifm.product.service.XfService;
import com.hyy.ifm.sys.service.impl.SqlUtils;
import com.hyy.ifm.util.ZfSignUtil;
import com.hyy.ifm.util.cj.ChanPayUtil;
import com.hyy.ifm.util.cj.ChanpayGateway;
import com.hyy.ifm.util.kq.action.Post;
import com.hyy.ifm.util.kq.dto.Pay2bankOrder;
import com.hyy.ifm.util.kq.dto.Pay2bankRequest;
import com.hyy.ifm.util.kq.dto.Pay2bankResponse;
import com.hyy.ifm.util.kq.entity.TransInfo;
import com.hyy.ifm.util.kq.util.CCSUtil;
import com.hyy.ifm.util.kq.util.Consts;
import com.hyy.ifm.util.kq.util.JsonUtil;
import com.hyy.ifm.util.kq.util.PKIUtil;
import com.hyy.ifm.util.xf.util.ECTXmlUtil;
import com.hyy.ifm.util.xf.util.HttpClientUtil;
import com.hyy.ifm.util.xf.util.MerchantSignAndVerify;
import com.hyy.ifm.util.xf.util.R;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@IocBean(name = "XfService")
public class XfServiceImpl extends BaseService implements XfService {

    @Inject
    private XfConfigDao xfConfigDao;

    @Override
    public Map<String, String> xfQueryPay(String merOrderId, String serialNo) {
        Map<String, String> resultMap = null;
        try {
            XfConfig xfConfig = xfConfigDao.selectByPrimaryKey(7);
            resultMap = null;
            if (xfConfig == null) {
                return  resultMap;
            }
            Map<String, String> map = new HashMap<String, String>();
            // 2.1 基本参数
            map.put("version", "1.0.1");// 版本
            map.put("merchantId",xfConfig.getPartnerId());  //商户号
            map.put("reqDate", new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));//请求时间
            map.put("type", "0");// 交易性质  0-全部，1-支付，2-退款
            map.put("merOrderId", merOrderId);// 商户订单号
            map.put("serialNo", serialNo); // 讯联智付交易流水号
            //加签
            String to_sign = MerchantSignAndVerify.createLinkString(map);
            String sign = new String(MerchantSignAndVerify.sign(to_sign, xfConfig.getPartnerId()));
            map.put("sign", sign);
            //拼装成xml请求报文，并发送post请求,
            String xmlString = ECTXmlUtil.mapToXml(map, ECTXmlUtil.CPREQ_TSREQ);
            log.info("发出报文：" + xmlString);
            String responseString = HttpClientUtil.postToServerByXml(xmlString, xfConfig.getUrl());
            log.info("返回报文：" + responseString);
            //验签
            resultMap = ECTXmlUtil.xmlToMap(responseString);
            String result_sign = resultMap.get("sign");
            String to_verify = MerchantSignAndVerify.createLinkString(resultMap);
            if(MerchantSignAndVerify.verify(to_verify.getBytes(R.PGWConstant.UTF8), result_sign.getBytes(R.PGWConstant.UTF8))){
                log.info("验签成功");
            }else{
                log.error("验签失败");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, String> xfQueryCharge(String merOrderId, String serialNo) {
        Map<String, String> resultMap = null;
        try {
            XfConfig xfConfig = xfConfigDao.selectByPrimaryKey(6);
            resultMap = null;
            if (xfConfig == null) {
                return  resultMap;
            }
            Map<String, String> map = new HashMap<String, String>();
            // 2.1 基本参数
            map.put("version", "1.0.1");// 版本
            map.put("merchantId",xfConfig.getPartnerId());  //商户号
            map.put("reqDate", new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));//请求时间
            map.put("type", "0");// 交易性质  0-全部，1-支付，2-退款
            map.put("merOrderId", merOrderId);// 商户订单号
            map.put("serialNo", serialNo); // 讯联智付交易流水号
            //加签
            String to_sign = MerchantSignAndVerify.createLinkString(map);
            String sign = new String(MerchantSignAndVerify.sign(to_sign, xfConfig.getPartnerId()));
            map.put("sign", sign);
            //拼装成xml请求报文，并发送post请求,
            String xmlString = ECTXmlUtil.mapToXml(map, ECTXmlUtil.CPREQ_QUREQ);
            log.info("发出报文：" + xmlString);
            String responseString = HttpClientUtil.postToServerByXml(xmlString, xfConfig.getUrl());
            log.info("返回报文：" + responseString);
            //验签
            resultMap = ECTXmlUtil.xmlToMap(responseString);
            String result_sign = resultMap.get("sign");
            String to_verify = MerchantSignAndVerify.createLinkString(resultMap);
            if(MerchantSignAndVerify.verify(to_verify.getBytes(R.PGWConstant.UTF8), result_sign.getBytes(R.PGWConstant.UTF8))){
                log.info("验签成功");
            }else{
                log.error("验签失败");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
