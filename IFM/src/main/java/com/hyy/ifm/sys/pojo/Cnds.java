package com.hyy.ifm.sys.pojo;


import com.hyy.ifm.common.pojo.BasePojo;
import com.hyy.ifm.util.Constants;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Cnds extends BasePojo {

	private UserBean rows;

	public UserBean getRows() {
		return rows;
	}

	public void setRows(UserBean rows) {
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

	public static class UserBean {
		private String id;
		private String user_name;
		private String user_code;
		private String account_type;
		private String sex;
		private String identity_card;
		private String status;
		private int userId;
		private String userName;
		private String orgId;
		private String jobNum;
		private String birthday;
		private String identityCard;
		private String phone;
		private String companyEmail;
		private String personalEmail;
		private Date onjobDate;
		private String liveAddr;
		private String degree;
		private Date creatDate;
		private String loginId;
		private String userCode;
		private String accountType;
		private String password = Constants.DEFULT_PASSWORD;
		private String oldpassword;
		private int parent_user_id;
		private String bank_name;
		private String bank_no;
		private String bank_branch;
		private String child_no;
		private String identity_no;
		private String mobile_no;
		private String qq;
		private String weixin;
		private String provinceAddress; //省份名称简称

		private String cpa;
		private String cpaType;

		public String getLoanRate() {
			return loanRate;
		}

		public void setLoanRate(String loanRate) {
			this.loanRate = loanRate;
		}

		private String loanRate;


		// 组织机构
		private String orgName;
		private String parentOrgId;
		private String isChild;
		
		// 角色
		private String roleId;
		private String roleName;
		private String roleCode;
		private String org_code;
		private String org_code_parent;

		public Double getSl() {
			return sl;
		}

		public void setSl(Double sl) {
			this.sl = sl;
		}

		private Double sl;
		
		//菜单项
		private String meuns;
		private String meunId;
		//
		private String muenId;
		private String muenName;
		private String url;
		private String xuhao;
		private String parentMuenId;
		private String parentMuenName;

		// 模糊查询条件
		private String user_name_cnd;
		private String user_code_cnd;
		private String account_type_cnd;
		private String sex_cnd;
		private String identity_card_cnd;
		private String status_cnd;
		private String roleId_cnd;
		private String roleName_cnd;
		private String roleCode_cnd;
		private String cm_name_cnd;
		private String user_name_1_cnd;
		private String role_filter;
		private List<String> attchs;
		private String user_name2_cnd;
		private String user_code2_cnd;
		private String bank_no_cnd;   //银行卡号
		private String bank_name_cnd;   //银行名称
		private String picture_quertity_cnd;  //图片数量
		private String operate_date_FROM_cnd;
		private String operate_date_TO_cnd;
		
		
		private String order_range = "0";
		private String sh_type;
		
		//基础数据配置模糊查询
		private String id_cnd;
		private String dataType_cnd;
		private String key_cnd;
		private String value_cnd;
		private String orderBy_cnd;
		private String outData_cnd;
		private String parentId_cnd;
		private String dict_desc_cnd;
		
		//基础数据配置
		
		private String dataType;
		private String key;
		private String value;
		private String orderBy;
		private String outData;
		private int parentId;
		private String dict_desc;
		
		//业务聚流
		 private String areaOut;
	     private String urlIos;
	     private String gender;
	     private String orgLogo;
	     private String areain;
	     private String productName;
	     private String amountLow;
	     private String loanPrompt;
	     private String urlHtml5;
	     private String interest;
	     private String platStatus;
	     private String amountHigh;
	     private String housingFund;
	     private String productType;
	     private String socialInsurance;
	     private String instalment;
	     private String mobile;
	     private String refuseStatus;
	     private String urlAndroid;
	     private String updateTime;
	     private String productDesc;
	     private String ageHigh;
	     private String matchingDegree;
	     private String createTime;
	     private String ageLow;
	     private String idcard;
	     private String eBusiness;

//		public Double getSl() {
//			return sl;
//		}
//
//		public void setSl(Double sl) {
//			this.sl = sl;
//		}
//
//		private Double sl;
	     
	     
		private String custom_name_cnd;
		private String loan_from_cnd;
		private String loan_product_cnd;
		private String phone_cnd;
		private String school_name_cnd;
		private String create_date_cnd;
		private String loaning_status_cnd;
		
		private String custom_name;
		private String loan_from;
		private String loan_product;
		private String school_name;
		private String create_date;
		private String loaning_status;
		
		private String qryType_cnd;
		private String is_bail_cnd;
		private String bail_amt; // 保证金
		private String is_bail;// 是否保底
		private String pay_amt;// 是否保底
		private String gg_bail_amt; // 杠杆比例后的金额
		private String gg_rate; // 杠杆比例后的金额
		private String collection_rate; // 杠杆比例后的金额
		private String is_zhima; // 是否要跳过芝麻分认证
		private String pay_ratio; // 支付比例
		private String invitation_code;
		private String address; // 获取本地Ip地址
		private String clientAddress;	// 客户端地址（本地|远程）
		private String serverAddress;	// 服务端地址（远程）
		private int is_activation;//激活次数		
		private BigDecimal rebate_ratio;//返点比例
		public String getQq() {
			return qq;
		}

		public String getCpaType() {
			return cpaType;
		}

		public void setCpaType(String cpaType) {
			this.cpaType = cpaType;
		}

		public void setQq(String qq) {
			this.qq = qq;
		}

		public String getWeixin() {
			return weixin;
		}

		public void setWeixin(String weixin) {
			this.weixin = weixin;
		}
		public String getOperate_date_FROM_cnd() {
			return operate_date_FROM_cnd;
		}

		public void setOperate_date_FROM_cnd(String operate_date_FROM_cnd) {
			this.operate_date_FROM_cnd = operate_date_FROM_cnd;
		}

		public String getOperate_date_TO_cnd() {
			return operate_date_TO_cnd;
		}

		public void setOperate_date_TO_cnd(String operate_date_TO_cnd) {
			this.operate_date_TO_cnd = operate_date_TO_cnd;
		}

		public int getIs_activation() {
		    return is_activation;
		}

		public void setIs_activation(int is_activation) {
		    this.is_activation = is_activation;
		}

		public String getInvitation_code() {
			return invitation_code;
		}

		public void setInvitation_code(String invitation_code) {
			this.invitation_code = invitation_code;
		}

		public String getProvinceAddress() {
			return provinceAddress;
		}

		public void setProvinceAddress(String provinceAddress) {
			this.provinceAddress = provinceAddress;
		}

		public String getBank_no_cnd() {
			return bank_no_cnd;
		}

		public void setBank_no_cnd(String bank_no_cnd) {
			this.bank_no_cnd = bank_no_cnd;
		}

		public String getBank_name_cnd() {
			return bank_name_cnd;
		}

		public void setBank_name_cnd(String bank_name_cnd) {
			this.bank_name_cnd = bank_name_cnd;
		}

		public String getPicture_quertity_cnd() {
			return picture_quertity_cnd;
		}

		public void setPicture_quertity_cnd(String picture_quertity_cnd) {
			this.picture_quertity_cnd = picture_quertity_cnd;
		}

		public String getIs_zhima() {
			return is_zhima;
		}

		public void setIs_zhima(String is_zhima) {
			this.is_zhima = is_zhima;
		}

		public String getPay_ratio() {
			return pay_ratio;
		}

		public void setPay_ratio(String pay_ratio) {
			this.pay_ratio = pay_ratio;
		}

		public String getCollection_rate() {
			return collection_rate;
		}

		public void setCollection_rate(String collection_rate) {
			this.collection_rate = collection_rate;
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

		public String getIs_bail_cnd() {
			return is_bail_cnd;
		}

		public void setIs_bail_cnd(String is_bail_cnd) {
			this.is_bail_cnd = is_bail_cnd;
		}

		public String getQryType_cnd() {
			return qryType_cnd;
		}

		public void setQryType_cnd(String qryType_cnd) {
			this.qryType_cnd = qryType_cnd;
		}

		public String getIdentity_no() {
			return identity_no;
		}

		public void setIdentity_no(String identity_no) {
			this.identity_no = identity_no;
		}

		public String getMobile_no() {
			return mobile_no;
		}

		public void setMobile_no(String mobile_no) {
			this.mobile_no = mobile_no;
		}

		public String getCustom_name() {
			return custom_name;
		}

		public void setCustom_name(String custom_name) {
			this.custom_name = custom_name;
		}

		public String getLoan_from() {
			return loan_from;
		}

		public void setLoan_from(String loan_from) {
			this.loan_from = loan_from;
		}

		public String getLoan_product() {
			return loan_product;
		}

		public void setLoan_product(String loan_product) {
			this.loan_product = loan_product;
		}

		public String getSchool_name() {
			return school_name;
		}

		public void setSchool_name(String school_name) {
			this.school_name = school_name;
		}

		public String getCreate_date() {
			return create_date;
		}

		public void setCreate_date(String create_date) {
			this.create_date = create_date;
		}

		public String getLoaning_status() {
			return loaning_status;
		}

		public void setLoaning_status(String loaning_status) {
			this.loaning_status = loaning_status;
		}

		public String getCustom_name_cnd() {
			return custom_name_cnd;
		}

		public void setCustom_name_cnd(String custom_name_cnd) {
			this.custom_name_cnd = custom_name_cnd;
		}

		public String getLoan_from_cnd() {
			return loan_from_cnd;
		}

		public void setLoan_from_cnd(String loan_from_cnd) {
			this.loan_from_cnd = loan_from_cnd;
		}

		public String getLoan_product_cnd() {
			return loan_product_cnd;
		}

		public void setLoan_product_cnd(String loan_product_cnd) {
			this.loan_product_cnd = loan_product_cnd;
		}

		public String getPhone_cnd() {
			return phone_cnd;
		}

		public void setPhone_cnd(String phone_cnd) {
			this.phone_cnd = phone_cnd;
		}

		public String getSchool_name_cnd() {
			return school_name_cnd;
		}

		public void setSchool_name_cnd(String school_name_cnd) {
			this.school_name_cnd = school_name_cnd;
		}

		public String getCreate_date_cnd() {
			return create_date_cnd;
		}

		public void setCreate_date_cnd(String create_date_cnd) {
			this.create_date_cnd = create_date_cnd;
		}

		public String getLoaning_status_cnd() {
			return loaning_status_cnd;
		}

		public void setLoaning_status_cnd(String loaning_status_cnd) {
			this.loaning_status_cnd = loaning_status_cnd;
		}

		public String getAreaOut() {
			return areaOut;
		}

		public void setAreaOut(String areaOut) {
			this.areaOut = areaOut;
		}

		public String getUrlIos() {
			return urlIos;
		}

		public void setUrlIos(String urlIos) {
			this.urlIos = urlIos;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getOrgLogo() {
			return orgLogo;
		}

		public void setOrgLogo(String orgLogo) {
			this.orgLogo = orgLogo;
		}

		public String getAreain() {
			return areain;
		}

		public void setAreain(String areain) {
			this.areain = areain;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getAmountLow() {
			return amountLow;
		}

		public void setAmountLow(String amountLow) {
			this.amountLow = amountLow;
		}

		public String getLoanPrompt() {
			return loanPrompt;
		}

		public void setLoanPrompt(String loanPrompt) {
			this.loanPrompt = loanPrompt;
		}

		public String getUrlHtml5() {
			return urlHtml5;
		}

		public void setUrlHtml5(String urlHtml5) {
			this.urlHtml5 = urlHtml5;
		}

		public String getInterest() {
			return interest;
		}

		public void setInterest(String interest) {
			this.interest = interest;
		}

		public String getPlatStatus() {
			return platStatus;
		}

		public void setPlatStatus(String platStatus) {
			this.platStatus = platStatus;
		}

		public String getAmountHigh() {
			return amountHigh;
		}

		public void setAmountHigh(String amountHigh) {
			this.amountHigh = amountHigh;
		}

		public String getHousingFund() {
			return housingFund;
		}

		public void setHousingFund(String housingFund) {
			this.housingFund = housingFund;
		}

		public String getProductType() {
			return productType;
		}

		public void setProductType(String productType) {
			this.productType = productType;
		}

		public String getSocialInsurance() {
			return socialInsurance;
		}

		public void setSocialInsurance(String socialInsurance) {
			this.socialInsurance = socialInsurance;
		}

		public String getInstalment() {
			return instalment;
		}

		public void setInstalment(String instalment) {
			this.instalment = instalment;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getRefuseStatus() {
			return refuseStatus;
		}

		public void setRefuseStatus(String refuseStatus) {
			this.refuseStatus = refuseStatus;
		}

		public String getUrlAndroid() {
			return urlAndroid;
		}

		public void setUrlAndroid(String urlAndroid) {
			this.urlAndroid = urlAndroid;
		}

		public String getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}

		public String getProductDesc() {
			return productDesc;
		}

		public void setProductDesc(String productDesc) {
			this.productDesc = productDesc;
		}

		public String getAgeHigh() {
			return ageHigh;
		}

		public void setAgeHigh(String ageHigh) {
			this.ageHigh = ageHigh;
		}

		public String getMatchingDegree() {
			return matchingDegree;
		}

		public void setMatchingDegree(String matchingDegree) {
			this.matchingDegree = matchingDegree;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public String getAgeLow() {
			return ageLow;
		}

		public void setAgeLow(String ageLow) {
			this.ageLow = ageLow;
		}

		public String getIdcard() {
			return idcard;
		}

		public void setIdcard(String idcard) {
			this.idcard = idcard;
		}

		public String geteBusiness() {
			return eBusiness;
		}

		public void seteBusiness(String eBusiness) {
			this.eBusiness = eBusiness;
		}

		public String getDataType() {
			return dataType;
		}

		public void setDataType(String dataType) {
			this.dataType = dataType;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getOrderBy() {
			return orderBy;
		}

		public void setOrderBy(String orderBy) {
			this.orderBy = orderBy;
		}

		public String getOutData() {
			return outData;
		}

		public void setOutData(String outData) {
			this.outData = outData;
		}

		public int getParentId() {
			return parentId;
		}

		public void setParentId(int parentId) {
			this.parentId = parentId;
		}

		public String getDict_desc() {
			return dict_desc;
		}

		public void setDict_desc(String dict_desc) {
			this.dict_desc = dict_desc;
		}
		
		public String getUser_name2_cnd() {
			return user_name2_cnd;
		}

		public void setUser_name2_cnd(String user_name2_cnd) {
			this.user_name2_cnd = user_name2_cnd;
		}

		public String getUser_code2_cnd() {
			return user_code2_cnd;
		}

		public void setUser_code2_cnd(String user_code2_cnd) {
			this.user_code2_cnd = user_code2_cnd;
		}

		public String getChild_no() {
			return child_no;
		}

		public void setChild_no(String child_no) {
			this.child_no = child_no;
		}

		public int getParent_user_id() {
			return parent_user_id;
		}

		public void setParent_user_id(int parent_user_id) {
			this.parent_user_id = parent_user_id;
		}

		public String getBank_name() {
			return bank_name;
		}

		public void setBank_name(String bank_name) {
			this.bank_name = bank_name;
		}

		public String getBank_no() {
			return bank_no;
		}

		public void setBank_no(String bank_no) {
			this.bank_no = bank_no;
		}

		public String getBank_branch() {
			return bank_branch;
		}

		public void setBank_branch(String bank_branch) {
			this.bank_branch = bank_branch;
		}

		public String getSh_type() {
			return sh_type;
		}

		public void setSh_type(String sh_type) {
			this.sh_type = sh_type;
		}

		
		
		public String getOrder_range() {
			return order_range;
		}

		public void setOrder_range(String order_range) {
			this.order_range = order_range;
		}

		public String getUser_name_1_cnd() {
			return user_name_1_cnd;
		}

		public void setUser_name_1_cnd(String user_name_1_cnd) {
			this.user_name_1_cnd = user_name_1_cnd;
		}

		public String getCm_name_cnd() {
			return cm_name_cnd;
		}

		public void setCm_name_cnd(String cm_name_cnd) {
			this.cm_name_cnd = cm_name_cnd;
		}

		public List<String> getAttchs() {
			return attchs;
		}

		public void setAttchs(List<String> attchs) {
			this.attchs = attchs;
		}

		public String getOrg_code_parent() {
			return org_code_parent;
		}

		public void setOrg_code_parent(String org_code_parent) {
			this.org_code_parent = org_code_parent;
		}

		public String getOrg_code() {
			return org_code;
		}

		public void setOrg_code(String org_code) {
			this.org_code = org_code;
		}

		public String getRoleCode() {
			return roleCode;
		}

		public void setRoleCode(String roleCode) {
			this.roleCode = roleCode;
		}

		public String getRoleCode_cnd() {
			return roleCode_cnd;
		}

		public void setRoleCode_cnd(String roleCode_cnd) {
			this.roleCode_cnd = roleCode_cnd;
		}

		public String getRole_filter() {
			return role_filter;
		}

		public void setRole_filter(String role_filter) {
			this.role_filter = role_filter;
		}

		public String getIsChild() {
			return isChild;
		}

		public void setIsChild(String isChild) {
			this.isChild = isChild;
		}
		public String getOldpassword() {
			return oldpassword;
		}

		public void setOldpassword(String oldpassword) {
			this.oldpassword = oldpassword;
		}

		public String getMeunId() {
			return meunId;
		}

		public void setMeunId(String meunId) {
			this.meunId = meunId;
		}

		public String getMeuns() {
			return meuns;
		}

		public void setMeuns(String meuns) {
			this.meuns = meuns;
		}

		public String getRoleId() {
			return roleId;
		}

		public void setRoleId(String roleId) {
			this.roleId = roleId;
		}

		public String getRoleName() {
			return roleName;
		}

		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

		public String getRoleId_cnd() {
			return roleId_cnd;
		}

		public void setRoleId_cnd(String roleId_cnd) {
			this.roleId_cnd = roleId_cnd;
		}

		public String getRoleName_cnd() {
			return roleName_cnd;
		}

		public void setRoleName_cnd(String roleName_cnd) {
			this.roleName_cnd = roleName_cnd;
		}

		public String getOrgName() {
			return orgName;
		}

		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}

		public String getParentOrgId() {
			return parentOrgId;
		}

		public void setParentOrgId(String parentOrgId) {
			this.parentOrgId = parentOrgId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getAccountType() {
			return accountType;
		}

		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}

		public String getUserCode() {
			return userCode;
		}

		public void setUserCode(String userCode) {
			this.userCode = userCode;
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

		public String getJobNum() {
			return jobNum;
		}

		public void setJobNum(String jobNum) {
			this.jobNum = jobNum;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getIdentityCard() {
			return identityCard;
		}

		public void setIdentityCard(String identityCard) {
			this.identityCard = identityCard;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getCompanyEmail() {
			return companyEmail;
		}

		public void setCompanyEmail(String companyEmail) {
			this.companyEmail = companyEmail;
		}

		public String getPersonalEmail() {
			return personalEmail;
		}

		public void setPersonalEmail(String personalEmail) {
			this.personalEmail = personalEmail;
		}

		public Date getOnjobDate() {
			return onjobDate;
		}

		public void setOnjobDate(Date onjobDate) {
			this.onjobDate = onjobDate;
		}

		public String getLiveAddr() {
			return liveAddr;
		}

		public void setLiveAddr(String liveAddr) {
			this.liveAddr = liveAddr;
		}

		public String getDegree() {
			return degree;
		}

		public void setDegree(String degree) {
			this.degree = degree;
		}

		public Date getCreatDate() {
			return creatDate;
		}

		public void setCreatDate(Date creatDate) {
			this.creatDate = creatDate;
		}

		public String getLoginId() {
			return loginId;
		}

		public void setLoginId(String loginId) {
			this.loginId = loginId;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUser_name() {
			return user_name;
		}

		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}

		public String getUser_code() {
			return user_code;
		}

		public void setUser_code(String user_code) {
			this.user_code = user_code;
		}

		public String getAccount_type() {
			return account_type;
		}

		public void setAccount_type(String account_type) {
			this.account_type = account_type;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getIdentity_card() {
			return identity_card;
		}

		public void setIdentity_card(String identity_card) {
			this.identity_card = identity_card;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getUser_name_cnd() {
			return user_name_cnd;
		}

		public void setUser_name_cnd(String user_name_cnd) {
			this.user_name_cnd = user_name_cnd;
		}

		public String getUser_code_cnd() {
			return user_code_cnd;
		}

		public void setUser_code_cnd(String user_code_cnd) {
			this.user_code_cnd = user_code_cnd;
		}

		public String getAccount_type_cnd() {
			return account_type_cnd;
		}

		public void setAccount_type_cnd(String account_type_cnd) {
			this.account_type_cnd = account_type_cnd;
		}

		public String getSex_cnd() {
			return sex_cnd;
		}

		public void setSex_cnd(String sex_cnd) {
			this.sex_cnd = sex_cnd;
		}

		public String getIdentity_card_cnd() {
			return identity_card_cnd;
		}

		public void setIdentity_card_cnd(String identity_card_cnd) {
			this.identity_card_cnd = identity_card_cnd;
		}

		public String getStatus_cnd() {
			return status_cnd;
		}

		public void setStatus_cnd(String status_cnd) {
			this.status_cnd = status_cnd;
		}

		public String getMuenId() {
			return muenId;
		}

		public void setMuenId(String muenId) {
			this.muenId = muenId;
		}

		public String getMuenName() {
			return muenName;
		}

		public void setMuenName(String muenName) {
			this.muenName = muenName;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getXuhao() {
			return xuhao;
		}

		public void setXuhao(String xuhao) {
			this.xuhao = xuhao;
		}

		public String getParentMuenId() {
			return parentMuenId;
		}

		public void setParentMuenId(String parentMuenId) {
			this.parentMuenId = parentMuenId;
		}

		public String getParentMuenName() {
			return parentMuenName;
		}

		public void setParentMuenName(String parentMuenName) {
			this.parentMuenName = parentMuenName;
		}

		public String getId_cnd() {
			return id_cnd;
		}

		public void setId_cnd(String id_cnd) {
			this.id_cnd = id_cnd;
		}

		public String getDataType_cnd() {
			return dataType_cnd;
		}

		public void setDataType_cnd(String dataType_cnd) {
			this.dataType_cnd = dataType_cnd;
		}

		public String getKey_cnd() {
			return key_cnd;
		}

		public void setKey_cnd(String key_cnd) {
			this.key_cnd = key_cnd;
		}

		public String getValue_cnd() {
			return value_cnd;
		}

		public void setValue_cnd(String value_cnd) {
			this.value_cnd = value_cnd;
		}

		public String getOrderBy_cnd() {
			return orderBy_cnd;
		}

		public void setOrderBy_cnd(String orderBy_cnd) {
			this.orderBy_cnd = orderBy_cnd;
		}

		public String getOutData_cnd() {
			return outData_cnd;
		}

		public void setOutData_cnd(String outData_cnd) {
			this.outData_cnd = outData_cnd;
		}

		public String getParentId_cnd() {
			return parentId_cnd;
		}

		public void setParentId_cnd(String parentId_cnd) {
			this.parentId_cnd = parentId_cnd;
		}

		public String getDict_desc_cnd() {
			return dict_desc_cnd;
		}

		public void setDict_desc_cnd(String dict_desc_cnd) {
			this.dict_desc_cnd = dict_desc_cnd;
		}
		
		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public BigDecimal getRebate_ratio() {
			return rebate_ratio;
		}

		public void setRebate_ratio(BigDecimal rebate_ratio) {
			this.rebate_ratio = rebate_ratio;
		}
		
		/**
		 * @return the clientAddress
		 */
		public String getClientAddress() {
			return clientAddress;
		}
		/**
		 * @param clientAddress the clientAddress to set
		 */
		public void setClientAddress(String clientAddress) {
			this.clientAddress = clientAddress;
		}
		
		/**
		 * @return the serverAddress
		 */
		public String getServerAddress() {
			return serverAddress;
		}
		/**
		 * @param serverAddress the serverAddress to set
		 */
		public void setServerAddress(String serverAddress) {
			this.serverAddress = serverAddress;
		}

		public String getCpa() {
			return cpa;
		}

		public void setCpa(String cpa) {
			this.cpa = cpa;
		}
	}
	
}