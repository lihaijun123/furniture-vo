package com.focustech.focus3d.agent.filter;

import java.io.IOException;

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
	public static final String SESSION_KEY = "loginInfo";
	public static final String LOGIN_PAGE_NAME = "login";
	//静态目录
	protected static String[] STATIC_FILE_DIR = new String[]{"/index.html", "css", "style", "images", "fileUpload", "fonts", "font-awesome", "script", "js", "html"};
	//动态链接
	protected static final String[] DYNAMIC_RESOURCES = {
		"/index"
		,"/sms/send"
		, "/register*"
		, "/apply/complate"
		, "/wxpay/scanpay/notify"
		, "/wxpay/scanpay/pay"
		, "/agent/rpc/search"
		, "/alipayh5/notifyCallback"
		, "/alipayh5/returnCallback"
		, "/ylpay/backCallback"
		, "/ylpay/frontCallback"
		, "/captchas/*"
		, "/" + LOGIN_PAGE_NAME
		, "/logout"
	};
	/**
	 * 
	 * *
	 * @param servletUrl
	 * @return
	 */
	public boolean isStaticResourceUrl(String servletUrl){
		boolean flag = false;
		servletUrl = servletUrl.toLowerCase();
		for(String dir : STATIC_FILE_DIR){
			if(servletUrl.startsWith("/" + dir) || servletUrl.startsWith(dir)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	/**
	 * 是否是不需要登录的url
	 * *
	 * @param servletPath
	 * @return
	 */
	public boolean isNotNeedAuthCheckUrl(String servletPath){
		boolean flag = isStaticResourceUrl(servletPath);
		if(!flag){
			for(String resource : DYNAMIC_RESOURCES){
				if(resource.endsWith("*")){
					String substring = resource.substring(0, resource.indexOf("*"));
					if(servletPath.startsWith(substring)){
						flag = true;
						break;
					}
				} else if(servletPath.equalsIgnoreCase(resource)){
					flag = true;
					break;
				}
			}
		}
	
		return flag;
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
