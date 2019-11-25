package com.hyy.ifm.data.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @Author 毛椅俊
 * @Date 2018-03-19 16:26
 * @Description Created with IntelliJ IDEA.
 */
@Table("dc_product_report")
public class DcProductReport {

    @Id
    private int id;
    @Column("product_id")
    private int productId;
    @Column("visit_time")
    private Date visitTime;
    @Column("visit_count")
    private Integer visitCount;
    @Column("reg_count")
    private Integer regCount;
    @Column("change_rate")
    private Double changeRate;
    @Column("loan_count")
    private Integer loanCount;

    @Column("settle_state")
    private String settleState;
    @Column("settle_way")
    private String settleWay;
    @Column("settle_money")
    private Double settleMoney;
    @Column("remark")
    private String remark;
//    @Column("user_id")
//    private String userId;

    @Column("serial_number")
    private String serialNumber;
    @Column("settle_certificatec")
    private String settleCertificatec;
    @Column("settle_time")
    private Date settleTime;

//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    public String getSettleWay() {
        return settleWay;
    }

    public void setSettleWay(String settleWay) {
        this.settleWay = settleWay;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSettleCertificatec() {
        return settleCertificatec;
    }

    public void setSettleCertificatec(String settleCertificatec) {
        this.settleCertificatec = settleCertificatec;
    }

    public Date getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getRegCount() {
        return regCount;
    }

    public void setRegCount(Integer regCount) {
        this.regCount = regCount;
    }

    public Double getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(Double changeRate) {
        this.changeRate = changeRate;
    }

    public Integer getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(Integer loanCount) {
        this.loanCount = loanCount;
    }

    public String getSettleState() {
        return settleState;
    }

    public void setSettleState(String settleState) {
        this.settleState = settleState;
    }

    public Double getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(Double settleMoney) {
        this.settleMoney = settleMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
