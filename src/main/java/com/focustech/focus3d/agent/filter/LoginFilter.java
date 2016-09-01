package com.focustech.focus3d.agent.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.focustech.cief.cop.ws.auth.Auth;
import com.focustech.cief.cop.ws.auth.AuthHolder;
import com.focustech.common.utils.ListUtils;
import com.focustech.focus3d.agent.auth.service.AgentResourceService;
import com.focustech.focus3d.agent.auth.service.AgentRoleResourceService;
import com.focustech.focus3d.agent.auth.service.AgentUserRoleService;
import com.focustech.focus3d.agent.model.AgentLogin;
import com.focustech.focus3d.agent.model.AgentResource;
import com.focustech.focus3d.agent.model.AgentRoleResource;
import com.focustech.focus3d.agent.model.AgentUserRole;
/**
 *
 * *
 * @author lihaijun
 *
 */
public class LoginFilter implements Filter {
	public static final String SESSION_KEY = "loginInfo";
	public static final String LOGIN_PAGE_NAME = "login";
	public static final String[] STATIC_RESOURCES = {"script", "images", "style", "fonts", "monitor.html", "index.html", "html", "favicon.ico"};
	public static final String[] DYNAMIC_RESOURCES = {
		"/sms/send"
		, "/register"
		, "/apply/complate"
		, "/wxpay/scanpay/notify"
		, "/wxpay/scanpay/pay"
		, "/agent/rpc/search"
		, "/alipayh5/notifyCallback"
		, "/alipayh5/returnCallback"
		, "/ylpay/backCallback"
		, "/ylpay/frontCallback"
		, "/captchas/*"
		, "/logout"
	};
	public static Auth auth = new Auth();

	@Autowired
	private AgentUserRoleService<AgentUserRole> agentUserRoleService;
	@Autowired
	private AgentRoleResourceService<AgentRoleResource> agentRoleResourceService;
	@Autowired
	private AgentResourceService<AgentResource> agentResourceService;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpSession session = request.getSession();
		Object sessinObj = session.getAttribute(SESSION_KEY);
		String servletPath = request.getServletPath();
		boolean isPass = false;
		if(isIncludePassPath(servletPath)){
			isPass = true;
		} else {
			if(sessinObj == null) {
				response.sendRedirect("/" + LOGIN_PAGE_NAME);
			} else {
				RequestThreadLocal.setLoginInfo(sessinObj);
				isPass = isIncludeAuthPath(servletPath, sessinObj);
			}
		}
		if(isPass){
			AuthHolder.setAuth(auth);
			fc.doFilter(req, resp);
		} else {
			response.setStatus(403);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			response.getOutputStream().print("禁止访问");
		}
	}
	/**
	 * 是否已经授权了访问的菜单
	 * *
	 * @param servletPath
	 * @return
	 */
	public boolean isIncludeAuthPath(String servletPath, Object sessinObj){
		boolean isPass = false;
		if(sessinObj != null) {
			//登录用户验证功能菜单权限
			if(sessinObj instanceof AgentLogin){
				if(servletPath.equals("/index")){
					isPass = true;
				} else {
					isPass = true;
					/*AgentLogin agentLogin = (AgentLogin)sessinObj;
					Long userId = agentLogin.getUserId();
					if(userId != null){
						List<AgentUserRole> roles = agentUserRoleService.getListByUserId(userId);
						if(ListUtils.isNotEmpty(roles)){
							List<AgentRoleResource> resources = agentRoleResourceService.getListByRoleId(roles.get(0).getRoleSn());
							for (AgentRoleResource agentRoleResource : resources) {
								AgentResource resource = agentResourceService.selectBySn(agentRoleResource.getResourceSn(), AgentResource.class);
								if(servletPath.startsWith(resource.getResourceInterface())){
									isPass = true;
									break;
								}
							}
						}
					}
					*/
				}
			}
		}
		return isPass;
	}
	/**
	 *
	 * *
	 * @param servletPath
	 * @return
	 */
	public boolean isIncludePassPath(String servletPath){
		boolean flag = false;
		if(servletPath.equalsIgnoreCase("/" + LOGIN_PAGE_NAME)){
			flag = true;
		} else {
			for(String resource : STATIC_RESOURCES){
				if(servletPath.equalsIgnoreCase("/" + resource)){
					flag = true;
					break;
				}
			}
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
		}
		return flag;
	}



	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig fcg) throws ServletException {
		auth.setUsername("system");
		auth.setUserSn(-1L);
		auth.setFromSubSystem("focus3d_agent");
	}

}
