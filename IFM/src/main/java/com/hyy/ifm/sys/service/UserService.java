package com.hyy.ifm.sys.service;


import com.hyy.ifm.common.pojo.CallBackBean;

/**
 * 用户表
 * 
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * token验证
	 * @param token
	 * @param userid
	 * @return
	 */
	public CallBackBean valiToken(String token, String userid);

	/**
	 * 用户登录
	 * @param json
	 * @return
	 */
	public CallBackBean login(String json);

	/**
	 * 修改用户密码
	 * @param json
	 * @return
	 */
	public CallBackBean changePassword(String json);

	/**
	 * 查询所有员工
	 * @param json
	 * @return
	 */
	public CallBackBean qryUserList(String json);

	/**
	 * 查询所有员工
	 * @param json
	 * @return
	 */
	public CallBackBean qryUserListByType(String json);

	/**
	 * 根据登录id查询员工
	 * @param json
	 * @return
	 */
	public CallBackBean qryUserByLgnId(String json);

	/**
	 * 根据登录id查询员工
	 * @param json
	 * @return
	 */
	public CallBackBean qrySourceByLgnId(String json);

	/**
	 * 更改员工状态
	 * @param json
	 * @return
	 */
	public CallBackBean updateUserStatus(String json);

	/**
	 * 保存或更新员工
	 * @param json
	 * @return
	 */
	public CallBackBean saveUser(String json);

	public CallBackBean saveQdaoSlList(String json);

	/**
	 * 保存或更新员工
	 * @param json
	 * @return
	 */
	public CallBackBean saveUserQudao(String json);

	/**
	 * 重置员工密码
	 * @param json
	 * @return
	 */
	public CallBackBean resetPassword(String json);

    /**
     * 查询所有角色
     * @param json
     * @return
     */
    public CallBackBean qryRoles(String json);

    /**
     * 查询员工所属角色
     * @param json
     * @return
     */
    public CallBackBean qryUserRole(String json);

    /**
     * 保存角色
     * @param json
     * @return
     */
    public CallBackBean saveRoles(String json);

    /**
     * 删除角色
     * @param json
     * @return
     */
    public CallBackBean deleteRoles(String json);

    /**
     * 根据角色查询菜单
     * @param json
     * @return
     */
    public CallBackBean qryMeunsByRole(String json);

    /**
     * 保存员工分配的角色
     * @param json
     * @return
     */
    public CallBackBean allocationRoles(String json);

    /**
     * 保存角色下的菜单
     * @param json
     * @return
     */
    public CallBackBean allocationMeuns(String json);

    /**
     * 查询所有的菜单
     * @param json
     * @return
     */
    public CallBackBean qryAllMeuns(String json);

    /**
     * 根据id查询菜单
     * @param json
     * @return
     */
    public CallBackBean qryMeun(String json);

    /**
     * 保存更新菜单
     * @param json
     * @return
     */
    public CallBackBean saveMuen(String json);

    /**
     * 删除菜单
     * @param json
     * @return
     */
    public CallBackBean deleteMuen(String json);

}
