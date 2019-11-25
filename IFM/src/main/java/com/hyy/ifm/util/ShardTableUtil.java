package com.hyy.ifm.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具类-分表
 */
public class ShardTableUtil {
	
	public static List<String> tables = new ArrayList<String>();
	static{
		tables.add("xf_callback_log");
		tables.add("xf_order");
	}
	
	/**
	 * 根据主键Id生成分表名称
	 * @param shardId 拆分id段
	 * @return
	 */
	public static String generateTableNameById(String tableName, long id, long shardId){
		if(tables.contains(tableName)){
            long num = id % shardId;
			return tableName + "_" + num;
		}else{
			return tableName;
		}
	}

}
