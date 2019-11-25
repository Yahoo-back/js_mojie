package com.hyy.ifm.dispcter.inlet;

import com.alibaba.fastjson.JSON;
import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.pojo.JsonBean;
import com.hyy.ifm.dispcter.Dispcter;
import com.visionalsun.util.handler.UtilHandler;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.lang.reflect.Method;

/**
 * 入口
 * 
 * @author 毛椅俊
 *
 */
@IocBean(scope="singleton", singleton=true)
public class Inlet extends Dispcter {
	private static final long serialVersionUID = 3319795731244805486L;
	
	
	/**
	 * <i>认证企业信息</i>
	 * 
	 * @param parms
	 * @return
	 */
	public static String Authz(String parms) {
		CallBackBean _callBackBean = new CallBackBean();
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			
		}
		
		return Json.toJson(_callBackBean, JsonFormat.full());
	}
	
	/**
	 * <i>认证人员信息</i>
	 * 
	 * @param parms
	 * @return
	 */
	public static String Authc(String parms) {
		CallBackBean _callBackBean = new CallBackBean();
		
		try {
			boolean _isNotPass = false;	// 是否未通过（true：是；false：否；）
			JsonBean _jsonBean = JSON.parseObject(parms, JsonBean.class);
			if (!nonAuthcServiceSet.contains(_jsonBean.getCmd())&&
					!"api".equals(_jsonBean.getUserCode())) {
				
				Object _service = serviceTreeMap.get("valiToken");
				if (UtilHandler.IsNotNull(_service)) {
					Method _method = _service.getClass().getMethod("valiToken", String.class, String.class);
					_callBackBean = (CallBackBean) _method.invoke(_service, _jsonBean.getToken(), _jsonBean.getUserCode());
					_isNotPass = !"success".equals(_callBackBean.getResultNode());	// token 验证不通过
				}
			}
			
			if (!_isNotPass) {
				Object _service = serviceTreeMap.get(_jsonBean.getCmd());
				if (UtilHandler.IsNotNull(_service)) {
					Method _method = _service.getClass().getMethod(_jsonBean.getCmd(), String.class);
					_callBackBean = (CallBackBean) _method.invoke(_service, parms);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			
		}
		
		return Json.toJson(_callBackBean, JsonFormat.full());
	}
	
}
