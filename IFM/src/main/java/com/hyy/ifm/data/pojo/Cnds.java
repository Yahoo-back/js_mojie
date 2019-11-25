package com.hyy.ifm.data.pojo;

import com.hyy.ifm.common.pojo.BasePojo;

public class Cnds extends BasePojo {

	private DataBean rows;

	public DataBean getRows() {
		return rows;
	}

	public void setRows(DataBean rows) {
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

	public static class DataBean {
		private String id;
		private String remark_form;
		private String source;

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getRemark_form() {
			return remark_form;
		}

		public void setRemark_form(String remark_form) {
			this.remark_form = remark_form;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		private double change_rate;
		private String report_id;
		private String daysFrom;
		private String daysTo;
		private String type;
		private String productId;
		private String reg_count_form;
		private String loan_count_form;
		private String settle_way;
		private String settle_money_form;
		private String serial_number;
		private String settle_certificatec;
		private String settle_time;
		private String file_uri;
		private String user_code_cnd;

		private String visit_count_form;;

		public String getFile_uri() {
			return file_uri;
		}

		public void setFile_uri(String file_uri) {
			this.file_uri = file_uri;
		}

		public String getUser_code_cnd() {
			return user_code_cnd;
		}

		public void setUser_code_cnd(String user_code_cnd) {
			this.user_code_cnd = user_code_cnd;
		}

		public double getChange_rate() {
			return change_rate;
		}

		public void setChange_rate(double change_rate) {
			this.change_rate = change_rate;
		}

		public String getSettle_way() {
			return settle_way;
		}

		public void setSettle_way(String settle_way) {
			this.settle_way = settle_way;
		}

		public String getSerial_number() {
			return serial_number;
		}

		public void setSerial_number(String serial_number) {
			this.serial_number = serial_number;
		}

		public String getSettle_certificatec() {
			return settle_certificatec;
		}

		public void setSettle_certificatec(String settle_certificatec) {
			this.settle_certificatec = settle_certificatec;
		}

		public String getSettle_time() {
			return settle_time;
		}

		public void setSettle_time(String settle_time) {
			this.settle_time = settle_time;
		}

		public String getSettle_money_form() {
			return settle_money_form;
		}

		public void setSettle_money_form(String settle_money_form) {
			this.settle_money_form = settle_money_form;
		}

		public String getReport_id() {
			return report_id;
		}

		public void setReport_id(String report_id) {
			this.report_id = report_id;
		}

		public String getReg_count_form() {
			return reg_count_form;
		}

		public void setReg_count_form(String reg_count_form) {
			this.reg_count_form = reg_count_form;
		}

		public String getLoan_count_form() {
			return loan_count_form;
		}

		public void setLoan_count_form(String loan_count_form) {
			this.loan_count_form = loan_count_form;
		}

		public String getDaysFrom() {
			return daysFrom;
		}

		public void setDaysFrom(String daysFrom) {
			this.daysFrom = daysFrom;
		}

		public String getDaysTo() {
			return daysTo;
		}

		public void setDaysTo(String daysTo) {
			this.daysTo = daysTo;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		/**
		 * 模糊查询条件
		 */
		private String name_cnd;
		private String product_name_cnd;
		private String classify_cnd;
		private String visit_time_FROM_cnd;
		private String visit_time_TO_cnd;
		private String settle_state_cnd;
		private String source_cnd;
		private String company_cnd;
		private String settle_way_cnd;

		public String getSettle_way_cnd() {
			return settle_way_cnd;
		}

		public void setSettle_way_cnd(String settle_way_cnd) {
			this.settle_way_cnd = settle_way_cnd;
		}

		public String getCompany_cnd() {
			return company_cnd;
		}

		public void setCompany_cnd(String company_cnd) {
			this.company_cnd = company_cnd;
		}

		public String getSource_cnd() {
			return source_cnd;
		}

		public void setSource_cnd(String source_cnd) {
			this.source_cnd = source_cnd;
		}

		public String getName_cnd() {
			return name_cnd;
		}

		public void setName_cnd(String name_cnd) {
			this.name_cnd = name_cnd;
		}

		public String getSettle_state_cnd() {
			return settle_state_cnd;
		}

		public void setSettle_state_cnd(String settle_state_cnd) {
			this.settle_state_cnd = settle_state_cnd;
		}

		public String getProduct_name_cnd() {
			return product_name_cnd;
		}

		public void setProduct_name_cnd(String product_name_cnd) {
			this.product_name_cnd = product_name_cnd;
		}

		public String getClassify_cnd() {
			return classify_cnd;
		}

		public void setClassify_cnd(String classify_cnd) {
			this.classify_cnd = classify_cnd;
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

		public String getVisit_count_form() {
			return visit_count_form;
		}

		public void setVisit_count_form(String visit_count_form) {
			this.visit_count_form = visit_count_form;
		}
	}
	
}