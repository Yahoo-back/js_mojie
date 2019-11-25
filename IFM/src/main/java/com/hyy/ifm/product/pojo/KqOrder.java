package com.hyy.ifm.product.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

@Table("kq_order")
public class KqOrder {
    @Id
    private Integer id;
    @Column("user_id")
    private Integer userId;
    @Column("type")
    private String type;
    @Column("outer_id")
    private Integer outerId;
    @Column("request_no")
    private String requestNo;
    @Column("user_ip")
    private String userIp;
    @Column("amt")
    private Double amt;
    @Column("protocol_no")
    private String protocolNo;
    @Column("create_time")
    private Date createTime;
    @Column("status")
    private String status;
    @Column("fuion_order_no")
    private String fuionOrderNo;
    @Column("pay_date")
    private Date payDate;
    @Column("fail_reason")
    private String failReason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getOuterId() {
        return outerId;
    }

    public void setOuterId(Integer outerId) {
        this.outerId = outerId;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo == null ? null : requestNo.trim();
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? null : userIp.trim();
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }

    public String getProtocolNo() {
        return protocolNo;
    }

    public void setProtocolNo(String protocolNo) {
        this.protocolNo = protocolNo == null ? null : protocolNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getFuionOrderNo() {
        return fuionOrderNo;
    }

    public void setFuionOrderNo(String fuionOrderNo) {
        this.fuionOrderNo = fuionOrderNo == null ? null : fuionOrderNo.trim();
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
    }
}