package com.hyy.ifm.product.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;


@Table("xf_callback_log")
 public class XfCallbackLog {


    @Id
    private Long id;

    @Column("user_id")
    private Integer userId;

    @Column("type")
    private String type;

    @Column("create_time")
    private Date createTime;

    @Column("content")
    private String content;


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
    * 获取0: 绑卡请求, 1: 绑卡, 2: 超时, 3: 扣款, 4: 扣款回调 5: 解绑
    *
    * @return 0: 绑卡请求, 1: 绑卡, 2: 超时, 3: 扣款, 4: 扣款回调 5: 解绑
    */
    public String getType(){
        return type;
    }

    /**
    * 设置0: 绑卡请求, 1: 绑卡, 2: 超时, 3: 扣款, 4: 扣款回调 5: 解绑
    * 
    * @param type 要设置的0: 绑卡请求, 1: 绑卡, 2: 超时, 3: 扣款, 4: 扣款回调 5: 解绑
    */
    public void setType(String type){
        this.type = type;
    }

    /**
    * 获取
    *
    * @return 
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置
    * 
    * @param createTime 要设置的
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
    * 获取
    *
    * @return 
    */
    public String getContent(){
        return content;
    }

    /**
    * 设置
    * 
    * @param content 要设置的
    */
    public void setContent(String content){
        this.content = content;
    }

}