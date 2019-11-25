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
import com.hyy.ifm.sys.service.impl.SqlUtils;
import com.hyy.ifm.util.ShardTableUtil;
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

@IocBean(name = "KqService")
public class KqServiceImpl extends BaseService implements KqService {

    private Log log = LogFactory.getLog(KqServiceImpl.class);

    public static final String CHARSET = "UTF-8";

    @Inject
    private KqConfigDao kqConfigDao;

    @Inject
    private CustomerDao customerDao;

    @Inject
    private ConfigDictDao configDictDao;

    @Inject
    private UserBankcardDao userBankcardDao;

    @Inject
    private KqCallbackLogDao kqCallbackLogDao;

   @Inject
    private KqOrderDao kqOrderDao;

    @Inject
    private UserOrderDao userOrderDao;

    @Inject
    private LoanOrderDao loanOrderDao;

    @Inject
    private CjConfigDao cjConfigDao;

    @Inject
    private CjCallbackLogDao cjCallbackLogDao;

    @Inject
    private CjOrderDao cjOrderDao;

    @Inject
    private XfConfigDao xfConfigDao;

    @Inject
    private XfCallbackLogDao xfCallbackLogDao;

    @Inject
    private XfOrderDao xfOrderDao;
    @Inject
    private BankDictDao bankDictDao;
    @Override
    public JSONObject kqBindCardPay(JSONObject param) {
        JSONObject detail = new JSONObject();
        String uuid = JsonUtil.getJStringAndCheck(param, "uuid", null, true, detail);
        String ip = JsonUtil.getJStringAndCheck(param, "ip", null, true, detail);
        String money = JsonUtil.getJStringAndCheck(param, "money", null, true, detail);
        if (detail.containsKey(Consts.RESULT)) {
            return detail;
        }
        List<DcUser>  userDetail = customerDao.qryCustomerByUuid(uuid);
        if (null == userDetail) {
            JsonUtil.setErrorMsg(detail, "user_login.not_login");
        }
        //DcUser userDetail = userDetails.get(0);

        List<DcDict> payType = configDictDao.qryDictByDataType("PAY_TYPE");
        if (null == payType.get(0) || StringUtils.isEmpty(payType.get(0).getItemValue().toString())) {
            JsonUtil.setErrorMsg(detail,  "系统还款类型丢失！");
            return detail;
        }

        UserOrder userOrderDaoByPrimary = userOrderDao.findByPrimary(userDetail.get(0).getId());
        Map<String, Object> arg = new HashMap<>();
        arg.put("userId", userDetail.get(0).getId());
        arg.put("type", "1");
        arg.put("outerId", userOrderDaoByPrimary.getId());
        KqOrder lastOrder = kqOrderDao.selectLastByUserIdAndTypeAndOuterId(arg);
        if (lastOrder != null) {
            if ("0".equals(lastOrder.getStatus()) || "3".equals(lastOrder.getStatus())) {
                JsonUtil.setErrorMsg(detail, "账单正在支付中, 请勿重新支付");
                return detail;
            } else if ("1".equals(lastOrder.getStatus())) {
                JsonUtil.setErrorMsg(detail, "账单已支付完成, 无需再次支付");
                return detail;
            }
        }

        List<DcUserInfo> dcUserInfo = customerDao.qryCustomerInfoById(userDetail.get(0).getId());

        //DcUserInfo dcUserInfo = dcUserInfoMapper.selectByUserId(userDetail.get("id").toString()));
        Map<String, Object> args0 = new HashMap<>();
        args0.put("userId", userDetail.get(0).getId());
        args0.put("cardNo", dcUserInfo.get(0).getBankCard());
        args0.put("authFrom", "1");
        UserBankcard userBankcard = userBankcardDao.selectByUserAndCardAndAuthFrom(args0);

        if(!userBankcard.getCardNo().replaceAll(" ", "").equals(dcUserInfo.get(0).getBankCard().replaceAll(" ", ""))) {
            JsonUtil.setErrorMsg(detail, "该卡的协议绑定关系不存在");
            return detail;
        }

        if (userOrderDaoByPrimary == null) {
            JsonUtil.setErrorMsg(detail, "订单不存在");
            return detail;
        }
        if (!userOrderDaoByPrimary.getUserId().equals(dcUserInfo.get(0).getUserId())) {
            JsonUtil.setErrorMsg(detail, "非本人订单, 不能支付");
            return detail;
        }
        KqConfig kqConfig = kqConfigDao.selectByPrimaryKey(3);
        if (kqConfig == null) {
            JsonUtil.setErrorMsg(detail,  "绑卡支付丢失！");
            return detail;
        }

        String requestNo = "KQ" + new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
        Date now = new Date();
        TransInfo transInfo=new TransInfo();

        //消费信息
        String version = "1.0";    //版本号
        String txnType = "PUR";    //交易类型
        String spFlag= "QPay02";    //特殊交易标志
        String interactiveStatus = "TR1";    //消息状态
        String payToken = userBankcard.getRemark();    //支付标记payToken
        String amount = money ;    //交易金额,以元为单位，小数点后最多两位
        String merchantId = kqConfig.getMchntcd();    //商户编号
        String terminalId = kqConfig.getTerminalId();    //终端编号
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        String entryTime =  dateName;    //商户端交易时间
        String externalRefNumber = requestNo;     //外部跟踪号
        String customerId = userDetail.get(0).getUuid();    //客户号
        String tr3Url = kqConfig.getCallbackUrl();    //tr3回调地址


        //设置消费交易的两个节点
        transInfo.setRecordeText_1("TxnMsgContent");
        transInfo.setRecordeText_2("ErrorMsgContent");

        //Tr1报文拼接
        String str1Xml = "";

        str1Xml += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        str1Xml += "<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">";
        str1Xml += "<version>"+version+"</version>";

        str1Xml += "<TxnMsgContent>";
        str1Xml += "<interactiveStatus>" + interactiveStatus + "</interactiveStatus>";
        str1Xml += "<spFlag>" + spFlag + "</spFlag>";
        str1Xml += "<txnType>" + txnType + "</txnType>";
        str1Xml += "<merchantId>" + merchantId + "</merchantId>";
        str1Xml += "<terminalId>" + terminalId + "</terminalId>";
        str1Xml += "<externalRefNumber>" + externalRefNumber + "</externalRefNumber>";
        str1Xml += "<entryTime>" + entryTime + "</entryTime>";
        str1Xml += "<amount>" + amount + "</amount>";
        str1Xml += "<customerId>" + customerId + "</customerId>";
        str1Xml += "<payToken>" + payToken + "</payToken>";
        str1Xml += "<tr3Url>"+tr3Url+"</tr3Url>";

        str1Xml += "<extMap>";
        str1Xml += "<extDate><key>phone</key><value></value></extDate>";
        str1Xml += "<extDate><key>validCode</key><value></value></extDate>";
        str1Xml += "<extDate><key>savePciFlag</key><value>0</value></extDate>";
        str1Xml += "<extDate><key>token</key><value></value></extDate>";
        str1Xml += "<extDate><key>payBatch</key><value>2</value></extDate>";
        str1Xml += "</extMap>";
        str1Xml += "</TxnMsgContent>";
        str1Xml += "</MasMessage>";
        log.info("绑卡支付  str1Xml = " + str1Xml);
        String url = kqConfig.getUrl();    //请求地址

        KqOrder kqOrder = new KqOrder();
        kqOrder.setUserId(userOrderDaoByPrimary.getUserId());
        kqOrder.setType("1");
        kqOrder.setOuterId(userOrderDaoByPrimary.getId().intValue());
        kqOrder.setRequestNo(requestNo);
        kqOrder.setUserIp(ip);
        kqOrder.setAmt(Double.valueOf(amount));
        kqOrder.setProtocolNo(payToken);
        kqOrder.setCreateTime(new Date());
        kqOrder.setStatus("0");
        try {
            Post post = new Post();
                HashMap respXml = post.sendPost(url,str1Xml,transInfo,merchantId);
                log.info("绑卡支付respXml = " + respXml);

                KqCallbackLog kqCallbackLog = new KqCallbackLog();
                kqCallbackLog.setUserId(userDetail.get(0).getId());
                kqCallbackLog.setType("3");
                kqCallbackLog.setContent(str1Xml);
                kqCallbackLog.setCreateTime(new Date());
                kqCallbackLogDao.insertSelective(kqCallbackLog);

                if(respXml==null)
                {
                   log.info("绑卡支付读取内容出错");
                } else
                {
                    //如果TR2获取的应答码responseCode的值为00时，成功
                    if("00".equals((String)respXml.get("responseCode")))
                    {
                       log.info("交易成功");
                       detail.put("code","0");
                        kqOrder.setStatus("1");
                        kqOrder.setPayDate(now);
                        kqOrder.setFuionOrderNo(respXml.get("refNumber").toString());
                        kqOrderDao.insertSelective(kqOrder);
                        // 更新订单状态
                        if(userOrderDaoByPrimary.getApplyAmt() <= (Double.parseDouble(respXml.get("amount").toString())+ userOrderDaoByPrimary.getPayAmt())){
                            // 更新订单状态
                            userOrderDaoByPrimary.setStatus("1");
                            userOrderDaoByPrimary.setPayTime(now);
                            userOrderDaoByPrimary.setPayAmt(Double.parseDouble(ZfSignUtil.sum(Double.parseDouble(respXml.get("amount").toString()),userOrderDaoByPrimary.getPayAmt())));
                            userOrderDaoByPrimary.setRepayAmt(0.00);
                            userOrderDao.updateSelective(userOrderDaoByPrimary);
                            detail.put("code","0");//交易结束
                            return detail;
                        }else{
                            userOrderDaoByPrimary.setStatus("0");
                            userOrderDaoByPrimary.setPayTime(now);
                            userOrderDaoByPrimary.setRepayAmt(Double.parseDouble(ZfSignUtil.sub(userOrderDaoByPrimary.getApplyAmt(),
                                    Double.parseDouble(ZfSignUtil.sum(Double.parseDouble(respXml.get("amount").toString()),userOrderDaoByPrimary.getPayAmt())))));
                            userOrderDaoByPrimary.setPayAmt(Double.parseDouble(ZfSignUtil.sum(Double.parseDouble(respXml.get("amount").toString()),userOrderDaoByPrimary.getPayAmt()))) ;
                            userOrderDao.updateSelective(userOrderDaoByPrimary);
                            detail.put("code","-1");//进入下一个循环
//                            detail.put("money",respXml.get("amount").toString());
                            detail.put("money",userOrderDao.findByPrimary(userOrderDaoByPrimary.getId().intValue()).getRepayAmt());
                            return detail;
                        }
                    }else if ("09".equals((String)respXml.get("responseCode"))) {
                        kqOrder.setStatus("0");
                        kqOrderDao.insertSelective(kqOrder);
                        detail.put("code","0");
                        return detail;
                    } else {
                        kqOrder.setStatus("2");
                        kqOrder.setFailReason(respXml.get("responseTextMessage").toString());
                        JsonUtil.setErrorMsg(detail, respXml.get("responseTextMessage").toString());
                        kqOrderDao.insertSelective(kqOrder);
//                        detail.put("money",respXml.get("amount").toString());
                        detail.put("money",userOrderDao.findByPrimary(userOrderDaoByPrimary.getId().intValue()).getRepayAmt());
                        detail.put("code","2");//失败
                        return detail;
                    }
                  }
                } catch (Exception e) {
                    kqOrder.setStatus("3");
                    kqOrderDao.insertSelective(kqOrder);
                    e.printStackTrace();
                }
        return detail;
    }

