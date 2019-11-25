package com.hyy.ifm.product.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 自动发布产品编号实体
 * 
 * @author zyp
 * @version 1.0.0
 * @date 2019-07-02 11:47:21
 * Copyright 江苏芒种互联信息科技有限公司  arc All Rights Reserved
 * 官方网站：www.graininear.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */

@Table("user_order")
 public class UserOrder {


   /**
    * 主键Id
    */
   @Id
   private Long id;

   /**
    *
    */
   @Column("user_id")
   private Integer userId;

   /**
    * 对外订单编号
    */
   @Column("order_no")
   private String orderNo;

    @Column("apply_bank_time")
   private Date applyBankTime;

   /**
    * 申请时间
    */
   @Column("apply_time")
   private Date applyTime;

   /**
    * 申请金额
    */
   @Column("apply_amt")
   private Double applyAmt;

   /**
    * 应还款金额
    */
   @Column("repay_amt")
   private Double repayAmt;

   /**
    * 实际还款时间
    */
   @Column("pay_time")
   private Date payTime;

   /**
    * 0: 支付中 1:成功 2：失败
    */
   @Column("status")
   private String status;

   /**
    * 银行卡号
    */
   @Column("bank_no")
   private String bankNo;

   /**
    * 银行名称
    */
   @Column("bank")
   private String bank;

   /**
    * 认证状态  1.活体 2.个人信息 3.银行卡 4.联系人 5.运营商
    */
   @Column("user_auth")
   private String userAuth;

    @Column("pay_amt")
   private Double payAmt;

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    /**
     * 退款申请时间
     */
    @Column("refund_time")
    private Date refundTime;

    public Double getRebackAmt() {
        return rebackAmt;
    }

    public void setRebackAmt(Double rebackAmt) {
        this.rebackAmt = rebackAmt;
    }

    @Column("reback_amt")
    private Double rebackAmt;

    @Column("sys_user_id")
    private Integer sysUserId;

    @Column("refund_amt")
    private Double refundAmt;

    public Double getRefundAmt() {
        return refundAmt;
    }

    public void setRefundAmt(Double refundAmt) {
        this.refundAmt = refundAmt;
    }

   /**
    * 获取主键Id
    *
    * @return id
    */
   public Long getId(){
      return id;
   }

   /**
    * 设置主键Id
    *
    */
   public void setId(Long id){
      this.id = id;
   }

    /**
     * 获取
     *
     * @return
     */
    public Integer getSysUserId(){
        return sysUserId;
    }

    /**
     * 设置
     *
     * @param sysUserId 要设置的
     */
    public void setSysUserId(Integer sysUserId){
        this.sysUserId = sysUserId;
    }

   /**
    * 获取
    *
    * @return
    */
   public Integer getUserId(){
      return userId;
   }

   /**
    * 设置
    *
    * @param userId 要设置的
    */
   public void setUserId(Integer userId){
      this.userId = userId;
   }

   /**
    * 获取对外订单编号
    *
    * @return 对外订单编号
    */
   public String getOrderNo(){
      return orderNo;
   }

   /**
    * 设置对外订单编号
    *
    * @param orderNo 要设置的对外订单编号
    */
   public void setOrderNo(String orderNo){
      this.orderNo = orderNo;
   }

   /**
    * 获取申请时间
    *
    * @return 申请时间
    */
   public Date getApplyTime(){
      return applyTime;
   }

   /**
    * 设置申请时间
    *
    * @param applyTime 要设置的申请时间
    */
   public void setApplyTime(Date applyTime){
      this.applyTime = applyTime;
   }

   /**
    * 获取申请金额
    *
    * @return 申请金额
    */
   public Double getApplyAmt(){
      return applyAmt;
   }

   /**
    * 设置申请金额
    *
    * @param applyAmt 要设置的申请金额
    */
   public void setApplyAmt(Double applyAmt){
      this.applyAmt = applyAmt;
   }

   /**
    * 获取应还款金额
    *
    * @return 应还款金额
    */
   public Double getRepayAmt(){
      return repayAmt;
   }

   /**
    * 设置应还款金额
    *
    * @param repayAmt 要设置的应还款金额
    */
   public void setRepayAmt(Double repayAmt){
      this.repayAmt = repayAmt;
   }

   /**
    * 获取实际还款时间
    *
    * @return 实际还款时间
    */
   public Date getPayTime(){
      return payTime;
   }

   /**
    * 设置实际还款时间
    *
    * @param payTime 要设置的实际还款时间
    */
   public void setPayTime(Date payTime){
      this.payTime = payTime;
   }

   /**
    * 获取0: 支付中 1:成功 2：失败
    *
    * @return 0: 支付中 1:成功 2：失败
    */
   public String getStatus(){
      return status;
   }

   /**
    * 设置0: 支付中 1:成功 2：失败
    *
    * @param status 要设置的0: 支付中 1:成功 2：失败
    */
   public void setStatus(String status){
      this.status = status;
   }

   /**
    * 获取银行卡号
    *
    * @return 银行卡号
    */
   public String getBankNo(){
      return bankNo;
   }

   /**
    * 设置银行卡号
    *
    * @param bankNo 要设置的银行卡号
    */
   public void setBankNo(String bankNo){
      this.bankNo = bankNo;
   }

   /**
    * 获取银行名称
    *
    * @return 银行名称
    */
   public String getBank(){
      return bank;
   }

   /**
    * 设置银行名称
    *
    * @param bank 要设置的银行名称
    */
   public void setBank(String bank){
      this.bank = bank;
   }

   /**
    * 获取认证状态  1.活体 2.个人信息 3.银行卡 4.联系人 5.运营商
    *
    * @return 认证状态  1.活体 2.个人信息 3.银行卡 4.联系人 5.运营商
    */
   public String getUserAuth(){
      return userAuth;
   }

   /**
    * 设置认证状态  1.活体 2.个人信息 3.银行卡 4.联系人 5.运营商
    *
    * @param userAuth 要设置的认证状态  1.活体 2.个人信息 3.银行卡 4.联系人 5.运营商
    */
   public void setUserAuth(String userAuth){
      this.userAuth = userAuth;
   }

   public Date getApplyBankTime() {
      return applyBankTime;
   }

   public void setApplyBankTime(Date applyBankTime) {
      this.applyBankTime = applyBankTime;
   }

   public Double getPayAmt() {
      return payAmt;
   }

   public void setPayAmt(Double payAmt) {
      this.payAmt = payAmt;
   }
}