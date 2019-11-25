package com.hyy.root.util;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.*;

public class StringUtil {
	public static String nvl(Object obj) {
		String str = "";
		if (null != obj)
			return obj.toString();
		return str;
	}

	public static int parseInt(Object obj) {
		try {
			if (null != obj) {
				return Integer.parseInt(nvl(obj));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static double parseDouble(double f) {
		BigDecimal   b   =   new   BigDecimal(f);  
		double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
		return f1;
	}
	
	public static float round(Double s){
		BigDecimal   b   =   new   BigDecimal(s);  
		float   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();  
		return f1;
	}

	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public final static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String countDK(String monthlys,String nhls,int qx){
		double monthly = Double.valueOf(monthlys);
		double nhl = Double.valueOf(nhls);
		double yll = nhl*0.01 / 12;
		double contract_amt = monthly * (Math.pow((1 + yll), qx) - 1) / (yll * (Math.pow((1 + yll), qx)));
		contract_amt = Math.ceil(contract_amt);
		return contract_amt+"";
	}
	
	
	public static String getIpAddr(HttpServletRequest request) {      
        String ip = request.getHeader("x-forwarded-for");      
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
           ip = request.getHeader("Proxy-Client-IP");      
       }      
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
           ip = request.getHeader("WL-Proxy-Client-IP");      
        }      
      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
            ip = request.getRemoteAddr();      
       }      
      return ip;      
 }
	
	/**       
     * 描述:获取 post 请求的 byte[] 数组 
     * <pre> 
     * 举例： 
     * </pre> 
     * @param request 
     * @return 
     * @throws IOException       
     */  
    private static byte[] getRequestPostBytes(HttpServletRequest request)  
            throws IOException {  
        int contentLength = request.getContentLength();  
        if(contentLength<0){  
            return null;  
        }  
        byte buffer[] = new byte[contentLength];  
        for (int i = 0; i < contentLength;) {  
  
            int readlen = request.getInputStream().read(buffer, i,  
                    contentLength - i);  
            if (readlen == -1) {  
                break;  
            }  
            i += readlen;  
        }  
        return buffer;  
    }  
      
    /**       
     * 描述:获取 post 请求内容 
     * <pre> 
     * 举例： 
     * </pre> 
     * @param request 
     * @return 
     * @throws IOException       
     */  
    public static String getRequestPostStr(HttpServletRequest request)  
            throws IOException {  
        byte buffer[] = getRequestPostBytes(request);  
        String charEncoding = request.getCharacterEncoding();  
        if (charEncoding == null) {  
            charEncoding = "UTF-8";  
        }  
        return new String(buffer, charEncoding);  
    }
	
	/** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static String md5Sign(Map<String, Object> sArray,boolean isEncode,String privateKey) {
        return MD5(createLinkStringNoNull(sArray,isEncode,privateKey)).toLowerCase();
    }
    
    
    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @param isEncode 是否要对value进行URLEncoder
     * @return 拼接后字符串
     */
    public static String createLinkStringNoNull(Map<String, Object> params,boolean isEncode,String privateKey) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";
        try {
	        for (int i = 0; i < keys.size(); i++) {
	            String key = keys.get(i);
	            String value = nvl(params.get(key));
	            /*if("".equals(value)){
	            	continue;
	            }*/
	            if(isEncode){
	            	if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
						prestr = prestr + key + "=" + java.net.URLEncoder.encode(value,"utf-8");
	                } else {
	                    prestr = prestr + key + "=" + java.net.URLEncoder.encode(value,"utf-8") + "&";
	                }
	            }else{
	            	if(value.indexOf("&") > 0 || value.indexOf("@") > 0){
	            		value = java.net.URLEncoder.encode(value,"utf-8");
	            	}
	            	if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
	                    prestr = prestr + key + "=" + value;
	                } else {
	                    prestr = prestr + key + "=" + value + "&";
	                }
	            }
	        }
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        System.out.println(prestr+privateKey);
        return prestr+privateKey;
    }
    
    /** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, Object> paraFilter(Map<String, Object> sArray) {

        Map<String, Object> result = new HashMap<String, Object>();
        for (String key : sArray.keySet()) {
            String value = nvl(sArray.get(key));
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
    
    
    /** 
     * 除去数组中的空值和签名参数、时间戳
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, Object> paraFilter2(Map<String, Object> sArray) {

        Map<String, Object> result = new HashMap<String, Object>();
        for (String key : sArray.keySet()) {
            String value = nvl(sArray.get(key));
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type") || key.equalsIgnoreCase("timeSpan")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
}
