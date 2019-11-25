package com.hyy.ifm.sys.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("IFM_ROLE_MUEN")
public class IfmRoleMuen {
	@Column("ROLE_ID")
	private int roleId;
	@Column("MUEN_ID")
	private int muenId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getMuenId() {
		return muenId;
	}

	public void setMuenId(int muenId) {
		this.muenId = muenId;
	}

}
