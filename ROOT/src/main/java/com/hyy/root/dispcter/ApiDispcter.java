package com.hyy.root.dispcter;

import java.util.Map;

/**
 * Api 调度器
 * 
 * @author 毛椅俊
 *
 */
public interface ApiDispcter {
	
	/**
	 * 执行流程
	 * 
	 * @param parms
	 * @return
	 */
	public String doProcess(Map<String, Object> parms);
	
}
