package com.hyy.ifm.product.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

@Table("user_bankcard")
public class UserBankcard {
    @Id
    private Integer id;

    @Column("user_id")
    private Integer userId;

    @Column("card_no")
    private String cardNo;

    @Column("bank_code")
    private String bankCode;

    @Column("card_top")
    private String cardTop;

    @Column("card_last")
    private String cardLast;

    @Column("request_no")
    private String requestNo;

    @Column("is_auth")
    private String isAuth;

    @Column("create_time")
    private Date createTime;

    @Column("bind_time")
    private Date bindTime;

    @Column("auth_from")
    private String authFrom;

    @Column("remark")
    private String remark;

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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getCardTop() {
        return cardTop;
    }

    public void setCardTop(String cardTop) {
        this.cardTop = cardTop == null ? null : cardTop.trim();
    }

    public String getCardLast() {
        return cardLast;
    }

    public void setCardLast(String cardLast) {
        this.cardLast = cardLast == null ? null : cardLast.trim();
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo == null ? null : requestNo.trim();
    }

    public String getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(String isAuth) {
        this.isAuth = isAuth == null ? null : isAuth.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getAuthFrom() {
        return authFrom;
    }

    public void setAuthFrom(String authFrom) {
        this.authFrom = authFrom == null ? null : authFrom.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}