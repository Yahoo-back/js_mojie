package com.hyy.ifm.sys.dao;


import com.hyy.ifm.sys.pojo.IfmRole;
import com.hyy.ifm.sys.pojo.IfmRoleMuen;
import com.hyy.ifm.sys.pojo.IfmUserRole;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-01-11 9:20
 * @Description Created with IntelliJ IDEA.
 */
public interface RoleDao {

    /**
     * 查询员工所属角色
     * @param userId
     * @return
     */
    List<IfmRole> qryUserRole(String userId);

    /**
     * 添加角色
     * @param org
     */
    void insertRoles(IfmRole org);

    /**
     * 更新角色
     * @param org
     */
    void updateRoles(IfmRole org);

    /**
     * 删除角色
     * @param roleId
     */
    void deleteRoles(String roleId);

    /**
     * 查询角色下的菜单
     * @param roleId
     * @return
     */
    List<IfmRoleMuen> qryRoleMuens(String roleId);

    /**
     * 删除员工下的角色
     * @param userId
     */
    void deleteUserRoles(String userId);

    /**
     * 插入员工分配的角色
     * @param rms
     */
    void fastInsertUserRole(List<IfmUserRole> rms);

    /**
     * 删除角色下的菜单
     * @param roleId
     */
    void deleteAllRoles(String roleId);

    /**
     * 分配角色下的菜单
     * @param rms
     */
    void fastInsertRolesMeuns(List<IfmRoleMuen> rms);
}
