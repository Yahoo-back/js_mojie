package com.hyy.ifm.common.pojo;

import com.visionalsun.util.bean.Result;
import com.visionalsun.util.handler.UtilHandler;

/**
 * 反馈重用组件
 * 
 * @author 饶瑞文
 *
 */
public class CallBackBean {
	private String cmd;	// 操作指令
	private String token;	// 请求票据
	
	private String result;	// 消息编码
	private String resultNode;	// 消息内容
	
	private Object rows;	// 数据信息
	private Integer total;	// 分页总条数
	private Object footer;	// 分页页脚
	
	
	/*
	 * 系统基础部分
	 */
	public CallBackBean() {
		super();
		this.setResult(String.valueOf(Result.Type.Fail_Zh.getCode()));	// 设置消息编码
		this.setResultNode(Result.Type.Fail_Zh.getMessage());	// 设置消息内容
	}
	public CallBackBean(Result.Type type) {
		super();
		this.setResult(String.valueOf(type.getCode()));	// 设置消息编码
		this.setResultNode(type.getMessage());	// 设置消息内容
	}
	public CallBackBean(Result.Type type, String message) {
		super();
		this.setResult(String.valueOf(type.getCode()));	// 设置消息编码
		this.setResultNode(UtilHandler.IsNotEmpty(message) ? message : type.getMessage());	// 设置消息内容
	}
	public CallBackBean(Result.Type type, Object data) {
		super();
		this.setResult(String.valueOf(type.getCode()));	// 设置消息编码
		this.setResultNode(type.getMessage());	// 设置消息内容
		this.setRows(data);	// 设置数据信息
	}
	public CallBackBean(Result.Type type, String message, Object data) {
		super();
		this.setResult(String.valueOf(type.getCode()));	// 设置消息编码
		this.setResultNode(UtilHandler.IsNotEmpty(message) ? message : type.getMessage());	// 设置消息内容
		this.setRows(data);	// 设置数据信息
	}
	public CallBackBean(Result.Type type, Object data, Integer total) {
		super();
		this.setResult(String.valueOf(type.getCode()));	// 设置消息编码
		this.setResultNode(type.getMessage());	// 设置消息内容
		this.setRows(data);	// 设置数据信息
		this.setTotal(total);	// 设置分页总条数
	}
	public CallBackBean(Result.Type type, String message, Object data, Integer total) {
		super();
		this.setResult(String.valueOf(type.getCode()));	// 设置消息编码
		this.setResultNode(UtilHandler.IsNotEmpty(message) ? message : type.getMessage());	// 设置消息内容
		this.setRows(data);	// 设置数据信息
		this.setTotal(total);	// 设置分页总条数
	}
	public CallBackBean(Result.Type type, Object data, Integer total, Object footer) {
		super();
		this.setResult(String.valueOf(type.getCode()));	// 设置消息编码
		this.setResultNode(type.getMessage());	// 设置消息内容
		this.setRows(data);	// 设置数据信息
		this.setTotal(total);	// 设置分页总条数
		this.setFooter(footer);	// 设置分页页脚
	}
	public CallBackBean(Result.Type type, String message, Object data, Integer total, Object footer) {
		super();
		this.setResult(String.valueOf(type.getCode()));	// 设置消息编码
		this.setResultNode(UtilHandler.IsNotEmpty(message) ? message : type.getMessage());	// 设置消息内容
		this.setRows(data);	// 设置数据信息
		this.setTotal(total);	// 设置分页总条数
		this.setFooter(footer);	// 设置分页页脚
	}
	
	/*
	 * 自定义扩展部分
	 * 
	 * 说明：该部分为应对历史遗留问题做兼容，仅对 com.ifm.common.service.BaseService 有效，
	 * 后续开发请参照 com.visionalsun.util.bean.Result 标准使用“系统基础部分”。
	 */
	public CallBackBean(String cmd, String token, Integer result, String resultNode) {
		this.cmd = cmd;
		this.token = token;
		this.result = String.valueOf(result);
		this.resultNode = resultNode;
	}
	public CallBackBean(String cmd, String token, Integer result, String resultNode, Object data) {
		this.cmd = cmd;
		this.token = token;
		this.result = String.valueOf(result);
		this.resultNode = resultNode;
		this.rows = data;
	}
	public CallBackBean(String cmd, String token, Integer result, String resultNode, Object data, Integer total) {
		this.cmd = cmd;
		this.token = token;
		this.result = String.valueOf(result);
		this.resultNode = resultNode;
		this.rows = data;
		this.total = total;
	}
	public CallBackBean(String cmd, String token, Integer result, String resultNode, Object data, Integer total, Object footer) {
		this.cmd = cmd;
		this.token = token;
		this.result = String.valueOf(result);
		this.resultNode = resultNode;
		this.rows = data;
		this.total = total;
		this.footer = footer;
	}
	
	
	/**
	 * @return the cmd
	 */
	public String getCmd() {
		return cmd;
	}
	/**
	 * @param cmd the cmd to set
	 */
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the resultNode
	 */
	public String getResultNode() {
		return resultNode;
	}
	/**
	 * @param resultNode the resultNode to set
	 */
	public void setResultNode(String resultNode) {
		this.resultNode = resultNode;
	}

	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * @return the rows
	 */
	public Object getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(Object rows) {
		this.rows = rows;
	}

	/**
	 * @return the footer
	 */
	public Object getFooter() {
		return footer;
	}
	/**
	 * @param footer the footer to set
	 */
	public void setFooter(Object footer) {
		this.footer = footer;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CallBackBean [cmd=" + cmd + ", token=" + token + ", result=" + result + ", resultNode=" + resultNode
				+ ", total=" + total + ", rows=" + rows + ", footer=" + footer + "]";
	}
	
}