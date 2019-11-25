package com.hyy.root.dispcter;

import com.hyy.root.util.ToolUtils;
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
 * <i>Filter : Web 过滤器</i>
 * 
 * @author 毛椅俊
 *
 */
@IocBean(scope="singleton", singleton=true)
public class SevFilter extends NutFilter {
	// log 日志管理器
	protected static final Log log = Logs.get();
	
	private Set<String> prefixs = new HashSet<String>();
	
	
	public void init(FilterConfig conf) throws ServletException {
		super.init(conf);
		
		prefixs.add(conf.getServletContext().getContextPath() + "/newhouse/list");
		prefixs.add(conf.getServletContext().getContextPath() + "/healthcheck");
		prefixs.add(conf.getServletContext().getContextPath() + "/admin");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		if (req instanceof HttpServletRequest) {
			String uri = ((HttpServletRequest) req).getRequestURI();

			for (String prefix : prefixs) {
				if (uri.startsWith(prefix)) {
					chain.doFilter(req, resp);
					System.out.println("Login IP: " + ToolUtils.getIpAddr((HttpServletRequest) req));
					return;
				}
			}
		}
		
		super.doFilter(req, resp, chain);
	}
	
}
