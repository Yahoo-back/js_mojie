package com.hyy.ifm.product.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

@Table("loan_order")
public class LoanOrder {


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPayFrom() {
        return payFrom;
    }

    public void setPayFrom(String payFrom) {
        this.payFrom = payFrom;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column("id")
    private String id;
    @Column("user_name")
    private String userName;
    @Column("user_id")
    private String userId;
    @Column("bank_no")
    private String bankNo;
    @Column("bank")
    private String bank;
    @Column("total_money")
    private String totalMoney;
    @Column("status")
    private String status;
    @Column("create_time")
    private Date createTime;
    @Column("pay_time")
    private Date payTime;
    @Column("order_id")
    private int orderId;
    @Column("identity_no")
    private String identityNo;
    @Column("mobile_no")
    private String mobileNo;
    @Column("pay_from")
    private String payFrom;
    @Column("pic_path")
    private String picPath;
    @Column("fail_reason")
    private String failReason;







}