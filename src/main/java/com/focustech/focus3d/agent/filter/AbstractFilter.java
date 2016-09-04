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
/**
 * 
 * *
 * @author lihaijun
 *
 */
public class AbstractFilter implements Filter {
	//静态文件目录
	protected static String[] STATIC_FILE_DIR = new String[]{"css", "style", "images", "fileUpload", "fonts", "font-awesome", "script", "js", "html"};
	/**
	 * 是否是静态资源url
	 * *
	 * @param url
	 * @return
	 */
	protected boolean isStaticResourceUrl(String url){
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
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
