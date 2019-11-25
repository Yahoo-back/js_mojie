package com.hyy.ifm.customer.pojo;

import com.hyy.ifm.common.pojo.BasePojo;

public class Cnds extends BasePojo {

	private CustomerBean rows;

	public CustomerBean getRows() {
		return rows;
	}

	public void setRows(CustomerBean rows) {
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

	public static class CustomerBean {
		private String id;
		private String status;
		private String file_uri;
		private String money;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getFile_uri() {
			return file_uri;
		}

		public void setFile_uri(String file_uri) {
			this.file_uri = file_uri;
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		/**
		 * 模糊查询条件
		 */
		private String mobile_cnd;
		private String user_name_cnd;
		private String id_card_cnd;
		private String bank_card_cnd;
		private String create_time_FROM_cnd;
		private String create_time_TO_cnd;
		private String status_cnd;
		private String user_auth_cnd;
		private String source_cnd;
		private String name_cnd;
		private String visit_time_FROM_cnd;
		private String visit_time_TO_cnd;
		private String pay_time_FROM_cnd;
		private String pay_time_TO_cnd;
		private String user_name;
		private String id_card;

		public String getUser_name() {
			return user_name;
		}

		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}

		public String getId_card() {
			return id_card;
		}

		public void setId_card(String id_card) {
			this.id_card = id_card;
		}

		public String getRequest_no_cnd() {
			return request_no_cnd;
		}

		public void setRequest_no_cnd(String request_no_cnd) {
			this.request_no_cnd = request_no_cnd;
		}

		private String request_no_cnd;


		public String getPay_time_TO_cnd() {
			return pay_time_TO_cnd;
		}

		public void setPay_time_TO_cnd(String pay_time_TO_cnd) {
			this.pay_time_TO_cnd = pay_time_TO_cnd;
		}

		public String getPay_time_FROM_cnd() {
			return pay_time_FROM_cnd;
		}

		public void setPay_time_FROM_cnd(String pay_time_FROM_cnd) {
			this.pay_time_FROM_cnd = pay_time_FROM_cnd;
		}

		public String getVisit_time_FROM_cnd() {
			return visit_time_FROM_cnd;
		}

		public void setVisit_time_FROM_cnd(String visit_time_FROM_cnd) {
			this.visit_time_FROM_cnd = visit_time_FROM_cnd;
		}

		public String getVisit_time_TO_cnd() {
			return visit_time_TO_cnd;
		}

		public void setVisit_time_TO_cnd(String visit_time_TO_cnd) {
			this.visit_time_TO_cnd = visit_time_TO_cnd;
		}

		public String getSource_cnd() {
			return source_cnd;
		}

		public void setSource_cnd(String source_cnd) {
			this.source_cnd = source_cnd;
		}

		public String getStatus_cnd() {
			return status_cnd;
		}

		public void setStatus_cnd(String status_cnd) {
			this.status_cnd = status_cnd;
		}

		public String getUser_name_cnd() {
			return user_name_cnd;
		}

		public void setUser_name_cnd(String user_name_cnd) {
			this.user_name_cnd = user_name_cnd;
		}

		public String getId_card_cnd() {
			return id_card_cnd;
		}

		public void setId_card_cnd(String id_card_cnd) {
			this.id_card_cnd = id_card_cnd;
		}

		public String getBank_card_cnd() {
			return bank_card_cnd;
		}

		public void setBank_card_cnd(String bank_card_cnd) {
			this.bank_card_cnd = bank_card_cnd;
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

		public String getUser_auth_cnd() {
			return user_auth_cnd;
		}

		public void setUser_auth_cnd(String user_auth_cnd) {
			this.user_auth_cnd = user_auth_cnd;
		}

		public String getName_cnd() {
			return name_cnd;
		}

		public void setName_cnd(String name_cnd) {
			this.name_cnd = name_cnd;
		}

	}
	
}