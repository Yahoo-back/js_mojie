package com.hyy.ifm.news.pojo;

import com.hyy.ifm.common.pojo.BasePojo;

public class Cnds extends BasePojo {

	private NewsBean rows;

	public NewsBean getRows() {
		return rows;
	}

	public void setRows(NewsBean rows) {
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

	public static class NewsBean {
		private String type;
		private String id;
		private String title;
		private String file_uri;
		private String classify;
		private String label;
		private String product_id;
		private String status;
		private String sort;
		private String position;
		private String position_dd;
		private String content;
		private String start_time;
		private String end_time;
		private String url;
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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

		public String getFile_uri() {
			return file_uri;
		}

		public void setFile_uri(String file_uri) {
			this.file_uri = file_uri;
		}

		public String getClassify() {
			return classify;
		}

		public void setClassify(String classify) {
			this.classify = classify;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getProduct_id() {
			return product_id;
		}

		public void setProduct_id(String product_id) {
			this.product_id = product_id;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getSort() {
			return sort;
		}

		public void setSort(String sort) {
			this.sort = sort;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		/**
		 * 模糊查询条件
		 */
		private String type_cnd;
		private String title_cnd;
		private String classify_cnd;
		private String position_cnd;
		private String status_cnd;
		private String start_time_FROM_cnd;
		private String start_time_TO_cnd;
		private String create_time_FROM_cnd;
		private String create_time_TO_cnd;
		private String name_cnd;

		public String getName_cnd() {
			return name_cnd;
		}

		public void setName_cnd(String name_cnd) {
			this.name_cnd = name_cnd;
		}

		public String getType_cnd() {
			return type_cnd;
		}

		public void setType_cnd(String type_cnd) {
			this.type_cnd = type_cnd;
		}

		public String getTitle_cnd() {
			return title_cnd;
		}

		public void setTitle_cnd(String title_cnd) {
			this.title_cnd = title_cnd;
		}

		public String getClassify_cnd() {
			return classify_cnd;
		}

		public void setClassify_cnd(String classify_cnd) {
			this.classify_cnd = classify_cnd;
		}

		public String getPosition_cnd() {
			return position_cnd;
		}

		public void setPosition_cnd(String position_cnd) {
			this.position_cnd = position_cnd;
		}

		public String getStatus_cnd() {
			return status_cnd;
		}

		public void setStatus_cnd(String status_cnd) {
			this.status_cnd = status_cnd;
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

		public String getCreate_time_FROM_cnd() {
			return create_time_FROM_cnd;
		}

		public void setCreate_time_FROM_cnd(String create_time_FROM_cnd) {
			this.create_time_FROM_cnd = create_time_FROM_cnd;
		}

		public String getCreate_time_TO_cnd() {
			return create_time_TO_cnd;
		}

		public void setCreate_time_TO_cnd(String create_time_TO_cnd) {
			this.create_time_TO_cnd = create_time_TO_cnd;
		}
	}
	
}