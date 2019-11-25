package com.hyy.ifm.sys.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.customer.pojo.Qdao;
import com.hyy.ifm.customer.pojo.QdaoSl;
import com.hyy.ifm.sys.dao.UserDao;
import com.hyy.ifm.sys.pojo.*;
import com.hyy.ifm.util.Constants;
import com.hyy.ifm.util.StringUtil;
import com.sun.javafx.charts.ChartLayoutAnimator;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean(name = "userDao")
public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public void saveQdao(Qdao qdao) {
		dao.insert(qdao);
	}

	@Override
	public void saveQdaoSl(QdaoSl qdaoSl) {
		dao.insert(qdaoSl);
	}
	
	@Override
	public IfmLogin qryInfo(String usercode, String password) {
		return dao.fetch(IfmLogin.class, Cnd.where("PASSWORD", "=", password).and("USER_CODE", "=", usercode));
	}

	@Override
	public IfmUser fetchUserByLngid(String lngid) {
		return dao.fetch(IfmUser.class, Cnd.where("LGN_ID", "=", lngid));
	}


	@Override
		public QdaoSl fetchQdSlByLngid(String lngid) {
		return dao.fetch(QdaoSl.class, Cnd.where("id", "=", lngid));
	}

	@Override
	public void deleteSSO(String userCode) {
		dao.clear("IFM_SYS_SESSION", Cnd.where("user_id", "=", userCode));
	}
	
	@Override
	public IfmSSO insertSSO(IfmSSO s) {
		return dao.insert(s);
	}
	
	@Override
	public void insertSYSLogin(IfmSysLoginLog isl) {
		dao.insert(isl);
	}

	@Override
	public IfmUser fetchUser(String userid) {
		return dao.fetch(IfmUser.class, Cnd.where("userid", "=", userid));
	}

	@Override
	public IfmLogin fetchLogin(String lgnid) {
		return dao.fetch(IfmLogin.class, Cnd.where("lgnId", "=", lgnid));
	}

	@Override
	public IfmLogin fetchLoginByUserCode(String userCode) {
		return dao.fetch(IfmLogin.class, Cnd.where("USER_CODE", "=", userCode));
	}

	@Override
	public void updateLogin(String lgnId, String passWord) {
		dao.update("IFM_SYS_LOGIN", Chain.make("PASSWORD", passWord), Cnd.where("lgnId", "=", lgnId));
	}

	@Override
	public void updateQdao(Qdao qdao) {
		dao.updateIgnoreNull(qdao);
	}

	@Override
	public void insertOperate(IfmUserOperate ifmUserOperate) {
		dao.insert(ifmUserOperate);
	}

	@Override
	public int countUserList(Cnds cnds) {
		try {
			Sql sql = dao.sqls().create("sys.user.count.data");
			sql.setVar("condition", cnds.getFuzzyCnd());
			return this.queryforCount(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Record> qryUserList(Cnds cnds) {
		try {
			Sql sql = dao.sqls().create("sys.user.select.data");
			sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
			sql.setVar("condition", cnds.getFuzzyCnd());
			return this.queryforlist(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<Record> qryUserListByType(Cnds cnds) {
		try {
			Sql sql = dao.sqls().create("sys.user.select.data.type");
			sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
			sql.setVar("condition", cnds.getFuzzyCnd());
			return this.queryforlist(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<IfmLogin> qryUserList2(String userCode, boolean f) {

		Cnd cnd = Cnd.where("userCode", "=", userCode);
		if (!f) {
			cnd = Cnd.where("lgnId", ">", 0);
		}
		return dao.query(IfmLogin.class, cnd);
			//return dao.query(IfmLogin.class, Cnd.where("lgnId", ">", 0));
	}

	@Override
	public void updateUser(String id, String status) {
		dao.update("IFM_SYS_USER", Chain.make("STATUS", status), Cnd.where("LGN_ID", "=", id));
	}

	@Override
	public IfmLogin fetchLoginByUserCode(String userCode, boolean f, int id) {
		Cnd cnd = Cnd.where("USER_CODE", "=", userCode);
		if (!f) {
			cnd = Cnd.where("USER_CODE", "=", userCode).and("lgnId", "<>", id);
		}
		return dao.fetch(IfmLogin.class, cnd);
	}



	@Override
	public QdaoSl fetchLoginByQdSl(String userCode, boolean f, String updateTime,Double sl) {
		Cnd cnd = Cnd.where("source", "=", userCode);
		if (!f) {
			cnd = Cnd.where("source", "=", userCode).and("update_time", "=", updateTime).and("sl","=",sl);
		}
		return dao.fetch(QdaoSl.class, cnd);
	}

	@Override
	public void saveLogin(IfmLogin login) {
		dao.insert(login);
	}

	@Override
	public void updateLoginObj(IfmLogin login) {
		dao.updateIgnoreNull(login);
	}

	@Override
	public void updateQdSl(QdaoSl qdaoSl) {
		dao.updateIgnoreNull(qdaoSl);
	}

	@Override
	public void saveQdSl(QdaoSl qdaoSl) {
		dao.insert(qdaoSl);
	}

	@Override
	public void updateUserObj(IfmUser user) {
		dao.updateIgnoreNull(user);
	}

	@Override
	public void saveUser(IfmUser user) {
		dao.insert(user);
	}

    @Override
    public QueryResult qryRoles(Cnds cnds) {
        Cnd cnd = Cnd.where("roleId", "<>", "1");
        if(!"".equals(StringUtil.nvl(cnds.getRows().getRoleId_cnd()))){
            cnd.and("roleId", "=", StringUtil.nvl(cnds.getRows().getRoleId_cnd()));
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getRoleName_cnd()))){
            cnd.and("ROLE_NAME", "like", "%"+StringUtil.nvl(cnds.getRows().getRoleName_cnd())+"%");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getRoleCode_cnd()))){
            cnd.and("ROLE_CODE", "like", "%"+StringUtil.nvl(cnds.getRows().getRoleCode_cnd())+"%");
        }
        int pageNumber = StringUtil.parseInt(cnds.getPageNum());
        Pager pager = dao.createPager(pageNumber, Constants.PAGESIZE);
        List<IfmRole> list = dao.query(IfmRole.class, cnd, pager);
        pager.setRecordCount(dao.count(IfmRole.class,cnd));
        return new QueryResult(list, pager);
    }

    @Override
    public List<Record> qryAllMuens(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("sys.muen.select.data");
            return this.queryforlist(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IfmMuen qryMuenByuri(String uri) {
        return dao.fetch(IfmMuen.class, Cnd.where("uri", "=", uri));
    }

    @Override
    public void insertMuen(IfmMuen muen) {
        dao.insert(muen);
    }

    @Override
    public void updateMuen(IfmMuen muen) {
        dao.updateIgnoreNull(muen);
    }

    @Override
    public void deleteMuen(String muenId) {
        dao.clear("IFM_SYS_MUEN", Cnd.where("muenId", "=", muenId));
    }

	@Override
	public IfmSSO fetchSSO(String userId, String token) {
		return dao.fetch(IfmSSO.class, Cnd.where("user_id", "=", userId).and("token", "=", token));
	}

}