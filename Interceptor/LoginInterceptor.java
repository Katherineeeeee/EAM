package com.rookie.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

/**
 * 登录拦截器, 如果没有登录成功, 那么将跳转至登录页面
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userToken = null;//用户token
        String managerToken = null;//管理员token
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (userToken == null && cookie.getName().equals("user")) {
                    userToken = cookie.getValue();
                }
                if (managerToken == null && cookie.getName().equals("manager")) {
                    managerToken = cookie.getValue();
                }
                if (userToken != null && managerToken != null) {
                    break;
                }
            }

            //如果当前访问的是用户的相关网页,并且token是对的,则正常跳转
            if (userToken != null && session.getAttribute(userToken) != null && Pattern.matches("/api/user.*", request.getRequestURI())) {
                return true;
            }

            //如果当前访问的管理员的相关网页, 并且token是对的, 则正常跳转
            if (managerToken != null && session.getAttribute(managerToken) != null && Pattern.matches("/api/manager.*", request.getRequestURI())) {
                return true;
            }
        }
        response.sendRedirect("/");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
