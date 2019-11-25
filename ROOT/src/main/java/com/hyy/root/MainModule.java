package com.hyy.root;

import org.nutz.integration.shiro.ShiroSessionProvider;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SessionBy;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@SessionBy(ShiroSessionProvider.class)
@SetupBy(value=MainSetup.class)
@IocBy(type = ComboIocProvider.class, args = { "*js", "ioc/", "*anno", "com.hyy.root", "*tx", "*async" })
@Modules(scanPackage = true)
public class MainModule {

}
