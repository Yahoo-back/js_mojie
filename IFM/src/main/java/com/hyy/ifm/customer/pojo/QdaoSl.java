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
@Table("sl_qdao")
public class QdaoSl {

    @Id
    private int id;

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    @Column("login_id")
    private int loginId;

    @Column("source")
    private String source;

    public int getCountCount() {
        return countCount;
    }

    public void setCountCount(int countCount) {
        this.countCount = countCount;
    }

    public int getUvCount() {
        return uvCount;
    }

    public void setUvCount(int uvCount) {
        this.uvCount = uvCount;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column("count_count")
    private int countCount;
    @Column("uv_count")
    private int uvCount;
    @Column("uv")
    private int uv;

    public Double getSl() {
        return sl;
    }

    public void setSl(Double sl) {
        this.sl = sl;
    }

    @Column("sl")
    private Double sl;
    @Column("remark")
    private String remark;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column("update_time")
    private Date updateTime;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


}
