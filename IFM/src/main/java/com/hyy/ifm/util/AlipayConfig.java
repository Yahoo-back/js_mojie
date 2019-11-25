package com.hyy.ifm.util;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088911777965350";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	// MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    public static String key = "xceq8scrn2b4kxb9u82bi54t3t3nhoyh";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://127.0.0.1:8080/create_direct_pay_by_user-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://127.0.0.1:8080/create_direct_pay_by_user-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "MD5";
	
	// 签名方式
	public static String sign_type_rsa = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "/home/ailipay";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "create_direct_pay_by_user";
	
	//私钥
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALnOkAz9cVvyWBvVpUf7whRe6YvfFPEI5GaRufQ/ObsDJHtaPZxiGkYnDC9sjgHBsTW/Xw5RvqEPSzXpfH9bSpPTVPxrw5+3cs8kxyy6KgfAD0TP75NOonPjMYz+XHLMBzGMA0+R2ynlGM0ihZtdSeXsULVlWYehBuA/7YdImoJ5AgMBAAECgYEAhW4wBhQOzt+mpD51Vc8WXXiXMIm/ySQAmSO3LQ4mUAT+stJoAiB1C8HIv1muVNaagqVuBqNvSMvpz8pfTDnrwCk3HYCo85ScfCVKki36u2fw+8kXhy/YpLxdGbXHQVIvTwUx/jf+x4EwkVjegA23Ct+voiUa83DM2utPWG+lV4ECQQDb9jLFDJyRYnnCNZTKeJ3G1eTGIowe+2xhthW2YBbVukOk7glzXCdf2O+5K3Isj7v3uKwO320QPRc4sfRW+DJRAkEA2D/RDj4lGV4b7FZAUaombWj7uOh61Ae4ESmfYGSg+IspZbnSjoBRa75BLOMpN2Pc3NY2CSX0+A+wpb6GvNbbqQJAQ1gYaS4YTYbJ4oUHHgpZvAPGvEbdgarWnv/hm+nFdABIL5nVeOVRBM5lbtr3ZN9I3GpE+3S6botVrb0GIw5wUQJBAJ9hOYVTzCgMMmxXjYQi8buFurEntw96uPD9SlfTEsjcWCB/em5IqLnOOV8J9aC7W1bwEldfwFLrzXf7GncnrDECQH21PcT8cr5iHFm2zti8KR9dN8zdLBFVEYVjn2XXo5zOIpW+OQhdjjLtK5pe1IwwvbyxGT/E5bmNd36LsISaOzk=";
	
	//公钥
	public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
//↓↓↓↓↓↓↓↓↓↓ 请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
	public static String anti_phishing_key = "";
	
	// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
	public static String exter_invoke_ip = "";
		
//↑↑↑↑↑↑↑↑↑↑请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
}

