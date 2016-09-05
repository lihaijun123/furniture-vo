package com.focustech.focus3d.agent.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	//静态目录
	protected static String[] STATIC_FILE = new String[]
	{
		"css"
		, "style"
		, "images"
		, "fileUpload"
		, "fonts"
		, "font-awesome"
		, "script"
		, "js"
		, "html"
		, "htmlhome"
	};
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String url = req.getRequestURI();
		//只验证静态资源
		boolean isLimitVisitUrl = validateStaticResource(url, req);
		if(!isLimitVisitUrl){
			//静态资源非法访问
			resp.setStatus(403);
			return;
		}
		fc.doFilter(req, resp);
	}
	
	/**
	 * 是否是静态资源url
	 * *
	 * @param url
	 * @return
	 */
	public boolean validateStaticResource(String url, HttpServletRequest request){
		boolean flag = true;
		int type = 0;
		url = url.toLowerCase();
		for(String dir : STATIC_FILE){
			if(url.startsWith("/" + dir)){
				Pattern pattern = Pattern.compile("^/" + dir + "(/?([a-zA-Z]|[0-9]|[-]|[_]|[.])+)+");
				Matcher matcher = pattern.matcher(url);
				flag = matcher.matches();
				type = 1;
				break;
			}
		}
		request.setAttribute("resourceType", type);
		return flag;
	}
}
