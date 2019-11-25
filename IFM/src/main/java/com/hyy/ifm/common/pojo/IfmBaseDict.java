package com.hyy.ifm.common.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("IFM_BASE_DICT")
public class IfmBaseDict {
	@Id
	private int id;
	@Column("DATA_TYPE")
	private String dataType;
	@Column("ITEM_KEY")
	private String key;
	@Column("ITEM_VALUE")
	private String value;
	@Column("XH")
	private String orderBy;
	@Column("OUT_DATA_FROM")
	private String outData;
	@Column("PARENT_ID")
	private int parentId;
	
	@Column("DICT_DESC")
	private String dict_desc;
	
	
	
	public String getDict_desc() {
		return dict_desc;
	}
	public void setDict_desc(String dict_desc) {
		this.dict_desc = dict_desc;
	}
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getOutData() {
		return outData;
	}
	public void setOutData(String outData) {
		this.outData = outData;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	
}
