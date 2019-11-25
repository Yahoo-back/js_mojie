package com.hyy.ifm.dispcter;

import com.hyy.ifm.dispcter.inlet.Inlet;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * <i>Dispatcher : Web 调度器</i>
 * 
 * @author xiachangfei
 *
 */
@IocBean(scope="singleton", singleton=true)
public class Dispcter extends HttpServlet {
	private static final long serialVersionUID = 5575452735430257833L;
	
	protected static final Log log = Logs.get();	// log 日志管理器
	
	/**服务列表*/
	public static final TreeMap<String, Object> serviceTreeMap = new TreeMap<String, Object>();
	/**无需认证的服务列表*/
	protected static final Set<String> nonAuthcServiceSet = new HashSet<String>();
	
	
	static {
		nonAuthcServiceSet.add("login");	// 登录
		nonAuthcServiceSet.add("qryMuenByUserid");	// 菜单
	}
	
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter _printWriter = resp.getWriter();
		log.info("req = " + URLDecoder.decode(req.getParameter("json"), "UTF-8"));
		_printWriter.println(Inlet.Authc(URLDecoder.decode(req.getParameter("json"), "UTF-8")));
		_printWriter.close();
	}
	
}