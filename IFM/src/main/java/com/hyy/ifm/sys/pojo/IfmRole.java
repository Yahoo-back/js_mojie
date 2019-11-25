package com.hyy.ifm.sys.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("IFM_SYS_ROLE")
public class IfmRole {
	@Id
	private int roleId;
	@Column("ROLE_NAME")
	private String roleName;
	@Column("ROLE_CODE")
	private String roleCode;
	/*@Column
	private String role_filter;

	public String getRole_filter() {
		return role_filter;
	}

	public void setRole_filter(String role_filter) {
		this.role_filter = role_filter;
	}*/

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
