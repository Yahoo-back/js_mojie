package com.hyy.ifm.data.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @Author 毛椅俊
 * @Date 2018-03-11 10:47
 * @Description Created with IntelliJ IDEA.
 */
@Table("dc_product_visit_log")
public class DcProductVisitLog {

    @Id
    private int id;
    @Column("product_id")
    private int productId;
    @Column("visit_time")
    private Date visitTime;
    @Column("count")
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
