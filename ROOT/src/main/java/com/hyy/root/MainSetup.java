package com.hyy.root;

import com.hyy.root.util.PropertiesUtil;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

public class MainSetup implements Setup {

	public void init(NutConfig conf) {
		PropertiesUtil.readProperties("/conf", "/conf/config.properties");
	}

	public void destroy(NutConfig conf) {

	}

}
