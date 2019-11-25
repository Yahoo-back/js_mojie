package com.hyy.ifm.util;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;

@SuppressWarnings("all")
public class DecryptDruidSource extends DruidDataSource {
    private static final long serialVersionUID = 1L;
    @Override
    public void setUsername(String username) {
	try {
	    username = ConfigTools.decrypt(username);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	super.setUsername(username);
    }

    @Override
    public void setUrl(String jdbcUrl) {
	try {
	    jdbcUrl = ConfigTools.decrypt(jdbcUrl);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	super.setUrl(jdbcUrl);
    }
}
