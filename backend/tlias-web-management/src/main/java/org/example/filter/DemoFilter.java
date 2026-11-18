package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Slf4j
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化方法");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到了请求。。。。。放行前的操作");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("拦截到了请求。。。。。放行后的操作");
    }

    @Override
    public void destroy() {
        log.info("摧毁方法");
    }
}