    @Override
    public JSONObject cjBindCardPay(JSONObject param) {
        JSONObject detail = new JSONObject();
        String uuid = JsonUtil.getJStringAndCheck(param, "uuid", null, true, detail);
        String ip = JsonUtil.getJStringAndCheck(param, "ip", null, true, detail);
        String money = JsonUtil.getJStringAndCheck(param, "money", null, true, detail);
        if (detail.containsKey(Consts.RESULT)) {
            return detail;
        }
        List<DcUser> userDetail = customerDao.qryCustomerByUuid(uuid);
        if (null == userDetail) {
            JsonUtil.setErrorMsg(detail, "user_login.not_login");
        }
        //DcUser userDetail = userDetails.get(0);

        List<DcDict> payType = configDictDao.qryDictByDataType("PAY_TYPE");
        if (null == payType.get(0) || StringUtils.isEmpty(payType.get(0).getItemValue().toString())) {
            JsonUtil.setErrorMsg(detail, "系统还款类型丢失！");
            return detail;
        }

        UserOrder userOrderDaoByPrimary = userOrderDao.findByPrimary(userDetail.get(0).getId());
        Map<String, Object> arg = new HashMap<>();
        arg.put("userId", userDetail.get(0).getId());
        arg.put("type", "1");
        arg.put("outerId", userOrderDaoByPrimary.getId());
        CjOrder lastOrder = cjOrderDao.selectLastByUserIdAndTypeAndOuterId(arg);
        if (lastOrder != null) {
            if ("0".equals(lastOrder.getStatus()) || "3".equals(lastOrder.getStatus())) {
                JsonUtil.setErrorMsg(detail, "账单正在支付中, 请勿重新支付");
                return detail;
            } else if ("1".equals(lastOrder.getStatus())) {
                JsonUtil.setErrorMsg(detail, "账单已支付完成, 无需再次支付");
                return detail;
            }
        }

        List<DcUserInfo> dcUserInfo = customerDao.qryCustomerInfoById(userDetail.get(0).getId());

        //DcUserInfo dcUserInfo = dcUserInfoMapper.selectByUserId(userDetail.get("id").toString()));
        Map<String, Object> args0 = new HashMap<>();
        args0.put("userId", userDetail.get(0).getId());
        args0.put("cardNo", dcUserInfo.get(0).getBankCard());
        args0.put("authFrom", "2");
        UserBankcard userBankcard = userBankcardDao.selectByUserAndCardAndAuthFrom(args0);

        if (!userBankcard.getCardNo().replaceAll(" ", "").equals(dcUserInfo.get(0).getBankCard().replaceAll(" ", ""))) {
            JsonUtil.setErrorMsg(detail, "该卡的协议绑定关系不存在");
            return detail;
        }

        if (userOrderDaoByPrimary == null) {
            JsonUtil.setErrorMsg(detail, "订单不存在");
            return detail;
        }
        if (!userOrderDaoByPrimary.getUserId().equals(dcUserInfo.get(0).getUserId())) {
            JsonUtil.setErrorMsg(detail, "非本人订单, 不能支付");
            return detail;
        }
        CjConfig cjConfig = cjConfigDao.selectByPrimaryKey(3);
        if (cjConfig == null) {
            JsonUtil.setErrorMsg(detail, "绑卡支付丢失！");
            return detail;
        }
        log.info(dcUserInfo.get(0).getBankCard());
        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap.put("Version", "1.0");
        origMap.put("PartnerId", cjConfig.getPartnerId());//生产环境测试商户号
        origMap.put("InputCharset", CHARSET);// 字符集
        DateFormat TradeDate = new SimpleDateFormat("yyyyMMdd");
        DateFormat TradeTime = new SimpleDateFormat("HHmmss");
        Date date = new Date();
        origMap.put("TradeDate", TradeDate.format(date));// 商户请求时间
        origMap.put("TradeTime", TradeTime.format(date));// 商户请求时间
        origMap.put("Memo", null);
        origMap.put("Service", "nmg_biz_api_quick_payment");// 支付的接口名
        // 2.2 业务参数
        String trxId = Long.toString(System.currentTimeMillis());
        origMap.put("TrxId", trxId);// 订单号
        origMap.put("OrdrName", "畅捷支付");// 商品名称
        origMap.put("MerUserId", userDetail.get(0).getUuid());// 用户标识（测试时需要替换一个新的meruserid）
        origMap.put("SellerId", cjConfig.getPartnerId());// 子账户号
        origMap.put("ExpiredTime", "40m");// 订单有效期
        origMap.put("CardBegin", dcUserInfo.get(0).getBankCard().substring(0,6));// 卡号前6位
        origMap.put("CardEnd", dcUserInfo.get(0).getBankCard().substring(dcUserInfo.get(0).getBankCard().length()-4));// 卡号后4位
        origMap.put("TrxAmt", money);// 交易金额
        origMap.put("TradeType", "11");// 交易类型
        origMap.put("SmsFlag", "0");//短信发送标识

        CjOrder cjOrder = new CjOrder();
        cjOrder.setUserId(userOrderDaoByPrimary.getUserId());
        cjOrder.setType("1");
        cjOrder.setOuterId(userOrderDaoByPrimary.getId().intValue());
        cjOrder.setRequestNo(trxId);
        cjOrder.setUserIp(ip);
        cjOrder.setAmt(Double.valueOf(money));
        cjOrder.setCreateTime(new Date());
        cjOrder.setStatus("0");
        try {
            String result = ChanpayGateway.gatewayPost(cjConfig.getUrl(),origMap, CHARSET, cjConfig.getPrivateKey());
            log.info("绑卡支付result = " + result);

            CjCallbackLog cjCallbackLog = new CjCallbackLog();
            cjCallbackLog.setUserId(userDetail.get(0).getId());
            cjCallbackLog.setType("3");
            cjCallbackLog.setContent(result);
            cjCallbackLog.setCreateTime(new Date());
            cjCallbackLogDao.insertSelective(cjCallbackLog);

            if (result == null) {
                log.info("绑卡支付读取内容出错");
            } else {
                JSONObject json = JSONObject.parseObject(result);
                if ("S".equals(json.getString("Status"))) {
                    log.info("交易成功");

                    cjOrder.setStatus("1");
                    cjOrder.setPayDate(date);
                    cjOrder.setOrderTrxid(json.getString("OrderTrxid"));
                    cjOrderDao.insertSelective(cjOrder);
                    if (userOrderDaoByPrimary.getApplyAmt() <= (Double.parseDouble(money) + userOrderDaoByPrimary.getPayAmt())) {
                        // 更新订单状态
                        userOrderDaoByPrimary.setStatus("1");
                        userOrderDaoByPrimary.setPayTime(date);
                        userOrderDaoByPrimary.setPayAmt(Double.parseDouble(ZfSignUtil.sum(Double.parseDouble(money), userOrderDaoByPrimary.getPayAmt())));
                        userOrderDaoByPrimary.setRepayAmt(0.00);
                        userOrderDao.updateSelective(userOrderDaoByPrimary);
                        detail.put("code", "0");//交易结束
                        return detail;
                    } else {
                        userOrderDaoByPrimary.setStatus("0");
                        userOrderDaoByPrimary.setPayTime(date);
                        userOrderDaoByPrimary.setRepayAmt(Double.parseDouble(ZfSignUtil.sub(userOrderDaoByPrimary.getApplyAmt(),
                                Double.parseDouble(ZfSignUtil.sum(Double.parseDouble(money), userOrderDaoByPrimary.getPayAmt())))));
                        userOrderDaoByPrimary.setPayAmt(Double.parseDouble(ZfSignUtil.sum(Double.parseDouble(money), userOrderDaoByPrimary.getPayAmt())));
                        userOrderDao.updateSelective(userOrderDaoByPrimary);
                        detail.put("code", "-1");//进入下一个循环
                        detail.put("money", money);
                        return detail;
                    }
                } else if ("P".equals(json.getString("Status"))) {
                    cjOrder.setStatus("0");
                    cjOrderDao.insertSelective(cjOrder);
                    detail.put("code", "0");//处理中
                    return detail;
                } else {
                    cjOrder.setStatus("2");
                    cjOrder.setFailReason(json.getString("RetMsg"));
                    JsonUtil.setErrorMsg(detail, json.getString("RetMsg"));
                    cjOrderDao.insertSelective(cjOrder);
                    detail.put("money", money);
                    detail.put("code", "2");//失败
                    return detail;
                }
            }
        } catch (Exception e) {
            cjOrder.setStatus("3");
            cjOrderDao.insertSelective(cjOrder);
            e.printStackTrace();
        }
        return detail;
    }

