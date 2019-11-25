package com.hyy.ifm;

import com.hyy.ifm.dispcter.Dispcter;
import com.hyy.ifm.dispcter.inlet.sys.*;
import com.hyy.ifm.util.PropertiesUtil;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.integration.quartz.NutQuartzCronJobFactory;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

/**
 * <i>Setup : Web 启动器入口</i>
 * 
 * @author 毛椅俊
 *
 */
public class MainSetup implements Setup {
	
	public void init(NutConfig nc) {
		Ioc ioc = nc.getIoc();
		PropertiesUtil.readProperties("/cfg", "/cfg/config.properties");

		// 触发quartz 工厂,将扫描job任务
		ioc.get(NutQuartzCronJobFactory.class);
		
		synchronized(Dispcter.serviceTreeMap.getClass()) {
			Mvcs.ctx().getDefaultIoc().get(SysInlet.class).init();
			Mvcs.ctx().getDefaultIoc().get(ProductInlet.class).init();
			Mvcs.ctx().getDefaultIoc().get(BusinessInlet.class).init();
			Mvcs.ctx().getDefaultIoc().get(DataInlet.class).init();
			Mvcs.ctx().getDefaultIoc().get(CustomerInlet.class).init();
			Mvcs.ctx().getDefaultIoc().get(RefundInlet.class).init();
			Mvcs.ctx().getDefaultIoc().get(ConfigInlet.class).init();
			Mvcs.ctx().getDefaultIoc().get(NewsInlet.class).init();
		//	Mvcs.ctx().getDefaultIoc().get(OrderInlet.class).init();
		}
	}
	public void destroy(NutConfig nc) {
		
	}
	
	
}
