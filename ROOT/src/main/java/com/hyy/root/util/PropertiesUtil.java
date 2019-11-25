package com.hyy.root.util;

import org.nutz.ioc.impl.PropertiesProxy;

public class PropertiesUtil {
	/**
	 * 读取各种配置文件
	 * @param filePackage
	 * @param fileName
	 */
	public static void readProperties(String filePackage,String fileName){
		PropertiesProxy property = new PropertiesProxy(filePackage, fileName);
		Constant.SERVER_ADDR =  property.get("server.addr");
		Constant.SYS_ENV =  property.get("system.env");
		Constant.UPLOAD_ADDR =  property.get("upload.addr");
		Constant.UPLOAD_WEBSITE =  property.get("upload.webSite");
	}
}