    @Override
    public JSONObject xfBindCardPay(JSONObject param) {
        JSONObject detail = new JSONObject();
        String uuid = JsonUtil.getJStringAndCheck(param, "uuid", null, true, detail);
        String ip = JsonUtil.getJStringAndCheck(param, "ip", null, true, detail);
        String money = JsonUtil.getJStringAndCheck(param, "money", null, true, detail);
        if (detail.containsKey(Consts.RESULT)) {
            return detail;
        }
        List<DcUser> userDetail = customerDao.qryCustomerByUuid(uuid);
        if (null == userDetail) {
            JsonUtil.setErrorMsg(detail, "user_login.not_login");
        }
        //DcUser userDetail = userDetails.get(0);

        List<DcDict> payType = configDictDao.qryDictByDataType("PAY_TYPE");
        if (null == payType.get(0) || StringUtils.isEmpty(payType.get(0).getItemValue().toString())) {
            JsonUtil.setErrorMsg(detail, "系统还款类型丢失！");
            return detail;
        }
        // 分表
        String tableName1 = ShardTableUtil.generateTableNameById("xf_order", userDetail.get(0).getId(), 3);
        int countTable1 = xfOrderDao.countTable(tableName1);
        if (countTable1 == 0) {
            xfOrderDao.createTable(tableName1);
        }
        UserOrder userOrderDaoByPrimary = userOrderDao.findByPrimary(userDetail.get(0).getId());
        if (Double.parseDouble(money)>Double.parseDouble(String.valueOf(userOrderDaoByPrimary.getRepayAmt()))){
            money=String.valueOf(userOrderDaoByPrimary.getRepayAmt());
        }
        Map<String, Object> arg = new HashMap<>();
        arg.put("userId", userDetail.get(0).getId());
        arg.put("type", "1");
        arg.put("outerId", userOrderDaoByPrimary.getId());
        XfOrder lastOrder = xfOrderDao.selectLastByUserIdAndTypeAndOuterId(tableName1,arg);
//        if (lastOrder != null) {
//            if ("0".equals(lastOrder.getStatus()) || "3".equals(lastOrder.getStatus())) {
//                JsonUtil.setErrorMsg(detail, "账单正在支付中, 请勿重新支付");
//                return detail;
//            } else if ("1".equals(lastOrder.getStatus())) {
//                JsonUtil.setErrorMsg(detail, "账单已支付完成, 无需再次支付");
//                return detail;
//            }
//        }

        List<DcUserInfo> dcUserInfo = customerDao.qryCustomerInfoById(userDetail.get(0).getId());

        //DcUserInfo dcUserInfo = dcUserInfoMapper.selectByUserId(userDetail.get("id").toString()));
        Map<String, Object> args0 = new HashMap<>();
        args0.put("userId", userDetail.get(0).getId());
        args0.put("cardNo", dcUserInfo.get(0).getBankCard());
        args0.put("authFrom", "3");

        UserBankcard userBankcard = userBankcardDao.selectByUserAndCardAndAuthFrom(args0);



        if (userOrderDaoByPrimary == null) {
            JsonUtil.setErrorMsg(detail, "订单不存在");
            return detail;
        }
        if (!userOrderDaoByPrimary.getUserId().equals(dcUserInfo.get(0).getUserId())) {
            JsonUtil.setErrorMsg(detail, "非本人订单, 不能支付");
            return detail;
        }
        XfConfig xfConfig = xfConfigDao.selectByPrimaryKey(3);
        if (xfConfig == null) {
            JsonUtil.setErrorMsg(detail,  "绑卡支付丢失！");
            return detail;
        }


        Map<String, String> map = new HashMap<String, String>();
        String outTradeNo = ChanPayUtil.generateOutTradeNo();
        // 2.1 基本参数
        map.put("version", "1.0.1");// 版本
        map.put("merchantId",xfConfig.getPartnerId());  //商户号
        map.put("payOrderId", outTradeNo); //订单号
        Date date = new Date();
        map.put("reqDate", new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(date));//请求时间
        map.put("amount", money);// 金额
        String protocolId = userBankcard.getRemark();
        map.put("protocolId", protocolId);// 签约协议号
        map.put("name", dcUserInfo.get(0).getUserName());// 客户姓名
        map.put("account", dcUserInfo.get(0).getBankCard()); // 银行账号
        map.put("payType", "140002"); // 支付场景
        map.put("orderDesc", "1#1#支付1支付^"+money+"^1"); // 订单详情
        map.put("subMercId", "");//二级商户编码
        map.put("subMercName", "");//二级商户简称
        map.put("cvv2", "");//信用卡背面签名栏的后三位数字
        map.put("validDate", "");//信用卡有效期
        //加签
        String to_sign = MerchantSignAndVerify.createLinkString(map);
        //调用CFCA方法得到加签 sign
        String sign = new String(MerchantSignAndVerify.sign(to_sign, xfConfig.getPartnerId()));
        //加签sign放入map
        map.put("sign", sign);
        //拼装成xml请求报文，并发送post请求,
        String xmlString = ECTXmlUtil.mapToXml(map, ECTXmlUtil.CPREQ_QPREQ);
        //log.info("发出报文：" + xmlString);
        String responseString = null;
        try {
            responseString = HttpClientUtil.postToServerByXml(xmlString, xfConfig.getUrl());
            //log.info("返回报文：" + responseString);
            //验签
            //xml解析成map，map中包含了 <CSReq>标签内的元素
            Map<String, String> resultMap = ECTXmlUtil.xmlToMap(responseString);
            String result_sign = resultMap.get("sign");
            String to_verify = MerchantSignAndVerify.createLinkString(resultMap);
            if(MerchantSignAndVerify.verify(to_verify.getBytes(R.PGWConstant.UTF8), result_sign.getBytes(R.PGWConstant.UTF8))){
                log.info("验签成功");
            }else{
                log.error("验签失败");
            }
        } catch (UnsupportedEncodingException e) {
            log.error("报文异常！");
            e.printStackTrace();
        }
        XfOrder xfOrder = new XfOrder();
        xfOrder.setUserId(userOrderDaoByPrimary.getUserId());
        xfOrder.setType("1");
        xfOrder.setOuterId(userOrderDaoByPrimary.getId().intValue());
        xfOrder.setRequestNo(outTradeNo);
        xfOrder.setUserIp(ip);
        xfOrder.setAmt(Double.valueOf(money));
        xfOrder.setCreateTime(new Date());
        xfOrder.setStatus("0");

        // 分表
        String tableName = ShardTableUtil.generateTableNameById("xf_callback_log", userDetail.get(0).getId(), 3);
        int countTable = xfCallbackLogDao.countTable(tableName);
        if (countTable == 0) {
            xfCallbackLogDao.createTable(tableName);
        }
        XfCallbackLog xfCallbackLog = new XfCallbackLog();
        xfCallbackLog.setUserId(userDetail.get(0).getId());
        xfCallbackLog.setType("3");
        xfCallbackLog.setContent(responseString);
        xfCallbackLog.setCreateTime(new Date());
        xfCallbackLogDao.saveShard(tableName, xfCallbackLog);

        if (responseString == null) {
            log.info("绑卡支付读取内容出错");
        } else {

            Map<String, String> resultMap = ECTXmlUtil.xmlToMap(responseString);
            if(resultMap.get("retFlag").equals("T")&&resultMap.get("resultCode").equals("0032")){
                log.info("交易成功");

                xfOrder.setStatus("1");
                xfOrder.setPayDate(date);
                xfOrder.setOrderTrxid(resultMap.get("payOrderId"));
                xfOrderDao.saveShard(tableName1,xfOrder);
                xfOrderDao.insertSelective(xfOrder);

                if (userOrderDaoByPrimary.getApplyAmt() <= (Double.parseDouble(money) + userOrderDaoByPrimary.getPayAmt())) {
                    // 更新订单状态
                    userOrderDaoByPrimary.setStatus("1");
                    userOrderDaoByPrimary.setPayTime(date);
                    userOrderDaoByPrimary.setPayAmt(Double.parseDouble(ZfSignUtil.sum(Double.parseDouble(money), userOrderDaoByPrimary.getPayAmt())));
                    userOrderDaoByPrimary.setRepayAmt(0.00);
                    userOrderDao.updateSelective(userOrderDaoByPrimary);
                    detail.put("code", "0");//交易结束
                    return detail;
                } else {
                    userOrderDaoByPrimary.setStatus("0");
                    userOrderDaoByPrimary.setPayTime(date);
                    userOrderDaoByPrimary.setRepayAmt(Double.parseDouble(ZfSignUtil.sub(userOrderDaoByPrimary.getApplyAmt(),
                            Double.parseDouble(ZfSignUtil.sum(Double.parseDouble(money), userOrderDaoByPrimary.getPayAmt())))));
                    userOrderDaoByPrimary.setPayAmt(Double.parseDouble(ZfSignUtil.sum(Double.parseDouble(money), userOrderDaoByPrimary.getPayAmt())));
                    userOrderDao.updateSelective(userOrderDaoByPrimary);
                    detail.put("code", "-1");//进入下一个循环
                    detail.put("money", money);
                    return detail;
                }
            } else if (resultMap.get("retFlag").equals("P")) {
                xfOrder.setStatus("0");
                xfOrderDao.saveShard(tableName1, xfOrder);
                detail.put("code", "0");//处理中
                return detail;
            } else {
                xfOrder.setStatus("2");
                xfOrder.setFailReason(resultMap.get("resultMsg"));
                JsonUtil.setErrorMsg(detail, resultMap.get("resultMsg"));
                xfOrderDao.saveShard(tableName1, xfOrder);
                detail.put("money", money);
                detail.put("code", "2");//失败
                return detail;
            }
        }
        return detail;
    }

