package com.start.boot.config;


import com.start.boot.support.utils.HttpContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


@Component
public class SessionInterceptor implements HandlerInterceptor {

    // 放行列表
    private  String uncheckUri ="/organization/GetDwbmTree,/account/loginDwbmTree,/account/signIn,/error";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取请求路径
        String uri = request.getRequestURI();
        String header = request.getHeader("X-Requested-with");
        if ("XMLHttpRequest".equals(header)){
            // 检测session是否失效
            if (isInterceptor(uri)) {
                //request.getco
                Object object = HttpContextUtils.getSession("user");
                if (object == null) {
                    // 跳转到登录页面
                    // response.sendRedirect(request.getContextPath()+"/login.html");
                    //   request.getRequestDispatcher("/login.html").forward(request, response);
                    PrintWriter writer = response.getWriter();
                    writer.write("sessionNull");
                    writer.flush();
                    writer.close();
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    // 针对某些请求不拦截
    public boolean isInterceptor(String uri) {
        for (String uncheck : uncheckUri.split(",")) {
            if (uri.contains(uncheck)) {
                return false;
            }
        }
        return true;
    }
}
