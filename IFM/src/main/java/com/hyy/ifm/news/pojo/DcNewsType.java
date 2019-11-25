package com.hyy.ifm.news.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @Author 毛椅俊
 * @Date 2018-03-27 15:04
 * @Description Created with IntelliJ IDEA.
 */
@Table("dc_news_type")
public class DcNewsType {

    @Id
    private int id;
    @Column("name")
    private String name;
    @Column("type")
    private String type;
    @Column("status")
    private String status;
    @Column("create_time")
    private Date createTime;
    @Column("sort")
    private String sort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
