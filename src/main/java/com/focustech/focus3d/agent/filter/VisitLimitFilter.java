package com.focustech.focus3d.agent.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
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
public class VisitLimitFilter implements Filter {
	//静态文件目录
	private static String[] STATIC_FILE_DIR = new String[]{"css", "style", "images", "fileUpload", "fonts", "script", "js"};
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String url = req.getRequestURI();
		boolean isLimitVisitUrl = isLimitUrl(url);;
		if(isLimitVisitUrl){
			//返回默认资源
			resp.setStatus(403);
			return;
		}
		fc.doFilter(req, resp);
	}

	/**
	 * 是否是非法访问资源url
	 * *
	 * @param url
	 * @return
	 */
	private boolean isLimitUrl(String url){
		url = url.toLowerCase();
		for(String dir : STATIC_FILE_DIR){
			if(url.startsWith("/" + dir)){
				Pattern pattern = Pattern.compile("^/" + dir + "(/?([a-zA-Z]|[0-9]|[-]|[_]|[.])+)+");
				Matcher matcher = pattern.matcher(url);
				return !matcher.matches();
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {

	}
}
