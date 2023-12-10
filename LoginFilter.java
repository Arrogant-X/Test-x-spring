package com.util;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init-----------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("------进入拦截器-----------");
        //排除login.jsp /login
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        String path=request.getServletPath().toString();
        System.out.println("当前请求路径："+path);
        if(path.equals("/login.jsp")||path.equals("/loginServlet"))
        {
            System.out.println("不拦截");
            //进入服务器
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else
        {
            System.out.println("拦截");
            //跳转到login
            HttpSession session=request.getSession();
            if(session.getAttribute("login")!=null)
            {
                System.out.println("已经登陆，进入服务器");
                //已经登陆了
                //进入服务器
                filterChain.doFilter(servletRequest,servletResponse);
            }
            else
            {
                System.out.println("没有登陆");
                HttpServletResponse response=(HttpServletResponse)servletResponse;
                response.sendRedirect("login.jsp");
            }

        }

    }

    @Override
    public void destroy() {
        System.out.println("dis---------------");
    }
}
