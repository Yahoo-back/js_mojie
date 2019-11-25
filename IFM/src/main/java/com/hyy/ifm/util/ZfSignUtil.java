package com.hyy.ifm.util;

import java.math.BigDecimal;
import java.util.*;

public class ZfSignUtil {
    public static void main(String[] args) {
        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
        //String mfrchant_id="fffff";
        String merchant_id="190010002";
        String business_type="1005";
        String out_trade_no="1400000001";
        String key="3A4BC4A4000CF1B5FFA9E351E6C1539E";
        //parameters.put("mfrchant_id", mfrchant_id);
        parameters.put("merchant_id", merchant_id);
        parameters.put("business_type", business_type);
        parameters.put("out_trade_no",out_trade_no);
        String characterEncoding = "UTF-8";         //指定字符集UTF-8
        String mySign = createSign(parameters,key);
        //System.out.println("我 的签名是："+mySign);

    }
    public static String createSign(SortedMap<Object,Object> parameters,String key){
        StringBuffer sb = new StringBuffer();

        Set es = parameters.entrySet();  //所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            //空值不传递，不参与签名组串
            if(null != v && !"".equals(v)) {
                if(!it.hasNext()){
                    sb.append(k + "=" + v );
                }else {
                    sb.append(k + "=" + v + "&");
                }
            }
        }
        //System.out.println("字符串:"+sb.toString());
        sb=sb.append(key);
        System.out.println("字符串:"+sb.toString());
        //MD5加密,结果转换为大写字符
        String sign = MD5Utils.MD5Encrpytion(sb.toString()).toLowerCase();
        System.out.println("MD5加密值:"+sign);
        return sign;
    }

    /**
     * double 乘法
     * @param d1
     * @param d2hu
     * @return
     */
    public static double mul(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.multiply(bd2).doubleValue();
    }

    /**
     * double 相加
     * @param d1
     * @param d2
     * @return
     */
    public static String sum(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return  String.format("%.2f", bd1.add(bd2).doubleValue());
    }


    /**
     * double 相减
     * @param d1
     * @param d2
     * @return
     */
    public static String sub(double d1, double d2){
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        double v = bd1.subtract(bd2).doubleValue();
        System.out.println(v);
        return  String.format("%.2f", bd1.subtract(bd2).doubleValue());

    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1,double v2){
        return div(v1,v2,2);
    }

    public static double div2(double v1,double v2){
        return div(v1,v2,0);
    }


    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
