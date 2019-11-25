package com.hyy.ifm.sys.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.sys.dao.MeunDao;
import com.hyy.ifm.sys.pojo.IfmMuen;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean(name="meunDao")
public class MeunDaoImpl extends BaseDao implements MeunDao {

	@Override
	public List<Record> qryMuenByUserid(String userCode) {
		try {
			Sql sql = dao.sqls().create("sys.meun.select.data");
			sql.params().set("user_code", userCode);
			return this.queryforlist(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<IfmMuen> qryMeuns() {
		return dao.query(IfmMuen.class, Cnd.where("STATUS", "=", "0").asc("XH"));
	}

}
