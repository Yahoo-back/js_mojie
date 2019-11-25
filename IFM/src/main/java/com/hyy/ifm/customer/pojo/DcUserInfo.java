package com.hyy.ifm.customer.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 14:20
 * @Description Created with IntelliJ IDEA.
 */
@Table("dc_user_info")
public class DcUserInfo {

    @Id
    private int id;
    @Column("user_id")
    private int userId;
    @Column("user_name")
    private String userName;
    @Column("sex")
    private String sex;
    @Column("job")
    private String job;
    @Column("zhima_score")
    private String zhimaScore;
    @Column("id_card")
    private String idCard;
    @Column("bank_card")
    private String bankCard;
    @Column("bank_open")
    private String bankOpen;

    public String getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(String userAuth) {
        this.userAuth = userAuth;
    }

    @Column("user_auth")
    private String userAuth;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getZhimaScore() {
        return zhimaScore;
    }

    public void setZhimaScore(String zhimaScore) {
        this.zhimaScore = zhimaScore;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankOpen() {
        return bankOpen;
    }

    public void setBankOpen(String bankOpen) {
        this.bankOpen = bankOpen;
    }

}
