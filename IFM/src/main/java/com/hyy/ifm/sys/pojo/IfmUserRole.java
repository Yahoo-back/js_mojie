package com.hyy.ifm.sys.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("IFM_USER_ROLE")
public class IfmUserRole {
	@Column("USER_ID")
	private int userId;
	@Column("ROLE_ID")
	private int roleId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
