package com.hyy.ifm.util.sharekey;

import com.alibaba.fastjson.JSONObject;
import com.visionalsun.util.bean.ShareKey;

/**
 * <i>Sys : 系统</i>
 * 
 * @author 饶瑞文
 *
 */
public class SysShareKey extends ShareKey {
	
	/**<i>命名空间*/
	public static final String NAMESPACE = "SYS.NAMESPACE";
	
	/**<i>登录 Session*/
	public static final String LoginSession = "user";
	/**<i>登录 Url*/
	public static final String LoginUrl = "/login";
	
	
	/**
	 * <i>值</i>
	 * 
	 * <ul>
	 * <li>说明：<u>通过此“值”的“键值对”可分别取得相应的 key-value</u>
	 * <li>语法：<u>具体使用如下</u>
	 * <ol><li>key：name()<li>value：get(Object.class)</ol>
	 * <li>示例：<ol><li>key：{@code enum.name()}<li>value：{@code enum.get(Object.class)}</ol>
	 * </ul>
	 * <p>可选项：
	 * <ul>
	 * <li>session：会话 Session
	 * <li>projectPath：Project 路径
	 * <li>alipayPayment：Alipay 支付宝付款
	 * </ul>
	 */
	public enum Value {
		
		/**<i>会话 Session*/
		session("7300c449-7d16-4d0b-88e7-f2b7b7072f48"),
		/**<i>Project 项目路径*/
		projectPath("/ROOT"),
		/**<i>Alipay 支付宝付款*/
		alipayPayment(new JSONObject()),
		/**<i>Service 服务地址（服务端远程）*/
		serviceAddress("122.96.86.140");
		
		
		private Object value;	// 值
		
		
		/*构造函数*/
		private Value(final Object value) {
			this.setValue(value);	// 设置值
		}
		
		
		/**
		 * @return the value
		 */
		private Object getValue() {
			return value;
		}
		/**
		 * @param value the value to set
		 */
		private void setValue(Object value) {
			this.value = value;
		}
		
		
		/**
		 * <i>get 方法</i>
		 * 
		 * @param type 类型
		 * <ul>
		 * <li>说明：<u>通过此类型得到相应的值</u>
		 * <li>语法：<u>Object.class（具体使用如下）</u>
		 * <li>示例：{@code Object.class}
		 * </ul>
		 * @return
		 */
		public <T> T get(Class<T> type) {
			return type.cast(this.getValue());
		}
		
	}
	
}
