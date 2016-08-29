package com.focustech.focus3d.agent.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.web.filter.OncePerRequestFilter;
/**
 *
 * *
 * @author lihaijun
 *
 */
public class RequestMessageCookieFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain ch) throws ServletException, IOException {
		//取出cookie
		String cookieValue = "";
        int maxAge = -1;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(RequestMessageCookie.COOKIE_KEY)) {
                    cookieValue = cookie.getValue();
                    //maxAge = cookie.getMaxAge();
                    break;
                }
            }
        }
        RequestMessageCookie requestMessageCookie = RequestThreadLocal.getMessageCookie();
        requestMessageCookie.build(cookieValue);
        RequestMessageResponseWrapper requestMessageResponseWrapper = new RequestMessageResponseWrapper(response);
		ch.doFilter(request, requestMessageResponseWrapper);
		//写cookie
		if (!requestMessageResponseWrapper.isCommitted()) {
			requestMessageResponseWrapper.addCookie();
	    }
	}

	class RequestMessageResponseWrapper extends HttpServletResponseWrapper {

		public RequestMessageResponseWrapper(HttpServletResponse response) {
			super(response);
		}
		@Override
		public void sendRedirect(String location) throws IOException {
			addCookie();
			super.sendRedirect(location);
		}

		private void addCookie() {
			RequestMessageCookie requestMessageCookie = RequestThreadLocal.getMessageCookie();
			Cookie cookie = new Cookie(RequestMessageCookie.COOKIE_KEY, requestMessageCookie.getCookieValue());
			cookie.setPath("/");
			cookie.setMaxAge(-1);
			this.addCookie(cookie);
			RequestThreadLocal.cleanAll();
		}
	}
}


