package com.hyy.ifm.dispcter.inlet.sys;

import com.hyy.ifm.dispcter.inlet.Inlet;
import com.hyy.ifm.sys.service.MeunService;
import com.hyy.ifm.sys.service.UserService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * [系统级]系统管理 - 入口
 * 
 * @author 毛椅俊
 *
 */
@IocBean(scope="singleton", singleton=true)
public class SysInlet extends Inlet {
	private static final long serialVersionUID = -6147426933528115662L;
	
	@Inject
	private MeunService meunService;	// 菜单服务
	@Inject
	private UserService userService;	// 用户服务

	@Override
	public void init() {
		/**
		 * 单点登录
		 */
		//serviceTreeMap.put("valiToken", userService);

		/**
		 * 登录
		 */
		serviceTreeMap.put("login", userService);

		/**
		 * 修改用户密码
		 */
		serviceTreeMap.put("changePassword", userService);

		/**
		 * 根据userId查询菜单
		 */
		serviceTreeMap.put("qryMuenByUserid", meunService);

		/**
		 * 查询所有员工
		 */
		serviceTreeMap.put("qryUserList", userService);

		/**
		 * 查询所有员工
		 */
		serviceTreeMap.put("qryUserListByType", userService);

		/**
		 * 根据登录id查询员工
		 */
		serviceTreeMap.put("qryUserByLgnId", userService);

		serviceTreeMap.put("qrySourceByLgnId", userService);

		/**
		 * 更改用户状态
		 */
		serviceTreeMap.put("updateUserStatus", userService);

		/**
		 * 保存或更新员工
		 */
		serviceTreeMap.put("saveUser", userService);

		serviceTreeMap.put("saveQdaoSlList", userService);

		/**
		 * 保存或更新员工
		 */
		serviceTreeMap.put("saveUserQudao", userService);

		/**
		 * 重置员工密码
		 */
		serviceTreeMap.put("resetPassword", userService);

        /**
         * 查询所有角色
         */
        serviceTreeMap.put("qryRoles", userService);

        /**
         * 查询员工所属角色
         */
        serviceTreeMap.put("qryUserRole", userService);

        /**
         * 添加角色
         */
        serviceTreeMap.put("saveRoles", userService);

        /**
         * 删除角色
         */
        serviceTreeMap.put("deleteRoles", userService);

        /**
         * 根据角色查询菜单
         */
        serviceTreeMap.put("qryMeunsByRole", userService);

        /**
         * 保存员工分配的角色
         */
        serviceTreeMap.put("allocationRoles", userService);

        /**
         * 保存角色下的菜单
         */
        serviceTreeMap.put("allocationMeuns", userService);

        /**
         * 查询所有的菜单
         */
        serviceTreeMap.put("qryAllMeuns", userService);

        /**
         * 根据id查询菜单
         */
        serviceTreeMap.put("qryMeun", userService);

        /**
         * 保存更新菜单
         */
        serviceTreeMap.put("saveMuen", userService);

        /**
         * 删除菜单
         */
        serviceTreeMap.put("deleteMuen", userService);
    }

}
