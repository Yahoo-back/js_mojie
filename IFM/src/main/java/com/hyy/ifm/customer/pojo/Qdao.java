package com.hyy.ifm.customer.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.ioc.loader.annotation.IocBean;


/**
 * @Author 毛椅俊
 * @Date 2018-03-15 10:35
 * @Description Created with IntelliJ IDEA.
 */
@IocBean
@Table("qd_count")
public class Qdao {

    @Id
    private int id;
    @Column("source")
    private String source;
    @Column("count")
    private int count;
    @Column("countUv")
    private int countUv;
    @Column("noneCount")
    private int noneCount;
    @Column("faceCount")
    private int faceCount;
    @Column("perCount")
    private int perCount;
    @Column("bankCount")
    private int bankCount;
    @Column("concatCount")
    private int concatCount;
    @Column("yunCount")
    private int yunCount;
    @Column("payCount")
    private int payCount;

    @Column("visit_time")
    private String visitTime;

    @Column("update_time")
    private String updateTime;

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }



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


    public int getCountUv() {
        return countUv;
    }

    public void setCountUv(int countUv) {
        this.countUv = countUv;
    }

    public int getNoneCount() {
        return noneCount;
    }

    public void setNoneCount(int noneCount) {
        this.noneCount = noneCount;
    }

    public int getFaceCount() {
        return faceCount;
    }

    public void setFaceCount(int faceCount) {
        this.faceCount = faceCount;
    }

    public int getPerCount() {
        return perCount;
    }

    public void setPerCount(int perCount) {
        this.perCount = perCount;
    }

    public void setPayCount(int payCount) {
        this.payCount = payCount;
    }

    public int getPayCount() {
        return payCount;
    }

    public void setYunCount(int yunCount) {
        this.yunCount = yunCount;
    }
    public int getYunCount() {
        return yunCount;
    }

    public void setConcatCount(int concatCount) {
        this.concatCount = concatCount;
    }

    public int getConcatCount() {
        return concatCount;
    }

    public void setBankCount(int bankCount) {
        this.bankCount = bankCount;
    }
    public int getBankCount() {
        return bankCount;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getUpdateTimee() {
        return updateTime;
    }
}
