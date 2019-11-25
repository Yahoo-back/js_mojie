package com.hyy.ifm;

import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * <i>Setup : Web 启动器组件</i>
 * 
 * @author 毛椅俊
 *
 */
@SetupBy(value=MainSetup.class)
@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/", "*anno", "com.hyy.ifm", "*tx", "*quartz"})
@Modules(scanPackage=true)
public class MainModule {
	
}
