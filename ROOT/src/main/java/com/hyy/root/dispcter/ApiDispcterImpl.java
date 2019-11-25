package com.hyy.root.dispcter;

import com.alibaba.fastjson.JSON;
import com.hyy.root.util.Constant;
import org.nutz.http.Http;
import org.nutz.ioc.loader.annotation.IocBean;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Api 调度器实现
 * 
 * @author 毛椅俊
 *
 */
@IocBean(name="apiDispcter")
public class ApiDispcterImpl implements ApiDispcter{

	/* (non-Javadoc)
	 * @see com.ism.dispcter.ApiDispcter#doProcess(java.util.Map)
	 */
	@Override
	public String doProcess(Map<String,Object> parms) {
		String apiRet = "";
		
		try {
			Map<String, Object> _parms = new HashMap<String, Object>();
			_parms.put("json", URLEncoder.encode(JSON.toJSONString(parms), "UTF-8"));
			apiRet = Http.post(Constant.SERVER_ADDR, _parms, Constant.TIME_OUT);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
		}
		
		return apiRet;
	}

}
