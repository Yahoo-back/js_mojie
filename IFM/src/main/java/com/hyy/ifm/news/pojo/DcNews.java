package com.hyy.ifm.news.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;
import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 15:33
 * @Description Created with IntelliJ IDEA.
 */
@Table("dc_news")
public class DcNews {

    @Id
    private int id;
    @Column("title")
    private String title;
    @Column("icon")
    private String icon;
    @Column("content")
    private String content;
    @Column("url")
    private String url;
    @Column("start_time")
    private Date startTime;
    @Column("end_time")
    private Date endTime;
    @Column("create_time")
    private Date createTime;
    @Column("status")
    private String status;
    @Column("sort")
    private int sort;
    @Column("position")
    private String position;
    @Column("remark")
    private String remark;

    private List<DcNewsAssociate> dcNewsAssociates;

    public List<DcNewsAssociate> getDcNewsAssociates() {
        return dcNewsAssociates;
    }

    public void setDcNewsAssociates(List<DcNewsAssociate> dcNewsAssociates) {
        this.dcNewsAssociates = dcNewsAssociates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
