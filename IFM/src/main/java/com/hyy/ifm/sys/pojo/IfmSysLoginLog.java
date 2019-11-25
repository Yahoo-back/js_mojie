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
@Table("IFM_SYS_LOGIN_LOG")
public class IfmSysLoginLog {
	@Id
	private int id;
	@Column("USER_CODE")
	private String userCode;
	@Column
	private Date loginDate;
	@Column
	private String loginPlace;
	@Column
	private String status;
	@Column
	private String address;
	@Column
	private String mac;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getLoginPlace() {
		return loginPlace;
	}
	public void setLoginPlace(String loginPlace) {
		this.loginPlace = loginPlace;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}

}
