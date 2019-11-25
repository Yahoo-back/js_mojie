package com.hyy.ifm.customer.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 14:23
 * @Description Created with IntelliJ IDEA.
 */
@Table("dc_user_attach")
public class DcUserAttach {

    @Id
    private int id;
    @Column("user_id")
    private int userId;
    @Column("type")
    private String type;
    @Column("path")
    private String path;
    @Column("create_time")
    private Date createTime;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
