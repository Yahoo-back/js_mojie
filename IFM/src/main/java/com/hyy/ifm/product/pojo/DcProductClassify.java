package com.hyy.ifm.product.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 10:13
 * @Description Created with IntelliJ IDEA.
 */
@Table("dc_product_classify")
public class DcProductClassify {

    @Id
    private int id;
    @Column("name")
    private String name;
    @Column("logo")
    private String logo;
    @Column("url")
    private String url;
    @Column("create_time")
    private Date createTime;
    @Column("status")
    private String status;
    @Column("is_home")
    private String isHome;
    @Column("sort")
    private String sort;
    @Column("home_sort")
    private String homeSort;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getIsHome() {
        return isHome;
    }

    public void setIsHome(String isHome) {
        this.isHome = isHome;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getHomeSort() {
        return homeSort;
    }

    public void setHomeSort(String homeSort) {
        this.homeSort = homeSort;
    }
}
