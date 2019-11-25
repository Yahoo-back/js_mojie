package com.hyy.root.pojo;

public class Logins extends BasePojo{
	private Login rows;
	
	
	public Login getRows() {
		return rows;
	}


	public void setRows(Login rows) {
		this.rows = rows;
	}


	public static class Login{
		private int userId;
		private String userName;
		private String orgId;
		private String loginId ;
		private String token;
		private String role_filter = "1";
		private String accountType;
		private String version;
		private String sh_type;
		private Integer parent_user_id;	// 主账号ID当改用户为主账号 此为0 ifm_sys_user 外键（子商户关联主账号ID） 
		private String tx_amt;
		private String is_bail_cnd;
		private Integer bail_status;	// 保证状态（0：未保证；1：已保证）
		private String bail_amt; // 保证金
		private String is_bail;// 是否保底
		private String pay_amt;// 是否保底
		private String gg_bail_amt; // 杠杆比例后的金额
		private String gg_rate; // 杠杆比例后的金额
		private String collection_rate; // 杠杆比例后的金额
		private String pay_ratio = "93"; //商户端的支付比例
		
		
		public String getPay_ratio() {
			return pay_ratio;
		}
		public void setPay_ratio(String pay_ratio) {
			this.pay_ratio = pay_ratio;
		}
		public String getIs_bail_cnd() {
			return is_bail_cnd;
		}
		public void setIs_bail_cnd(String is_bail_cnd) {
			this.is_bail_cnd = is_bail_cnd;
		}
		
		/**
		 * @return the bail_status
		 */
		public Integer getBail_status() {
			return bail_status;
		}
		/**
		 * @param bail_status the bail_status to set
		 */
		public void setBail_status(Integer bail_status) {
			this.bail_status = bail_status;
		}
		
		public String getBail_amt() {
			return bail_amt;
		}
		public void setBail_amt(String bail_amt) {
			this.bail_amt = bail_amt;
		}
		public String getIs_bail() {
			return is_bail;
		}
		public void setIs_bail(String is_bail) {
			this.is_bail = is_bail;
		}
		public String getPay_amt() {
			return pay_amt;
		}
		public void setPay_amt(String pay_amt) {
			this.pay_amt = pay_amt;
		}
		public String getGg_bail_amt() {
			return gg_bail_amt;
		}
		public void setGg_bail_amt(String gg_bail_amt) {
			this.gg_bail_amt = gg_bail_amt;
		}
		public String getGg_rate() {
			return gg_rate;
		}
		public void setGg_rate(String gg_rate) {
			this.gg_rate = gg_rate;
		}
		public String getCollection_rate() {
			return collection_rate;
		}
		public void setCollection_rate(String collection_rate) {
			this.collection_rate = collection_rate;
		}
		public String getTx_amt() {
			return tx_amt;
		}
		public void setTx_amt(String tx_amt) {
			this.tx_amt = tx_amt;
		}
		public String getSh_type() {
			return sh_type;
		}
		public void setSh_type(String sh_type) {
			this.sh_type = sh_type;
		}
		
		/**
		 * @return the parent_user_id
		 */
		public Integer getParent_user_id() {
			return parent_user_id;
		}
		/**
		 * @param parent_user_id the parent_user_id to set
		 */
		public void setParent_user_id(Integer parent_user_id) {
			this.parent_user_id = parent_user_id;
		}
		
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
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
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getOrgId() {
			return orgId;
		}
		public void setOrgId(String orgId) {
			this.orgId = orgId;
		}
		public String getLoginId() {
			return loginId;
		}
		public void setLoginId(String loginId) {
			this.loginId = loginId;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		
	}
}
