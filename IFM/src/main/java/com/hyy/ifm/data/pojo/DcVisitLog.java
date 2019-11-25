package com.hyy.ifm.data.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Author 毛椅俊
 * @Date 2018-03-11 10:23
 * @Description Created with IntelliJ IDEA.
 */
@Table("")
public class DcVisitLog {

    @Id
    private int id;
    @Column("visit_time")
    private String visitTime;
    @Column("count")
    private int count;
    @Column("count_uv")
    private long countUv;
    @Column
    private String classify;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
