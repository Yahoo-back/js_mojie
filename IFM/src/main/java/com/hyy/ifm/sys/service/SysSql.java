package com.hyy.ifm.sys.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hyy.ifm.util.StringUtil;

public class SysSql {
	
	public static JSONObject queryAllCustoms2 (JSONObject jsonObject){
		StringBuffer cnd = new StringBuffer();
		JSONObject jsons = (JSONObject) JSON.parse(jsonObject.getString("rows"));
		boolean f = false ;
		if(!"".equals(StringUtil.nvl(jsons.getString("company_name_cnd")))){
			cnd.append(" and t.company_name like '%"+StringUtil.nvl(jsons.getString("company_name_cnd"))+"%' ");
			f = true;
		}
		if(!"".equals(StringUtil.nvl(jsons.getString("identity_card_cnd")))){
			cnd.append(" and t.identity_card like '"+StringUtil.nvl(jsons.getString("identity_card_cnd"))+"' ");
			f = true;
		}
		if(!"".equals(StringUtil.nvl(jsons.getString("mobile_cnd")))){
			cnd.append(" and t.mobile like '"+StringUtil.nvl(jsons.getString("mobile_cnd"))+"' ");
			f = true;
		}
		if(!"".equals(StringUtil.nvl(jsons.getString("submit_time_TO_cnd")))){
			cnd.append(" and t2.submit_time<='"+StringUtil.nvl(jsons.getString("submit_time_TO_cnd"))+" 23:59:59' ");
			f = true;
		}
		if(!"".equals(StringUtil.nvl(jsons.getString("submit_time_FROM_cnd")))){
			cnd.append(" and t2.submit_time>='"+StringUtil.nvl(jsons.getString("submit_time_FROM_cnd"))+"' ");
			f = true;
		}
		if(!"".equals(StringUtil.nvl(jsons.getString("license_type_cnd")))){
			cnd.append(" and t.license_type = '"+StringUtil.nvl(jsons.getString("license_type_cnd"))+"' ");
			f = true;
		}
		if(!"".equals(StringUtil.nvl(jsons.getString("is_legal_rpst_cnd")))){
			cnd.append(" and t.is_legal_rpst = '"+StringUtil.nvl(jsons.getString("is_legal_rpst_cnd"))+"' ");
			f = true;
		}
		if(!"".equals(StringUtil.nvl(jsons.getString("log_status_cnd")))){
			cnd.append(" and t2.status = '"+StringUtil.nvl(jsons.getString("log_status_cnd"))+"' ");
			f = true;
		}
		if(!f){
			cnd.append("and t2.status =0");
		}
		jsonObject.put("fuzzy", cnd.toString());
		return jsonObject;
	}
	
	
	public static JSONObject queryForSerOrder (JSONObject jsonObject){
		StringBuffer cnd = new StringBuffer();
		JSONObject jsons = (JSONObject) JSON.parse(jsonObject.getString("rows"));
		boolean f = false ;
		if(!"".equals(StringUtil.nvl(jsons.getString("company_name_cnd")))){
			cnd.append(" and t2.company_name like '%"+StringUtil.nvl(jsons.getString("company_name_cnd"))+"' ");
			f = true;
		}
		if(!"".equals(StringUtil.nvl(jsons.getString("service_name_cnd")))){
			cnd.append(" and t3.service_name like '%"+StringUtil.nvl(jsons.getString("service_name_cnd"))+"' ");
			f = true;
		}
		if(!"".equals(StringUtil.nvl(jsons.getString("mobile_cnd")))){
			cnd.append(" and t2.mobile like '"+StringUtil.nvl(jsons.getString("mobile_cnd"))+"' ");
			f = true;
		}
		if(!"".equals(StringUtil.nvl(jsons.getString("submit_time_TO_cnd")))){
			cnd.append(" and t4.submit_time<='"+StringUtil.nvl(jsons.getString("submit_time_TO_cnd"))+" 23:59:59' ");
			f = true;
		}
		if(!"".equals(StringUtil.nvl(jsons.getString("submit_time_FROM_cnd")))){
			cnd.append(" and t4.submit_time>='"+StringUtil.nvl(jsons.getString("submit_time_FROM_cnd"))+"' ");
			f = true;
		}
		if(!"".equals(StringUtil.nvl(jsons.getString("is_legal_rpst_cnd")))){
			cnd.append(" and t2.is_legal_rpst = '"+StringUtil.nvl(jsons.getString("is_legal_rpst_cnd"))+"' ");
			f = true;
		}
		if(!"".equals(StringUtil.nvl(jsons.getString("payment_cnd")))){
			cnd.append(" and t.payment = '"+StringUtil.nvl(jsons.getString("payment_cnd"))+"' ");
			f = true;
		}
		if(!"".equals(StringUtil.nvl(jsons.getString("status_cnd")))){
			cnd.append(" and t.status = '"+StringUtil.nvl(jsons.getString("status_cnd"))+"' ");
			f = true;
		}
		jsonObject.put("fuzzy", cnd.toString());
		return jsonObject;
	}

}
