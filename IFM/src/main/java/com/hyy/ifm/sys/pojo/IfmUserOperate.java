package com.hyy.ifm.sys.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

@Table("ifm_sys_user_operate")
public class IfmUserOperate {

    @Id
    private int id;
    @Column("ifm_user_id")
    private int ifm_user_id;
    @Column("address")
    private String address;
    @Column("operate")
    private String operate;
    @Column("operate_date")
    private Date operate_date;
    @Column("remark")
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIfm_user_id() {
        return ifm_user_id;
    }

    public void setIfm_user_id(int ifm_user_id) {
        this.ifm_user_id = ifm_user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public Date getOperate_date() {
        return operate_date;
    }

    public void setOperate_date(Date operate_date) {
        this.operate_date = operate_date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
