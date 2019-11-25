package com.hyy.root.controller;

import com.google.gson.Gson;
import com.hyy.root.dispcter.ApiDispcterImpl;
import com.hyy.root.pojo.Logins;
import com.hyy.root.pojo.Muens;
import com.hyy.root.util.IPUtils;
import com.hyy.root.util.ToolUtils;
import com.visionalsun.util.handler.NetworkAddressHandler;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@IocBean
@Ok("json")
@Fail("http:500")
public class LoginController extends BaseController{
	
	private Gson gson = new Gson();

	@At
	@Ok("jsp:main.index")
	public void index(){
		log.info("   欢迎登陆魔借后台管理系统.......");
	}

	@At
	@Ok("re:jsp:main.main")
	@Fail("re:jsp:main.index")
	public void login(HttpServletRequest req,HttpSession session) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "";
		String url2=req.getScheme()+"://"+ req.getServerName();//+request.getRequestURI();
		System.out.println(url2);
		System.out.println("getIpAddr(req)======" + IPUtils.getIpAddr(req));
		try {
			ApiDispcterImpl api = Mvcs.ctx().getDefaultIoc().get(ApiDispcterImpl.class);
			// 获得用户名 和  密码 
			String userName = ToolUtils.nvl(req.getParameter("Username"));
			String password = ToolUtils.nvl(req.getParameter("Password"));
			if(userName.isEmpty() || password.isEmpty()){
				req.setAttribute("msg", "用户名或者密码为空！");
				throw  new Exception("用户名或者密码为空！");
			}
			map.put("cmd", "login");
			String ip = IPUtils.getIpAddr(req);
			map.put("rows", "{'userName':'"+userName+"','password':'"+ToolUtils.MD5(password)+"','address':'"+ip+"'}");
			String res = api.doProcess(map);
			Logins logins = gson.fromJson(res,Logins.class);
			msg = logins.getResultNode();
			if("success".equals(logins.getResultNode())){
				session.setAttribute("user", logins.getRows());
			}else{
				req.setAttribute("msg", logins.getResultNode());
				throw  new Exception(logins.getResultNode());
			}
			map = new HashMap<String,Object>();
			map.put("cmd", "qryMuenByUserid");
			map.put("userCode", logins.getRows().getUserId());
			res = api.doProcess(map);
			Muens meuns = gson.fromJson(res,Muens.class);
			req.setAttribute("mainmeuns", meuns);
			req.setAttribute("meuns", meuns.getRows());
			req.setAttribute("meunsJson", gson.toJson(meuns.getRows()));
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", msg);
			throw  new Exception(msg);
		}
	}
	
	@At
	@Ok(">>:/index")
	public void loginOut(HttpSession session){
		session.invalidate();
	}
	
}
