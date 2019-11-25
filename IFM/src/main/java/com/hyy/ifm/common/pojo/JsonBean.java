package com.hyy.ifm.common.pojo;

public  class JsonBean {
	
	private String cmd;
	private int pageNum;
	private int pageSize = 10;
	private String userCode;
	private String token;

	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
