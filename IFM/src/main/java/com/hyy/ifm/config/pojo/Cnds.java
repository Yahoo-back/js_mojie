package com.hyy.ifm.config.pojo;

import com.hyy.ifm.common.pojo.BasePojo;
import org.nutz.dao.entity.annotation.Column;

import java.util.Date;

public class Cnds extends BasePojo {

	private ConfigBean rows;

	public ConfigBean getRows() {
		return rows;
	}

	public void setRows(ConfigBean rows) {
		this.rows = rows;
	}

	private String fuzzyCnd;

	public String getFuzzyCnd() {
		return fuzzyCnd;
	}

	public void setFuzzyCnd(String fuzzyCnd) {
		this.fuzzyCnd = fuzzyCnd;
	}

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static class ConfigBean {
		private String id;
		private String name;
		private String associate_type;
		private String associate_id;
		private String picPath;
		private String position_dd;
		private String url;
		private String start_time;
		private String end_time;
		private String sort;
		private String status;
		private String remark;
		private String data_type;
		private String file_uri;
		private String item_value;
		private String item_key;
		private String is_use;
		private String xh;

		public String getXh() {
			return xh;
		}

		public void setXh(String xh) {
			this.xh = xh;
		}

		public String getIs_use() {
			return is_use;
		}

		public void setIs_use(String is_use) {
			this.is_use = is_use;
		}

		public String getItem_value() {
			return item_value;
		}

		public void setItem_value(String item_value) {
			this.item_value = item_value;
		}

		public String getFile_uri() {
			return file_uri;
		}

		public void setFile_uri(String file_uri) {
			this.file_uri = file_uri;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAssociate_type() {
			return associate_type;
		}

		public void setAssociate_type(String associate_type) {
			this.associate_type = associate_type;
		}

		public String getAssociate_id() {
			return associate_id;
		}

		public void setAssociate_id(String associate_id) {
			this.associate_id = associate_id;
		}

		public String getPicPath() {
			return picPath;
		}

		public void setPicPath(String picPath) {
			this.picPath = picPath;
		}

		public String getPosition_dd() {
			return position_dd;
		}

		public void setPosition_dd(String position_dd) {
			this.position_dd = position_dd;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getStart_time() {
			return start_time;
		}

		public void setStart_time(String start_time) {
			this.start_time = start_time;
		}

		public String getEnd_time() {
			return end_time;
		}

		public void setEnd_time(String end_time) {
			this.end_time = end_time;
		}

		public String getSort() {
			return sort;
		}

		public void setSort(String sort) {
			this.sort = sort;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getData_type() {
			return data_type;
		}

		public void setData_type(String data_type) {
			this.data_type = data_type;
		}

		/**
		 * 模糊查询条件
		 */
		private String name_cnd;
		private String position_cnd;
		private String start_time_FROM_cnd;
		private String start_time_TO_cnd;
		private String end_time_FROM_cnd;
		private String end_time_TO_cnd;
		private String status_cnd;
		private String item_value_cnd;
		private String is_use_cnd;
		private String data_type_cnd;
		private String url_cnd;

		public String getUrl_cnd() {
			return url_cnd;
		}

		public void setUrl_cnd(String url_cnd) {
			this.url_cnd = url_cnd;
		}

		public String getData_type_cnd() {
			return data_type_cnd;
		}

		public void setData_type_cnd(String data_type_cnd) {
			this.data_type_cnd = data_type_cnd;
		}

		public String getItem_value_cnd() {
			return item_value_cnd;
		}

		public void setItem_value_cnd(String item_value_cnd) {
			this.item_value_cnd = item_value_cnd;
		}

		public String getIs_use_cnd() {
			return is_use_cnd;
		}

		public void setIs_use_cnd(String is_use_cnd) {
			this.is_use_cnd = is_use_cnd;
		}

		public String getStatus_cnd() {
			return status_cnd;
		}

		public void setStatus_cnd(String status_cnd) {
			this.status_cnd = status_cnd;
		}

		public String getName_cnd() {
			return name_cnd;
		}

		public void setName_cnd(String name_cnd) {
			this.name_cnd = name_cnd;
		}

		public String getPosition_cnd() {
			return position_cnd;
		}

		public void setPosition_cnd(String position_cnd) {
			this.position_cnd = position_cnd;
		}

		public String getStart_time_FROM_cnd() {
			return start_time_FROM_cnd;
		}

		public void setStart_time_FROM_cnd(String start_time_FROM_cnd) {
			this.start_time_FROM_cnd = start_time_FROM_cnd;
		}

		public String getStart_time_TO_cnd() {
			return start_time_TO_cnd;
		}

		public void setStart_time_TO_cnd(String start_time_TO_cnd) {
			this.start_time_TO_cnd = start_time_TO_cnd;
		}

		public String getEnd_time_FROM_cnd() {
			return end_time_FROM_cnd;
		}

		public void setEnd_time_FROM_cnd(String end_time_FROM_cnd) {
			this.end_time_FROM_cnd = end_time_FROM_cnd;
		}

		public String getEnd_time_TO_cnd() {
			return end_time_TO_cnd;
		}

		public void setEnd_time_TO_cnd(String end_time_TO_cnd) {
			this.end_time_TO_cnd = end_time_TO_cnd;
		}

		public String getItem_key() {
			return item_key;
		}

		public void setItem_key(String item_key) {
			this.item_key = item_key;
		}
	}
	
}