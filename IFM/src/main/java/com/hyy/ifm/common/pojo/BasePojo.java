package com.hyy.ifm.common.pojo;

public abstract class BasePojo {
	private String cmd;
	private int pageNum = 1;
	private int pageSize = 10;
	private String userCode;
	private String taoken;
	private String role_filter;
	private String accountType;
	
	

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getRole_filter() {
		return role_filter;
	}

	public void setRole_filter(String role_filter) {
		this.role_filter = role_filter;
	}

	public String getTaoken() {
		return taoken;
	}

	public void setTaoken(String taoken) {
		this.taoken = taoken;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
