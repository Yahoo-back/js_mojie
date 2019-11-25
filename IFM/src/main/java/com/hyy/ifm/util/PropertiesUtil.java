package com.hyy.ifm.util;

import org.nutz.ioc.impl.PropertiesProxy;

public class PropertiesUtil {
    /**
     * 读取各种配置文件
     * 
     * @param filePackage
     * @param fileName
     */
    public static void readProperties(String filePackage, String fileName) {
        PropertiesProxy property = new PropertiesProxy(filePackage, fileName);
        
    }
}
