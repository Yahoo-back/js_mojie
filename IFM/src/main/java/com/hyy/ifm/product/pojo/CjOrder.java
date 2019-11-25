package com.hyy.ifm.product.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;
import java.util.Date;

@Table("cj_order")
 public class CjOrder {


     @Id
    private Long id;

    @Column("user_id")
    private Integer userId;

    @Column("type")
    private String type;

    @Column("outer_id")
    private Integer outerId;

    @Column("request_no")
    private String requestNo;

    @Column("user_ip")
    private String userIp;

    @Column("amt")
    private Double amt;

    @Column("order_trxid")
    private String orderTrxid;

    @Column("create_time")
    private Date createTime;

    @Column("status")
    private String status;

    @Column("pay_date")
    private Date payDate;

    @Column("fail_reason")
    private String failReason;


    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取
    *
    * @return 
    */
    public Integer getUserId(){
        return userId;
    }

    /**
    * 设置
    * 
    * @param userId 要设置的
    */
    public void setUserId(Integer userId){
        this.userId = userId;
    }

    /**
    * 获取0: 续期, 1: 还款
    *
    * @return 0: 续期, 1: 还款
    */
    public String getType(){
        return type;
    }

    /**
    * 设置0: 续期, 1: 还款
    * 
    * @param type 要设置的0: 续期, 1: 还款
    */
    public void setType(String type){
        this.type = type;
    }

    /**
    * 获取order_delay标id或user_order标id
    *
    * @return order_delay标id或user_order标id
    */
    public Integer getOuterId(){
        return outerId;
    }

    /**
    * 设置order_delay标id或user_order标id
    * 
    * @param outerId 要设置的order_delay标id或user_order标id
    */
    public void setOuterId(Integer outerId){
        this.outerId = outerId;
    }

    /**
    * 获取唯一请求号
    *
    * @return 唯一请求号
    */
    public String getRequestNo(){
        return requestNo;
    }

    /**
    * 设置唯一请求号
    * 
    * @param requestNo 要设置的唯一请求号
    */
    public void setRequestNo(String requestNo){
        this.requestNo = requestNo;
    }

    /**
    * 获取客户ip
    *
    * @return 客户ip
    */
    public String getUserIp(){
        return userIp;
    }

    /**
    * 设置客户ip
    * 
    * @param userIp 要设置的客户ip
    */
    public void setUserIp(String userIp){
        this.userIp = userIp;
    }

    /**
    * 获取交易金额, 以元计算, 保留两位小数
    *
    * @return 交易金额, 以元计算, 保留两位小数
    */
    public Double getAmt(){
        return amt;
    }

    /**
    * 设置交易金额, 以元计算, 保留两位小数
    * 
    * @param amt 要设置的交易金额, 以元计算, 保留两位小数
    */
    public void setAmt(Double amt){
        this.amt = amt;
    }

    /**
    * 获取畅捷流水号
    *
    * @return 畅捷流水号
    */
    public String getOrderTrxid(){
        return orderTrxid;
    }

    /**
    * 设置畅捷流水号
    * 
    * @param orderTrxid 要设置的畅捷流水号
    */
    public void setOrderTrxid(String orderTrxid){
        this.orderTrxid = orderTrxid;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置创建时间
    * 
    * @param createTime 要设置的创建时间
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
    * 获取0: 支付中, 1: 成功, 2: 失败, 3: 异常
    *
    * @return 0: 支付中, 1: 成功, 2: 失败, 3: 异常
    */
    public String getStatus(){
        return status;
    }

    /**
    * 设置0: 支付中, 1: 成功, 2: 失败, 3: 异常
    * 
    * @param status 要设置的0: 支付中, 1: 成功, 2: 失败, 3: 异常
    */
    public void setStatus(String status){
        this.status = status;
    }

    /**
    * 获取支付时间
    *
    * @return 支付时间
    */
    public Date getPayDate(){
        return payDate;
    }

    /**
    * 设置支付时间
    * 
    * @param payDate 要设置的支付时间
    */
    public void setPayDate(Date payDate){
        this.payDate = payDate;
    }

    /**
    * 获取失败原因
    *
    * @return 失败原因
    */
    public String getFailReason(){
        return failReason;
    }

    /**
    * 设置失败原因
    * 
    * @param failReason 要设置的失败原因
    */
    public void setFailReason(String failReason){
        this.failReason = failReason;
    }

}