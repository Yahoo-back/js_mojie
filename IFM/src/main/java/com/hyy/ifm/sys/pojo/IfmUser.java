package com.hyy.ifm.sys.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

@Table("IFM_SYS_USER")
public class IfmUser {
	@Id
	private Integer userId;
	@Column("USER_NAME")
	private String userName;
	@Column("ORG_ID")
	private String orgId;
	@Column("JOB_NUM")
	private String jobNum;
	@Column("SEX")
	private String sex;
	@Column("DEGREE")
	private String degree;
	@Column("IDENTITY_CARD")
	private String identityCard;
	@Column("PHONE")
	private String phone;
	@Column("COMPANY_EMAIL")
	private String companyEmail;
	@Column("PERSONAL_EMAIL")
	private String personalEmail;
	@Column("LIVE_ADDR")
	private String liveAddr;
	@Column("CREAT_DATE")
	private Date creatDate;
	@Column("LGN_ID")
	private String loginId;
	@Column("STATUS")
	private String status;
	@Column("ONJOBDATE")
	private Date onjobDate;
	@Column("department")
	private String department;
	@Column("QQ")
	private String qq;
	@Column("WEIXIN")
	private String weixin;
	@Column("cpaType")
	private String cpaType;

	public String getLoanRate() {
		return loanRate;
	}

	public void setLoanRate(String loanRate) {
		this.loanRate = loanRate;
	}

	@Column("loanRate")
	private String loanRate;

	public String getCpaType() {
		return cpaType;
	}

	public void setCpaType(String cpaType) {
		this.cpaType = cpaType;
	}

	public String getCpa() {
		return cpa;
	}

	public void setCpa(String cpa) {
		this.cpa = cpa;
	}

	@Column("cpa")
	private String cpa;

	public String getQq() {
		return qq;
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

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getOnjobDate() {
		return onjobDate;
	}
	public void setOnjobDate(Date onjobDate) {
		this.onjobDate = onjobDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
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
	public String getLiveAddr() {
		return liveAddr;
	}
	public void setLiveAddr(String liveAddr) {
		this.liveAddr = liveAddr;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
