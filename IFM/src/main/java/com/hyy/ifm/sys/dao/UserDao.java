
package com.hyy.ifm.sys.dao;

import com.hyy.ifm.customer.pojo.Qdao;
import com.hyy.ifm.customer.pojo.QdaoSl;
import com.hyy.ifm.sys.pojo.*;
import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * 用户表
 * 
 * @author Administrator
 *
 */
public interface UserDao {

	/**
	 * 根据用户名和密码查询
	 * @param usercode
	 * @param password
	 * @return
	 */
	 IfmLogin qryInfo(String usercode, String password);

	/**
	 * 根据登录id查询
	 * @param lngid
	 * @return
	 */
	IfmUser fetchUserByLngid(String lngid);

	QdaoSl fetchQdSlByLngid(String lngid);

	/**
	 *	根据userid删除ifm_sys_session表记录
	 * @param usedid
	 */
	 void deleteSSO(String usedid);

	/**
	 * 插入ifm_sys_session表记录
	 * @param sso
	 * @return
	 */
	IfmSSO insertSSO(IfmSSO sso);

	/**
	 * 插入登录日志
	 * @param isl
	 */
	 void insertSYSLogin(IfmSysLoginLog isl);

	/**
	 * 根据userId查询user
	 * @param userid
	 * @return
	 */
    IfmUser fetchUser(String userid);

	/**
	 * 根据lgnId查询用户登录账户
	 * @param lgnid
	 * @return
	 */
	IfmLogin fetchLogin(String lgnid);

	IfmLogin fetchLoginByUserCode(String userCode);

	/**
	 * 根据lgnid修改密码
	 * @param lgnId
	 * @param passWord
	 */
	void updateLogin(String lgnId, String passWord);

	/**
	 * 插入用户操作记录
	 * @param ifmUserOperate
	 */
	void insertOperate(IfmUserOperate ifmUserOperate);

	/**
	 * 查询员工count
	 * @param cnds
	 * @return
	 */
    int countUserList(Cnds cnds);

	/**
	 * 查询所有员工
	 * @param cnds
	 * @return
	 */
	List<Record> qryUserList(Cnds cnds);

	/**
	 * 查询所有渠道员工
	 * @param cnds
	 * @return
	 */
	List<Record> qryUserListByType(Cnds cnds);

	/**
	 * 查询所有员工
	 * @param cnds
	 * @return
	 */
	List<IfmLogin> qryUserList2(String userCode, boolean f);

	/**
	 * 更改用户状态
	 * @param id
	 * @param status
	 */
	void updateUser(String id, String status);

	/**
	 * 根据登录名称查找员工
	 * @param userCode
	 * @param f
	 * @param id
	 * @return
	 */
	IfmLogin fetchLoginByUserCode(String userCode, boolean f, int id);

	QdaoSl fetchLoginByQdSl(String userCode, boolean f, String updateTime,Double sl);

	/**
	 * 保存员工ifm_sys_login
	 * @param login
	 */
	void saveLogin(IfmLogin login);

	void saveQdao(Qdao qdao);

	void saveQdaoSl(QdaoSl qdaoSl);

	void updateQdao(Qdao qdao);

	/**
	 * 更新ifm_sys_login
	 * @param login
	 */
	void updateLoginObj(IfmLogin login);

	void updateQdSl(QdaoSl qdaoSl);

	void saveQdSl(QdaoSl qdaoSl);

	/**
	 * 更新ifm_sys_user
	 * @param user
	 */
	void updateUserObj(IfmUser user);

	/**
	 * 保存ifm_sys_user
	 * @param user
	 */
	void saveUser(IfmUser user);

    /**
     * 查询所有角色
     * @param cnds
     * @return
     */
    QueryResult qryRoles(Cnds cnds);

    /**
     * 查询所有菜单
     * @param cnds
     * @return
     */
    List<Record> qryAllMuens(Cnds cnds);

    /**
     * 根据路劲查询菜单
     * @param uri
     * @return
     */
    IfmMuen qryMuenByuri(String uri);

    /**
     * 保存菜单
     * @param muen
     */
    void insertMuen(IfmMuen muen);

    /**
     * 更新菜单
     *
     * @param muen
     */
    void updateMuen(IfmMuen muen);

    /**
     * 删除菜单
     * @param muenId
     */
    void deleteMuen(String muenId);

	/**
	 * 根据userId查询session
	 * @param userId
	 * @param token
	 * @return
	 */
	IfmSSO fetchSSO(String userId, String token);
}