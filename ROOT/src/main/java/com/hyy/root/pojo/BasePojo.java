package com.hyy.root.pojo;

public abstract class BasePojo {
	protected String cmd;
	protected String token;
	protected String resultNode;

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getResultNode() {
		return resultNode;
	}

	public void setResultNode(String resultNode) {
		this.resultNode = resultNode;
	}

}
