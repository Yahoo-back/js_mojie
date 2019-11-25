package com.hyy.ifm.product.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;


@Table("xf_config")
 public class XfConfig {


    @Id
    private Long id;

    @Column("url")
    private String url;

    @Column("mchntcd")
    private String partnerId;

    @Column("password")
    private String password;



    @Column("terminalId")
    private String terminalId;
    @Column("mer_user_id")
    private String merUserId;

    @Column("seller_id")
    private String sellerId;

    @Column("sub_merchant_no")
    private String subMerchantNo;

    @Column("private_key")
    private String privateKey;

    @Column("public_key")
    private String publicKey;

    @Column("callback_url")
    private String callbackUrl;

    @Column("remark")
    private String remark;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
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
    * 获取接口地址
    *
    * @return 接口地址
    */
    public String getUrl(){
        return url;
    }

    /**
    * 设置接口地址
    * 
    * @param url 要设置的接口地址
    */
    public void setUrl(String url){
        this.url = url;
    }

    /**
    * 获取商户号
    *
    * @return 商户号
    */
    public String getPartnerId(){
        return partnerId;
    }

    /**
    * 设置商户号
    * 
    * @param partnerId 要设置的商户号
    */
    public void setPartnerId(String partnerId){
        this.partnerId = partnerId;
    }

    /**
    * 获取用户标识
    *
    * @return 用户标识
    */
    public String getMerUserId(){
        return merUserId;
    }

    /**
    * 设置用户标识
    * 
    * @param merUserId 要设置的用户标识
    */
    public void setMerUserId(String merUserId){
        this.merUserId = merUserId;
    }

    /**
    * 获取子账户号
    *
    * @return 子账户号
    */
    public String getSellerId(){
        return sellerId;
    }

    /**
    * 设置子账户号
    * 
    * @param sellerId 要设置的子账户号
    */
    public void setSellerId(String sellerId){
        this.sellerId = sellerId;
    }

    /**
    * 获取子商户号
    *
    * @return 子商户号
    */
    public String getSubMerchantNo(){
        return subMerchantNo;
    }

    /**
    * 设置子商户号
    * 
    * @param subMerchantNo 要设置的子商户号
    */
    public void setSubMerchantNo(String subMerchantNo){
        this.subMerchantNo = subMerchantNo;
    }

    /**
    * 获取用户私钥
    *
    * @return 用户私钥
    */
    public String getPrivateKey(){
        return privateKey;
    }

    /**
    * 设置用户私钥
    * 
    * @param privateKey 要设置的用户私钥
    */
    public void setPrivateKey(String privateKey){
        this.privateKey = privateKey;
    }

    /**
    * 获取快钱公钥
    *
    * @return 快钱公钥
    */
    public String getPublicKey(){
        return publicKey;
    }

    /**
    * 设置快钱公钥
    * 
    * @param publicKey 要设置的快钱公钥
    */
    public void setPublicKey(String publicKey){
        this.publicKey = publicKey;
    }

    /**
    * 获取回调地址
    *
    * @return 回调地址
    */
    public String getCallbackUrl(){
        return callbackUrl;
    }

    /**
    * 设置回调地址
    * 
    * @param callbackUrl 要设置的回调地址
    */
    public void setCallbackUrl(String callbackUrl){
        this.callbackUrl = callbackUrl;
    }

    /**
    * 获取备注
    *
    * @return 备注
    */
    public String getRemark(){
        return remark;
    }

    /**
    * 设置备注
    * 
    * @param remark 要设置的备注
    */
    public void setRemark(String remark){
        this.remark = remark;
    }

}