    @Override
    public CallBackBean thirdPay(String json) {

        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);

        String money = cnds.getRows().getMoney();

        int id = Integer.parseInt(cnds.getRows().getId());

        List<DcUser>  userList = customerDao.qryUserById(id);

        List<DcUserInfo> dcUserInfoList = customerDao.qryCustomerInfoById(id);

        if (null == dcUserInfoList) {
            return this.joinformateJson(json, "用户不存在", "");
        }

        if (null == userList) {
            return this.joinformateJson(json, "用户不存在", "");
        }



        Map<String, Object> args1 = new HashMap<>();
        args1.put("userId", dcUserInfoList.get(0).getUserId());
        //args1.put("cardNo", dcUserInfoList.get(0).getBankCard());
//        args1.put("authFrom", "1");
        UserBankcard userBankcard = userBankcardDao.selectByUserId(args1);
        if(null == userBankcard){
            return this.joinformateJson(json, "该用户绑卡关系不存在", "");
        }
        if(userBankcard.getAuthFrom().equals("1")){
            Map<String, Object> args0 = new HashMap<>();
            args0.put("userId", dcUserInfoList.get(0).getUserId());
            args0.put("cardNo", dcUserInfoList.get(0).getBankCard());
            args0.put("authFrom", "1");

            DcUserInfo dcUserInfo = dcUserInfoList.get(0);

            DcUser dcUser = userList.get(0);

            UserOrder userOrder = userOrderDao.findByPrimary(dcUser.getId());

            if(!userOrder.getUserAuth().equals("1") && !userOrder.getUserAuth().equals("4")){
                return this.joinformateJson(json, "该用户目前状态不属于退款状态", "");
            }

            List<Record> order = loanOrderDao.qryLoanOrderByOrderId(userOrder.getId());

            if (!CollectionUtils.isEmpty(order)) {

                if ("0".equals(order.get(0).get("status"))) {
                    return this.joinformateJson(json, "当前正在放款中, 请稍后再试", "");
                } else if ("1".equals(order.get(0).get("status")) || "3".equals(order.get(0).get("status"))) {
                    return this.joinformateJson(json, "当前账单已放款, 不能继续放款", "");
                }
            }

            String uuid = "MYHBANKNOCHECK" + String.valueOf(System.currentTimeMillis());
            LoanOrder lo = new LoanOrder();
            lo.setId(uuid);
            lo.setUserName(dcUserInfo.getUserName());
            lo.setBankNo(userOrder./*getBankNo()*/getBankNo().replaceAll(" ", ""));
            lo.setBank(userOrder.getBank());

            lo.setTotalMoney(money);
            lo.setCreateTime(new Date());
            lo.setOrderId(userOrder.getId().intValue());
            lo.setUserId(userOrder.getUserId().toString());
            lo.setIdentityNo(dcUserInfo.getIdCard());
            lo.setMobileNo(dcUser.getMobile());
            lo.setPayFrom("1");

            KqConfig kqConfig = kqConfigDao.selectByPrimaryKey(5);
            if (kqConfig == null) {
                return this.joinformateJson(json, "代发配置丢失", "");
            }

            String pkiMsg ;
            String sealMsg ;
            try {
                pkiMsg = test_genPKIMsg(uuid,money,dcUserInfo,dcUser,kqConfig.getMchntcd());
                sealMsg = test_invokeCSSCollection(pkiMsg,kqConfig.getUrl());
                String rtnString = test_unsealMsg(sealMsg,kqConfig.getMchntcd());

                KqCallbackLog kqCallbackLog = new KqCallbackLog();
                kqCallbackLog.setUserId(dcUser.getId());
                kqCallbackLog.setType("6");
                kqCallbackLog.setContent("代付请求"+rtnString);
                kqCallbackLog.setCreateTime(new Date());
                kqCallbackLogDao.insertSelective(kqCallbackLog);

                if(rtnString.startsWith("0000")){
                    lo.setStatus("0");
                    loanOrderDao.insertProductClassify(lo);
                    userOrder.setUserAuth("2");
                    userOrderDao.updateSelective(userOrder);
                    return this.joinformateJson(json, "退款处理中，请稍后刷新页面查询状态", "");
                } else{
                    lo.setStatus("2");
                    loanOrderDao.insertProductClassify(lo);
                    userOrder.setUserAuth("4");
                    userOrderDao.updateSelective(userOrder);
                    return this.joinformateJson(json, "退款失败", "");
                }

            } catch (Exception e) {
                e.printStackTrace();
                return this.joinformateJson(json, "代发失败", "");
            }
        }else if(userBankcard.getAuthFrom().equals("2")){

            DcUserInfo dcUserInfo = dcUserInfoList.get(0);

            DcUser dcUser = userList.get(0);

            UserOrder userOrder = userOrderDao.findByPrimary(dcUser.getId());

            if(!userOrder.getUserAuth().equals("1") && !userOrder.getUserAuth().equals("4")){
                return this.joinformateJson(json, "该用户目前状态不属于退款状态", "");
            }

            List<Record> order = loanOrderDao.qryLoanOrderByOrderId(userOrder.getId());

            if (!CollectionUtils.isEmpty(order)) {

                if ("0".equals(order.get(0).get("status"))) {
                    return this.joinformateJson(json, "当前正在放款中, 请稍后再试", "");
                } else if ("1".equals(order.get(0).get("status")) || "3".equals(order.get(0).get("status"))) {
                    return this.joinformateJson(json, "当前账单已放款, 不能继续放款", "");
                }
            }

            String outTradeNo = ChanPayUtil.generateOutTradeNo();
            LoanOrder lo = new LoanOrder();
            lo.setId(outTradeNo);
            lo.setUserName(dcUserInfo.getUserName());
            lo.setBankNo(userOrder.getBankNo().replaceAll(" ", ""));
            lo.setBank(userOrder.getBank());

            lo.setTotalMoney(money);
            lo.setCreateTime(new Date());
            lo.setOrderId(userOrder.getId().intValue());
            lo.setUserId(userOrder.getUserId().toString());
            lo.setIdentityNo(dcUserInfo.getIdCard());
            lo.setMobileNo(dcUser.getMobile());
            lo.setPayFrom("2");

            CjConfig cjConfig = cjConfigDao.selectByPrimaryKey(5);
            if (cjConfig == null) {
                return this.joinformateJson(json, "代发配置丢失", "");
            }
            try {
                String rtnString = send(cjConfig,money,dcUserInfo,outTradeNo);

                CjCallbackLog cjCallbackLog = new CjCallbackLog();
                cjCallbackLog.setUserId(dcUser.getId());
                cjCallbackLog.setType("6");
                cjCallbackLog.setContent("代付请求"+rtnString);
                cjCallbackLog.setCreateTime(new Date());
                cjCallbackLogDao.insertSelective(cjCallbackLog);

                JSONObject jo= JSONObject.parseObject(rtnString);
                if(jo.getString("PlatformRetCode").equals("0000")){
                    lo.setStatus("0");
                    loanOrderDao.insertProductClassify(lo);
                    userOrder.setUserAuth("2");
                    userOrderDao.updateSelective(userOrder);
                    return this.joinformateJson(json, "退款处理中，请稍后刷新页面查询状态", "");
                } else{
                    lo.setStatus("2");
                    loanOrderDao.insertProductClassify(lo);
                    userOrder.setUserAuth("4");
                    userOrderDao.updateSelective(userOrder);
                    if(jo.containsKey("PlatformErrorMessage")){
                        if (!("").equals(jo.getString("PlatformErrorMessage"))){
                            return this.joinformateJson(json, jo.getString("PlatformErrorMessage"), "");
                        }
                    }
                    return this.joinformateJson(json, "退款失败", "");
                }

            } catch (Exception e) {
                e.printStackTrace();
                return this.joinformateJson(json, "代发失败", "");
            }
        }else if(userBankcard.getAuthFrom().equals("3")){
            DcUserInfo dcUserInfo = dcUserInfoList.get(0);

            DcUser dcUser = userList.get(0);

            UserOrder userOrder = userOrderDao.findByPrimary(dcUser.getId());

            if("0".equals(cnds.getRows().getType())){
                if(!userOrder.getUserAuth().equals("1") && !userOrder.getUserAuth().equals("4")){
                    return this.joinformateJson(json, "该用户目前状态不属于退款状态", "");
                }
            }
            List<Record> order = loanOrderDao.qryLoanOrderByOrderId(userOrder.getId());

            if (!CollectionUtils.isEmpty(order)) {

                if ("0".equals(order.get(0).get("status"))) {
                    return this.joinformateJson(json, "当前正在放款中, 请稍后再试", "");
                } else if ("1".equals(order.get(0).get("status")) || "3".equals(order.get(0).get("status"))) {
                    return this.joinformateJson(json, "当前账单已放款, 不能继续放款", "");
                }
            }
            String outTradeNo = ChanPayUtil.generateOutTradeNo();
            LoanOrder lo = new LoanOrder();
            lo.setId(outTradeNo);
            lo.setUserName(dcUserInfo.getUserName());
            lo.setBankNo(userOrder./*getBankNo()*/getBankNo().replaceAll(" ", ""));
            lo.setBank(userOrder.getBank());

            lo.setTotalMoney(money);
            lo.setCreateTime(new Date());
            lo.setOrderId(userOrder.getId().intValue());
            lo.setUserId(userOrder.getUserId().toString());
            lo.setIdentityNo(dcUserInfo.getIdCard());
            lo.setMobileNo(dcUser.getMobile());
            lo.setPayFrom("3");
            XfConfig xfConfig = xfConfigDao.selectByPrimaryKey(5);
            if (xfConfig == null) {
                return this.joinformateJson(json, "代发配置丢失", "");
            }
            try {
                String rtnString = xfsend(xfConfig,money,dcUserInfo,outTradeNo);

                // 分表
                String tableName = ShardTableUtil.generateTableNameById("xf_callback_log", dcUser.getId(), 3);
                int countTable = xfCallbackLogDao.countTable(tableName);
                if (countTable == 0) {
                    xfCallbackLogDao.createTable(tableName);
                }
                XfCallbackLog xfCallbackLog = new XfCallbackLog();
                xfCallbackLog.setUserId(dcUser.getId());
                xfCallbackLog.setType("6");
                xfCallbackLog.setContent("代付请求"+rtnString);
                xfCallbackLog.setCreateTime(new Date());
                xfCallbackLogDao.saveShard(tableName, xfCallbackLog);

                Map<String, String> resultMap = ECTXmlUtil.xmlToMap(rtnString);
                if(resultMap.get("retFlag").equals("T")){
                    lo.setStatus("1");
                    loanOrderDao.insertProductClassify(lo);
                    userOrder.setStatus("1");
                    userOrder.setUserAuth("3");
                    userOrder.setRebackAmt(Double.parseDouble(money));
                    userOrderDao.updateSelective(userOrder);
                    return this.joinformateJson(json, "退款成功", "");
                } else{
                    lo.setStatus("2");
                    loanOrderDao.insertProductClassify(lo);
                    userOrder.setStatus("1");
                    userOrder.setUserAuth("4");
                    userOrderDao.updateSelective(userOrder);
                    return this.joinformateJson(json, "退款失败", "");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return this.joinformateJson(json, "代发失败", "");
            }
        }
        return this.joinformateJson(json, "代发失败", "");
    }

