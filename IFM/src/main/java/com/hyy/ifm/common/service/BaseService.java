package com.hyy.ifm.common.service;

import com.alibaba.fastjson.JSON;
import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.util.StringUtil;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * 基础服务
 * 
 * @author 饶瑞文
 *
 */
public class BaseService {
	protected static final Log log = Logs.get();	// log 日志管理器
	
	private NutMap nutMap = new NutMap();
	
	
	/*
	 * 自定义扩展部分
	 * 
	 * 说明：该部分为应对历史遗留问题做兼容，仅对 com.ifm.common.service.BaseService 有效，
	 * 后续开发请参照 com.visionalsun.util.bean.Result 标准使用“系统基础部分”。
	 */
	protected CallBackBean joinformateJson(String json, String resultNode, Object rows) {
		if (!StringUtil.nvl(json).isEmpty()) this.nutMap = JSON.parseObject(json, NutMap.class);
		return new CallBackBean(this.nutMap.getString("cmd"), this.nutMap.getString("token"), 1000, resultNode, rows);
	}
	protected CallBackBean joinformateJson(String json, String resultNode, Integer total, Object rows) {
		if (!StringUtil.nvl(json).isEmpty()) this.nutMap = JSON.parseObject(json, NutMap.class);
		return new CallBackBean(this.nutMap.getString("cmd"), this.nutMap.getString("token"), 1000, resultNode, rows, total);
	}
	protected CallBackBean joinformateJson(String json, String resultNode, Integer total, Object rows, Object footer) {
		if (!StringUtil.nvl(json).isEmpty()) this.nutMap = JSON.parseObject(json, NutMap.class);
		return new CallBackBean(this.nutMap.getString("cmd"), this.nutMap.getString("token"), 1000, resultNode, rows, total, footer);
	}
	
	protected CallBackBean joinErrorFormateJson(String json, String resultNode) {
		if (!StringUtil.nvl(json).isEmpty()) this.nutMap = JSON.parseObject(json, NutMap.class);
		return new CallBackBean(this.nutMap.getString("cmd"), this.nutMap.getString("token"), 0, resultNode);
	}
	
}