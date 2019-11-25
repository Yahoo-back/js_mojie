package com.hyy.ifm.product.pojo;

import com.hyy.ifm.common.pojo.BasePojo;

import java.util.Date;

public class Cnds extends BasePojo {

	private ProductBean rows;

	public ProductBean getRows() {
		return rows;
	}

	public void setRows(ProductBean rows) {
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

	public static class ProductBean {
		private String id;
		private String name;
		private String logo;
		private String classify_id;
		private String interest;
		private String count;
		private String money;
		private String link;
		private String description;
		private String settle_way_cpa;
		private String settle_way_cps;
		private String settle_cycle;
		private String manager_url;
		private String manager_user;
		private String manager_password;
		private String create_time;
		private String status;
		private String sort;
		private String position;
		private String file_uri;
		private String perio_way;
		private String periodization;
		private String apply_require;
		private String apply_data;
		private String ktx_desc;
		private String is_hot;
		private String hot_sort;
		private String contact;
		private String contact_info;
		private String is_home;
		private String home_sort;
		private String url;
		private String company;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		private String type;

		public String getSettle_way_cpa() {
			return settle_way_cpa;
		}

		public void setSettle_way_cpa(String settle_way_cpa) {
			this.settle_way_cpa = settle_way_cpa;
		}

		public String getSettle_way_cps() {
			return settle_way_cps;
		}

		public void setSettle_way_cps(String settle_way_cps) {
			this.settle_way_cps = settle_way_cps;
		}

		public String getSettle_cycle() {
			return settle_cycle;
		}

		public void setSettle_cycle(String settle_cycle) {
			this.settle_cycle = settle_cycle;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getHome_sort() {
			return home_sort;
		}

		public void setHome_sort(String home_sort) {
			this.home_sort = home_sort;
		}

		public String getIs_home() {
			return is_home;
		}

		public void setIs_home(String is_home) {
			this.is_home = is_home;
		}

		public String getContact() {
			return contact;
		}

		public void setContact(String contact) {
			this.contact = contact;
		}

		public String getContact_info() {
			return contact_info;
		}

		public void setContact_info(String contact_info) {
			this.contact_info = contact_info;
		}

		public String getIs_hot() {
			return is_hot;
		}

		public void setIs_hot(String is_hot) {
			this.is_hot = is_hot;
		}

		public String getHot_sort() {
			return hot_sort;
		}

		public void setHot_sort(String hot_sort) {
			this.hot_sort = hot_sort;
		}

		public String getKtx_desc() {
			return ktx_desc;
		}

		public void setKtx_desc(String ktx_desc) {
			this.ktx_desc = ktx_desc;
		}

		public String getApply_require() {
			return apply_require;
		}

		public void setApply_require(String apply_require) {
			this.apply_require = apply_require;
		}

		public String getApply_data() {
			return apply_data;
		}

		public void setApply_data(String apply_data) {
			this.apply_data = apply_data;
		}

		public String getPerio_way() {
			return perio_way;
		}

		public void setPerio_way(String perio_way) {
			this.perio_way = perio_way;
		}

		public String getPeriodization() {
			return periodization;
		}

		public void setPeriodization(String periodization) {
			this.periodization = periodization;
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

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getLogo() {
			return logo;
		}

		public void setLogo(String logo) {
			this.logo = logo;
		}

		public String getClassify_id() {
			return classify_id;
		}

		public void setClassify_id(String classify_id) {
			this.classify_id = classify_id;
		}

		public String getInterest() {
			return interest;
		}

		public void setInterest(String interest) {
			this.interest = interest;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
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

		public String getManager_url() {
			return manager_url;
		}

		public void setManager_url(String manager_url) {
			this.manager_url = manager_url;
		}

		public String getManager_user() {
			return manager_user;
		}

		public void setManager_user(String manager_user) {
			this.manager_user = manager_user;
		}

		public String getManager_password() {
			return manager_password;
		}

		public void setManager_password(String manager_password) {
			this.manager_password = manager_password;
		}

		public String getCreate_time() {
			return create_time;
		}

		public void setCreate_time(String create_time) {
			this.create_time = create_time;
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

		/**
		 * 模糊查询条件
		 */
		private String name_cnd;
		private String classify_cnd;
		private String status_cnd;
		private String position_cnd;
		private String create_time_FROM_cnd;
		private String create_time_TO_cnd;
		private String is_hot_cnd;
		private String is_home_cnd;

		public String getIs_home_cnd() {
			return is_home_cnd;
		}

		public void setIs_home_cnd(String is_home_cnd) {
			this.is_home_cnd = is_home_cnd;
		}

		public String getIs_hot_cnd() {
			return is_hot_cnd;
		}

		public void setIs_hot_cnd(String is_hot_cnd) {
			this.is_hot_cnd = is_hot_cnd;
		}

		public String getName_cnd() {
			return name_cnd;
		}

		public void setName_cnd(String name_cnd) {
			this.name_cnd = name_cnd;
		}

		public String getClassify_cnd() {
			return classify_cnd;
		}

		public void setClassify_cnd(String classify_cnd) {
			this.classify_cnd = classify_cnd;
		}

		public String getStatus_cnd() {
			return status_cnd;
		}

		public void setStatus_cnd(String status_cnd) {
			this.status_cnd = status_cnd;
		}

		public String getPosition_cnd() {
			return position_cnd;
		}

		public void setPosition_cnd(String position_cnd) {
			this.position_cnd = position_cnd;
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