package com.hyy.ifm.sys.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.sys.dao.RoleDao;
import com.hyy.ifm.sys.pojo.IfmRole;
import com.hyy.ifm.sys.pojo.IfmRoleMuen;
import com.hyy.ifm.sys.pojo.IfmUserRole;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-01-11 9:20
 * @Description Created with IntelliJ IDEA.
 */
@IocBean
public class RoleDaoImpl extends BaseDao implements RoleDao {

    @Override
    public List<IfmRole> qryUserRole(String userId) {
        return dao.query(IfmRole.class, Cnd.wrap(" roleId in (select ROLE_ID from IFM_USER_ROLE where user_id='"+userId+"' ) "));
    }

    @Override
    public void insertRoles(IfmRole org) {
        dao.insert(org);
    }

    @Override
    public void updateRoles(IfmRole org) {
        dao.updateIgnoreNull(org);
    }

    @Override
    public void deleteRoles(String roleId) {
        dao.clear("IFM_SYS_ROLE", Cnd.where("roleId", "=", roleId));
        dao.clear("IFM_ROLE_MUEN", Cnd.where("ROLE_ID", "=", roleId));
        dao.clear("IFM_USER_ROLE", Cnd.where("ROLE_ID", "=", roleId));
    }

    @Override
    public List<IfmRoleMuen> qryRoleMuens(String roleId) {
        return dao.query(IfmRoleMuen.class, Cnd.where("ROLE_ID", "=", roleId));
    }

    @Override
    public void deleteUserRoles(String userId) {
        dao.clear("IFM_USER_ROLE", Cnd.where("USER_ID", "=", userId));
    }

    @Override
    public void fastInsertUserRole(List<IfmUserRole> rms) {
        dao.fastInsert(rms);
    }

    @Override
    public void deleteAllRoles(String roleId) {
        dao.clear("IFM_ROLE_MUEN", Cnd.where("ROLE_ID", "=", roleId));
    }

    @Override
    public void fastInsertRolesMeuns(List<IfmRoleMuen> rms) {
        dao.fastInsert(rms);
    }

}
