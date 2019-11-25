package com.hyy.root.controller;

import com.google.gson.Gson;
import com.hyy.root.dispcter.ApiDispcter;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 基础控制器
 * 
 * @author 毛椅俊
 *
 */
@IocBean(name="baseController")
public class BaseController {
	protected static final Log log = Logs.get();
	
	@Inject
	protected ApiDispcter apiDispcter;	// Api 调度器

	protected Gson gson = new Gson();
	
	
	protected void makeJSONObject(HttpServletResponse response, String json) throws IOException {
		PrintWriter pw = response.getWriter();
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			pw.write(json);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != pw) {
				pw.close();
			}
		}
	}

}
