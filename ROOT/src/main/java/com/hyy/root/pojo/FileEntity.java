package com.hyy.root.pojo;

import java.io.Serializable;

public class FileEntity implements Serializable {
	
	private String file_uri;
	private String id;
	private String type;
	private String oldName;


	
	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getFile_uri() {
		return file_uri;
	}

	public void setFile_uri(String file_uri) {
		this.file_uri = file_uri;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
