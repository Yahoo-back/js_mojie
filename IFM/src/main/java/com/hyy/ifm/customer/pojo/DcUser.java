package com.hyy.ifm.customer.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.Date;

/**
 * @Author 毛椅俊
 * @Date 2018-03-15 10:35
 * @Description Created with IntelliJ IDEA.
 */
@IocBean
@Table("dc_user")
public class DcUser {

    @Id
    private int id;
    @Column("mobile")
    private String mobile;
    @Column("password")
    private String password;
    @Column("create_time")
    private Date createTime;
    @Column("status")
    private String status;
    @Column("uuid")
    private String uuid;
    @Column("remark")
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
