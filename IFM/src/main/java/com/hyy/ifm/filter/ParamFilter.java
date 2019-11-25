package com.hyy.ifm.filter;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * <i>Filter : Web Parameter 过滤器</i>
 * 
 * @author 饶瑞文
 *
 */
@IocBean(scope="singleton", singleton=true)
public class ParamFilter extends NutFilter {
	protected static final Log log = Logs.get();	// log 日志管理器
	
	private Set<String> prefixs = new HashSet<String>();
	
	
	/* (non-Javadoc)
	 * @see org.nutz.mvc.NutFilter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig conf) throws ServletException {
		super.init(conf);
		
		prefixs.add(conf.getServletContext().getContextPath() + "/apm");
	}
	
	/* (non-Javadoc)
	 * @see org.nutz.mvc.NutFilter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		/*
		 * 通过请求入参获取指定的 projectPath（当前发出请求的项目路径），实现当前项目与本项目的 session 共享（覆盖模式）。
		 * 
		 * 注：本技术适用环境为“同一服务域下”，非分布式。
		 */
		/*String _projectPath = req.getParameter(SysShareKey.Value.projectPath.name());	// Project 项目路径
		if (UtilHandler.IsNotEmpty(_projectPath)) {
			ServletContext _servletContext = ((HttpServletRequest) req).getServletContext().getContext(_projectPath);
			if (UtilHandler.IsNotNull(_servletContext)) {
				HttpSession _session = (HttpSession) _servletContext.getAttribute(SysShareKey.Value.session.name());
				if (UtilHandler.IsNotNull(_session)) req.setAttribute(SysShareKey.Value.session.name(), _session);
			}
		}*/
		
		if (req instanceof HttpServletRequest) {
			String uri = ((HttpServletRequest) req).getRequestURI();
			for (String prefix : prefixs) {
				if (uri.startsWith(prefix)) {
					chain.doFilter(req, resp);
					return;
				}
			}
		}
		
		super.doFilter(req, resp, chain);
	}
	
}
