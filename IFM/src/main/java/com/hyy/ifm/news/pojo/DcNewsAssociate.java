package com.hyy.ifm.news.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Author 毛椅俊
 * @Date 2018-03-27 15:02
 * @Description Created with IntelliJ IDEA.
 */
@Table("dc_news_associate")
public class DcNewsAssociate {

    @Id
    private int id;
    @Column("news_id")
    private int newsId;
    @Column("associate_id")
    private int associateId;
    @Column("classify")
    private String classify;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getAssociateId() {
        return associateId;
    }

    public void setAssociateId(int associateId) {
        this.associateId = associateId;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }
}
