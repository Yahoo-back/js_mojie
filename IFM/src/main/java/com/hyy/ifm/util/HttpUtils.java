package com.hyy.ifm.util;

import com.alibaba.fastjson.JSONObject;
import org.nutz.http.Http;

import java.util.*;

public class HttpUtils {
	/**
	 * 调用地方接口，获得JSON数据
	 * 
	 * @param method
	 *            方法名
	 * @param map
	 *            参数，不含有sign、timeSpan的参数
	 * @return
	 */
	public static String callThirdParty(String url, Map<String, Object> map) {
		map.put("timeSpan", DateUtil.DateToString(new Date(), DateStyle.YYYYMMDDHHMMSS));
		String sign = StringUtil.MD5(StringUtil.createLinkString(map, true)).toLowerCase();
		// appSecrect不参与展示在前台
		map.remove("appSecrect");
		map.put("sign", sign);
		String apiRet = Http.post(url, map, 60000);
		return apiRet;
	}

	/**
	 * 调用地方接口，获得JSON数据
	 * 
	 * @param method
	 *            方法名
	 * @param jsonObject
	 *            参数，jsonObject ,传入了json字符串，在接收方要按照流的方式接，必须为UTF-8
	 * @return
	 */
	public static String callThirdParty2(String url, JSONObject jsonObject) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> keys = new ArrayList<String>(jsonObject.keySet());
		for (int i = 0; i < keys.size(); i++) {
			map.put(keys.get(i), jsonObject.get(keys.get(i)));
		}
		String curr = DateUtil.DateToString(new Date(), DateStyle.YYYYMMDDHHMMSS);
		map.put("timeSpan", curr);
		String str = StringUtil.createLinkString(map, true);
		String sign = StringUtil.MD5(str).toLowerCase();
		// appSecrect不参与展示在前台
		map.remove("appSecrect");
		jsonObject.remove("appSecrect");

		jsonObject.put("timeSpan", curr);
		jsonObject.put("sign", sign);
		String apiRet = APIHttpClient.doPost(url, jsonObject.toJSONString());
		return apiRet;
	}
	
	/**
	 * 调用地方接口，获得JSON数据
	 * 
	 * @param method
	 *            方法名
	 * @param jsonObject
	 *            参数，jsonObject ,传入了json字符串，在接收方要按照流的方式接，必须为UTF-8
	 * @return
	 */
	public static String callThirdParty4TD(String url, JSONObject jsonObject,String key) {
		Map<String, Object> map = (Map<String, Object>) jsonObject;
		String sign = StringUtil.MD5(StringUtil.createLinkStringNoNull(map,true,key));
		jsonObject.put("sign", sign);
		String apiRet = APIHttpClient.doPost(url, jsonObject.toJSONString());
		return apiRet;
	}
}
