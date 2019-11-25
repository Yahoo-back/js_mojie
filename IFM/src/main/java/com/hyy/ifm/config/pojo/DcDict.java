package com.hyy.ifm.config.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 9:47
 * @Description Created with IntelliJ IDEA.
 */
@Table("dc_dict")
public class DcDict {

    @Id
    private int id;
    @Column("data_type")
    private String dataType;
    @Column("item_key")
    private String itemKey;
    @Column("item_value")
    private String itemValue;
    @Column("xh")
    private String xh;
    @Column("is_use")
    private String isUse;
    @Column("out_data_from")
    private String outDataFrom;
    @Column("parent_id")
    private String parentId;
    @Column("dict_desc")
    private String dictDesc;

    @Column("showese")
    private String showese;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getOutDataFrom() {
        return outDataFrom;
    }

    public void setOutDataFrom(String outDataFrom) {
        this.outDataFrom = outDataFrom;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    public void setShowese(String show) {
        this.showese = show;
    }

    public String getShowese() {
        return showese;
    }
}
