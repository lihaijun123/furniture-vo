package com.focustech.focus3d.agent.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 访问控制过滤器
 * *
 * @author lihaijun
 *
 */
public class VisitLimitFilter extends AbstractFilter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String url = req.getRequestURI();
		boolean isLimitVisitUrl = isStaticResourceUrl(url);;
		if(isLimitVisitUrl){
			//返回默认资源
			resp.setStatus(403);
			return;
		}
		fc.doFilter(req, resp);
	}
}
