package com.hyy.ifm.business.pojo;

import com.hyy.ifm.common.pojo.BasePojo;

public class Cnds extends BasePojo {

	private BusinessBean rows;

	public BusinessBean getRows() {
		return rows;
	}

	public void setRows(BusinessBean rows) {
		this.rows = rows;
	}

	private String fuzzyCnd;

	public String getFuzzyCnd() {
		return fuzzyCnd;
	}

	public void setFuzzyCnd(String fuzzyCnd) {
		this.fuzzyCnd = fuzzyCnd;
	}

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static class BusinessBean {
		/**
		 * 模糊查询条件
		 */
		private String name_cnd;
		private String mobile_cnd;
		private String create_time_FROM_cnd;
		private String create_time_TO_cnd;

		public String getName_cnd() {
			return name_cnd;
		}

		public void setName_cnd(String name_cnd) {
			this.name_cnd = name_cnd;
		}

		public String getMobile_cnd() {
			return mobile_cnd;
		}

		public void setMobile_cnd(String mobile_cnd) {
			this.mobile_cnd = mobile_cnd;
		}

		public String getCreate_time_FROM_cnd() {
			return create_time_FROM_cnd;
		}

		public void setCreate_time_FROM_cnd(String create_time_FROM_cnd) {
			this.create_time_FROM_cnd = create_time_FROM_cnd;
		}

		public String getCreate_time_TO_cnd() {
			return create_time_TO_cnd;
		}

		public void setCreate_time_TO_cnd(String create_time_TO_cnd) {
			this.create_time_TO_cnd = create_time_TO_cnd;
		}
	}
	
}