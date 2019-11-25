package com.hyy.ifm.sys.service.impl;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.sys.dao.MeunDao;
import com.hyy.ifm.sys.pojo.Cnds;
import com.hyy.ifm.sys.service.MeunService;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import java.util.List;

/**
 * 菜单服务实现
 * 
 * @author 饶瑞文
 *
 */
@IocBean
public class MeunServiceImpl extends BaseService implements MeunService {
	@Inject
	private MeunDao meunDao;	// 菜单数据访问对象

	@Override
	public CallBackBean qryMuenByUserid(String json) {
		try {
			Cnds cnds = Json.fromJson(Cnds.class, json);
			List<Record> res = meunDao.qryMuenByUserid(cnds.getUserCode());
			return joinformateJson(json, "success", res);
		} catch (Exception e) {
			e.printStackTrace();
			return joinformateJson(json, "查询菜单失败", "");
		}
	}

}
