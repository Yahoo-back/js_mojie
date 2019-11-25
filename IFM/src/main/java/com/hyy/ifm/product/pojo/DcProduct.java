package com.hyy.ifm.product.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 16:22
 * @Description Created with IntelliJ IDEA.
 */
@Table("dc_product")
public class DcProduct {

    @Id
    private int id;
    @Column("name")
    private String name;
    @Column("logo")
    private String logo;
    @Column("classify_id")
    private int classifyId;
    @Column("interest")
    private String interest;
    @Column("ktx_desc")
    private String ktxDesc;
    @Column("money")
    private String money;
    @Column("link")
    private String link;
    @Column("description")
    private String description;
    @Column("settle_way_cpa")
    private String settleWayCpa;
    @Column("settle_way_cps")
    private String settleWayCps;
    @Column("settle_cycle")
    private String settleCycle;
    @Column("perio_way")
    private String perioWay;
    @Column("periodization")
    private String periodization;
    @Column("condition_list")
    private String conditionList;
    @Column("data_list")
    private String dataList;
    @Column("contact")
    private String contact;
    @Column("contact_info")
    private String contactInfo;
    @Column("manager_url")
    private String managerUrl;
    @Column("manager_user")
    private String managerUser;
    @Column("manager_password")
    private String managerPassword;
    @Column("create_time")
    private Date createTime;
    @Column("status")
    private String status;
    @Column("is_hot")
    private String isHot;
    @Column("hot_sort")
    private String hotSort;
    @Column("sort")
    private int sort;
    @Column("position")
    private String position;
    @Column("apply_num")
    private String applyNum;
    @Column("company")
    private String company;

    public String getSettleWayCpa() {
        return settleWayCpa;
    }

    public void setSettleWayCpa(String settleWayCpa) {
        this.settleWayCpa = settleWayCpa;
    }

    public String getSettleWayCps() {
        return settleWayCps;
    }

    public void setSettleWayCps(String settleWayCps) {
        this.settleWayCps = settleWayCps;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }

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

    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getKtxDesc() {
        return ktxDesc;
    }

    public void setKtxDesc(String ktxDesc) {
        this.ktxDesc = ktxDesc;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSettleCycle() {
        return settleCycle;
    }

    public void setSettleCycle(String settleCycle) {
        this.settleCycle = settleCycle;
    }

    public String getPerioWay() {
        return perioWay;
    }

    public void setPerioWay(String perioWay) {
        this.perioWay = perioWay;
    }

    public String getPeriodization() {
        return periodization;
    }

    public void setPeriodization(String periodization) {
        this.periodization = periodization;
    }

    public String getConditionList() {
        return conditionList;
    }

    public void setConditionList(String conditionList) {
        this.conditionList = conditionList;
    }

    public String getDataList() {
        return dataList;
    }

    public void setDataList(String dataList) {
        this.dataList = dataList;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getManagerUrl() {
        return managerUrl;
    }

    public void setManagerUrl(String managerUrl) {
        this.managerUrl = managerUrl;
    }

    public String getManagerUser() {
        return managerUser;
    }

    public void setManagerUser(String managerUser) {
        this.managerUser = managerUser;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
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

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    public String getHotSort() {
        return hotSort;
    }

    public void setHotSort(String hotSort) {
        this.hotSort = hotSort;
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
}
