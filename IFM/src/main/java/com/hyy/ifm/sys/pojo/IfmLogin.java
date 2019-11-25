package com.hyy.ifm.sys.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * 登录表
 * 
 * @author Administrator
 *
 */
@Table("IFM_SYS_LOGIN")
public class IfmLogin {
	@Id
	private int lgnId;
	@Column("USER_CODE")
	private String userCode;
	@Column("PASSWORD")
	private String password;
	@Column("REGISTER_DATE")
	private Date registerDate;
	@Column("type")
	private int type;
	@Column("openLgnId")
	private String openLgnId;

	public String getOpenLgnId() {
		return openLgnId;
	}

	public void setOpenLgnId(String openLgnId) {
		this.openLgnId = openLgnId;
	}

	public int getLgnId() {
		return lgnId;
	}

	public void setLgnId(int lgnId) {
		this.lgnId = lgnId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
