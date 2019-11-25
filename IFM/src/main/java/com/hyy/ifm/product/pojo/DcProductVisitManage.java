package com.hyy.ifm.product.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;


@Table("dc_product_visit_manage")
public class DcProductVisitManage {

    @Id
    private int id;
    @Column("product_id")
    private int productId;
    @Column("create_time")
    private Date createTime;
    @Column("count")
    private int count;
    @Column("money")
    private String money;
    @Column("status")
    private String status;
    @Column("is_money")
    private String isMoney;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsMoney() {
        return isMoney;
    }

    public void setIsMoney(String isMoney) {
        this.isMoney = isMoney;
    }

}
