package com.hyy.root.pojo;

import java.util.List;

public class Muens extends BasePojo{
	private List<Muen> rows;
	
	
	public List<Muen> getRows() {
		return rows;
	}


	public void setRows(List<Muen> rows) {
		this.rows = rows;
	}


	public static class Muen{
		private String muenid;
		private String muen_name;
		private String parent_muen_id;
		private String xh;
		private String status;
		private String uri;
		
		private int id;
		private String file_name;
		private String file_uri;
		private int appr_id;
		private String icon;
		
		
		
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getFile_name() {
			return file_name;
		}
		public void setFile_name(String file_name) {
			this.file_name = file_name;
		}
		public String getFile_uri() {
			return file_uri;
		}
		public void setFile_uri(String file_uri) {
			this.file_uri = file_uri;
		}
		public int getAppr_id() {
			return appr_id;
		}
		public void setAppr_id(int appr_id) {
			this.appr_id = appr_id;
		}
		public String getMuenid() {
			return muenid;
		}
		public void setMuenid(String muenid) {
			this.muenid = muenid;
		}
		public String getMuen_name() {
			return muen_name;
		}
		public void setMuen_name(String muen_name) {
			this.muen_name = muen_name;
		}
		public String getParent_muen_id() {
			return parent_muen_id;
		}
		public void setParent_muen_id(String parent_muen_id) {
			this.parent_muen_id = parent_muen_id;
		}
		public String getXh() {
			return xh;
		}
		public void setXh(String xh) {
			this.xh = xh;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getUri() {
			return uri;
		}
		public void setUri(String uri) {
			this.uri = uri;
		}
	}
}
