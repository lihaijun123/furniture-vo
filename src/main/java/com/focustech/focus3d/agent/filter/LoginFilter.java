package com.focustech.focus3d.agent.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.focustech.cief.cop.ws.auth.Auth;
import com.focustech.cief.cop.ws.auth.AuthHolder;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.model.AgentLogin;
/**
 *
 * *
 * @author lihaijun
 *
 */
public class LoginFilter extends AbstractFilter {
	public static final String SESSION_KEY = "loginInfo";
	public static final String LOGIN_PAGE_NAME = "login";
	//动态链接
	protected static final String[] DYNAMIC_RESOURCES = {
		"/index.html"
		,"/monitor.html"
		,"/crossdomain.xml"
		,"/cross-domain-policy.xml"
		,"/cross-domain-policy.dtd"
		,"/index"
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
		, "/fnthouse/*"
		, "/fntproduct/*"
		, "/fntprodcate/*"
		, "/rest*"
	};
	public static Auth auth = new Auth();

	/*@Autowired
	private AgentUserRoleService<AgentUserRole> agentUserRoleService;
	@Autowired
	private AgentRoleResourceService<AgentRoleResource> agentRoleResourceService;
	@Autowired
	private AgentResourceService<AgentResource> agentResourceService;*/

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpSession session = request.getSession();
		Object sessinObj = session.getAttribute(SESSION_KEY);
		String servletPath = request.getServletPath();
		boolean isPass = false;
		if(isNotNeedAuthCheckUrl(servletPath, request)){
			if("/index".equals(servletPath)){
				//首页回话设置用户信息
				RequestThreadLocal.setLoginInfo(sessinObj);
			}
			isPass = true;
		} else {
			if(sessinObj == null) {
				response.sendRedirect("/" + LOGIN_PAGE_NAME);
			} else {
				RequestThreadLocal.setLoginInfo(sessinObj);
				isPass = isAuthedUrl(servletPath, sessinObj);
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
	 * 是否是不需要登录的url
	 * *
	 * @param servletPath
	 * @return
	 */
	public boolean isNotNeedAuthCheckUrl(String servletPath, HttpServletRequest request){
		int resourceType = TCUtil.iv(request.getAttribute("resourceType"));
		boolean flag = (resourceType == 1);
		if(!flag){
			for(String resource : DYNAMIC_RESOURCES){
				if(resource.contains("*")){
					Pattern pattern = Pattern.compile("^" + resource.replace("*", ".*"));
					Matcher matcher = pattern.matcher(servletPath);
					if(matcher.matches()){
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
	/**
	 * 是否已经授权了的url
	 * *
	 * @param servletPath
	 * @return
	 */
	public boolean isAuthedUrl(String servletPath, Object sessinObj){
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

	@Override
	public void init(FilterConfig fcg) throws ServletException {
		auth.setUsername("system");
		auth.setUserSn(-1L);
		auth.setFromSubSystem("focus3d_agent");
	}
}