    public String send(CjConfig cjConfig,String money,DcUserInfo dcUserInfo,String outTradeNo) {
        Map<String, String> map = new HashMap<String, String>();
        // 2.1 基本参数
        map.put("Service", "cjt_dsf");// 接口名
        map.put("Version", "1.0");
        map.put("PartnerId", cjConfig.getPartnerId()); //生产环境测试商户号
        map.put("TradeDate", new SimpleDateFormat("yyyyMMdd").format(new Date()));
        map.put("TradeTime", new SimpleDateFormat("HHmmss").format(new Date()));
        map.put("InputCharset", CHARSET);// 字符集
        map.put("Memo", "");// 备注
        map.put("TransCode", "T10000"); // 交易码
        map.put("OutTradeNo", outTradeNo); // 商户网站唯一订单号
        map.put("BusinessType", "0"); // 业务类型：0对私 1对公
        map.put("BankCommonName", dcUserInfo.getBankOpen()); // 通用银行名称
        map.put("AcctNo", ChanPayUtil.encrypt(dcUserInfo.getBankCard(), cjConfig.getPublicKey(), CHARSET)); // 对手人账号(此处需要用真实的账号信息)
        map.put("AcctName", ChanPayUtil.encrypt(dcUserInfo.getUserName(), cjConfig.getPublicKey(), CHARSET)); // 对手人账户名称
        map.put("TransAmt", money);
        map.put("CorpPushUrl", cjConfig.getCallbackUrl());

        String result = ChanPayUtil.sendPost(map, CHARSET,
                cjConfig.getPrivateKey(),cjConfig.getUrl());
        return result;
    }
    public String xfsend(XfConfig xfConfig,String money,DcUserInfo dcUserInfo,String outTradeNo) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        // 2.1 基本参数
        map.put("version", "1.0.1");// 版本
        map.put("merchantId",xfConfig.getPartnerId());  //商户号
        map.put("orderId", outTradeNo); //订单号
        map.put("reqDate", new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));//请求时间
        map.put("amount", money);// 金额
        map.put("name", dcUserInfo.getUserName());// 收款人姓名
        String payBankCode = JSONObject.parseObject(bankDictDao.selectByItemValue(dcUserInfo.getBankOpen()).getString("ITEM_KEY")).getString("xfPay");
        map.put("organCode", payBankCode); // 收款人机构号
        map.put("account", dcUserInfo.getBankCard()); // 收款人账号
        map.put("accountType", "90"); // 收款人账户类型 90 ：储蓄卡
        map.put("idType", "10"); // 收款人证件类型   10：身份证
        map.put("idCode", dcUserInfo.getIdCard()); // 收款人身份证
        map.put("orderBizzType", "130002"); // 业务类型分类
        map.put("subMercId", "");//二级商户编码
        map.put("subMercName", "");//二级商户简称
        //加签
        String to_sign = MerchantSignAndVerify.createLinkString(map);
        //调用CFCA方法得到加签 sign
        String sign = new String(MerchantSignAndVerify.sign(to_sign, xfConfig.getPartnerId()));
        //加签sign放入map
        map.put("sign", sign);
        //拼装成xml请求报文，并发送post请求,
        //这里只是给出了一种写法，开发者可以自由编写只要请求报文符合接口文档的规范
        String xmlString = ECTXmlUtil.mapToXml(map, ECTXmlUtil.CPREQ_SPREQ);
        log.info("发出报文：" + xmlString);
        String responseString = HttpClientUtil.postToServerByXml(xmlString, xfConfig.getUrl());
        log.info("返回报文：" + responseString);

        //验签
        //xml解析成map，map中包含了 <CSReq>标签内的元素
        Map<String, String> resultMap = ECTXmlUtil.xmlToMap(responseString);
        String result_sign = resultMap.get("sign");
        String to_verify = MerchantSignAndVerify.createLinkString(resultMap);
        if(MerchantSignAndVerify.verify(to_verify.getBytes(R.PGWConstant.UTF8), result_sign.getBytes(R.PGWConstant.UTF8))){
            log.info("验签成功");
        }else{
            log.error("验签失败");
        }
        return responseString;
    }

    //接口版本
    private static String VERSION = "1.0";
    //字符编码
    private static String encoding = "UTF-8";
    //策略编码，固定值 F41
    private static String fetureCode = "F41";

    public static String test_unsealMsg(String msg,String membercode) throws Exception {
        System.out.println("加密返回报文 = " + msg);
        Pay2bankResponse response = CCSUtil.converyToJavaBean(msg, Pay2bankResponse.class);
        //response.getResponseBody().getErrorCode().equals("0000");
        if("0000".equals(response.getResponseBody().getErrorCode())){
            SealedData sealedData = new SealedData();
            sealedData.setOriginalData(response.getResponseBody().getSealDataType().getOriginalData()==null?null: PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getOriginalData()));
            sealedData.setSignedData(response.getResponseBody().getSealDataType().getSignedData()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getSignedData()));
            sealedData.setEncryptedData(response.getResponseBody().getSealDataType().getEncryptedData()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getEncryptedData()));
            sealedData.setDigitalEnvelope(response.getResponseBody().getSealDataType().getDigitalEnvelope()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getDigitalEnvelope()));
            Mpf mpf = genMpf(fetureCode , membercode);
            UnsealedData unsealedData = null;
            try {
                ICryptoService service = CryptoServiceFactory.createCryptoService();
                unsealedData = service.unseal(mpf, sealedData);
            } catch (CryptoException e) {
                System.out.println(e);
            }
            byte[] decryptedData = unsealedData.getDecryptedData();
            if (null != decryptedData) {
                String rtnString = "0000" + PKIUtil.byte2UTF8String(decryptedData);
                System.out.println("解密后返回报文 = " + rtnString);
                return rtnString;
            } else {
                String  rtnString = "0000" + PKIUtil.byte2UTF8String(sealedData.getOriginalData());
                System.out.println("解密后返回报文 = " + rtnString);
                return rtnString;
            }
        }else {
            return  response.getResponseBody().getErrorCode() +  response.getResponseBody().getErrorMsg();
        }
    }

    public static String test_genPKIMsg(String uuid,String money,DcUserInfo dcUserInfo,DcUser dcUser,String membercode) {
        //构建一个订单对象
        Pay2bankOrder orderDto = CCSUtil.genOrder(uuid,money,dcUserInfo,dcUser);
        String orderXml = CCSUtil.convertToXml(orderDto, encoding);
        System.out.println("请求明文报文 = " + orderXml);
        //获取原始报文
        String originalStr = orderXml;
        //加签、加密
        Mpf mpf = genMpf(fetureCode , membercode);
        SealedData sealedData = null;
        try {
            ICryptoService service = CryptoServiceFactory.createCryptoService();
            sealedData = service.seal(mpf, originalStr.getBytes("utf-8"));
        } catch (CryptoException e) {
            System.out.println(e);
        }catch (Exception e) {
            System.out.println(e);
        }
        Pay2bankRequest request = CCSUtil.genRequest(membercode , VERSION);
        byte[] nullbyte = {};
        byte[] byteOri = sealedData.getOriginalData() == null ? nullbyte : sealedData.getOriginalData();
        byte[] byteEnc = sealedData.getEncryptedData() == null ? nullbyte : sealedData.getEncryptedData();
        byte[] byteEnv = sealedData.getDigitalEnvelope() == null ? nullbyte : sealedData.getDigitalEnvelope();
        byte[] byteSig = sealedData.getSignedData() == null ? nullbyte : sealedData.getSignedData();
        request.getRequestBody().getSealDataType().setOriginalData(PKIUtil.byte2UTF8StringWithBase64(byteOri));
        //获取加签报文
        request.getRequestBody().getSealDataType().setSignedData(PKIUtil.byte2UTF8StringWithBase64(byteSig));
//		//获取加密报文
        request.getRequestBody().getSealDataType().setEncryptedData(PKIUtil.byte2UTF8StringWithBase64(byteEnc));
//		//数字信封
        request.getRequestBody().getSealDataType().setDigitalEnvelope(PKIUtil.byte2UTF8StringWithBase64(byteEnv));

        //请求报文
        String requestXml = CCSUtil.convertToXml(request, encoding);
        System.out.println("请求加密报文 = " + requestXml);
        return requestXml;
    }


    public static String test_invokeCSSCollection(String requestXml,String url) throws Exception {
        //初始化HttpClient
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, null, null);
        SSLContext.setDefault(sslContext);
        //请求服务端
//		InputStream in_withcode = new ByteArrayInputStream(requestXml.getBytes(encoding));
//		method.setRequestBody(in_withcode);
        // url的连接等待超时时间设置
        client.getHttpConnectionManager().getParams().setConnectionTimeout(2000);

        // 读取数据超时时间设置
        client.getHttpConnectionManager().getParams().setSoTimeout(3000);
        method.setRequestEntity(new StringRequestEntity(requestXml, "text/html", "utf-8"));
        client.executeMethod(method);

        //打印服务器返回的状态
        System.out.println(method.getStatusLine());

        //打印返回的信息
        InputStream stream = method.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(stream,encoding));
        StringBuffer buf = new StringBuffer();
        String line;
        while (null != (line = br.readLine())) {
            buf.append(line).append("\n");
        }
        //释放连接
        method.releaseConnection();
        return buf.toString();
    }


    public static Mpf genMpf(String fetureCode, String membercode) {
        Mpf mpf = new Mpf();
        mpf.setFeatureCode(fetureCode);
        mpf.setMemberCode(membercode);
        return mpf;
    }

}
