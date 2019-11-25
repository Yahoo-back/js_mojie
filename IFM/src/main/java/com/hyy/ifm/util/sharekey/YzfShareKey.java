package com.hyy.ifm.util.sharekey;

import com.visionalsun.util.bean.ShareKey;

/**
 * <i>Yzf : 悦租房</i>
 * 
 * @author 饶瑞文
 *
 */
public class YzfShareKey extends ShareKey {
	
	/**<i>命名空间*/
	public static final String NAMESPACE = "YZF.NAMESPACE";
	
	
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
	 * <li>pay_ratio：付款比率百分比
	 * <li>penalty_ratio：违约金比率百分比
	 * <li>payCallbackURLToSHTK_KHTZ$YZF$Alipay：支付回调 URL > 商户退款_客户退租$悦租房$支付宝
	 * </ul>
	 */
	public enum Value {
		
		/**<i>付款比率百分比*/
		pay_ratio(93D),
		/**<i>违约金比率百分比*/
		penalty_ratio(20D),
		/**<i>支付回调 URL > 商户退款_客户退租$悦租房$支付宝*/
		payCallbackURLToSHTK_KHTZ$YZF$Alipay("http://127.0.0.1/ifm/api?cmd=payCallbackToSHTK_KHTZ$YZF$Alipay");
		
		
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
