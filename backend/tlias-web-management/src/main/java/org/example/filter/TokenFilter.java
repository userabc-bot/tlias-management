package org.example.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.CurrentHolder;
import org.example.utils.JwtUtils;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        //1.获取请求url
        String requestURL = httpServletRequest.getRequestURL().toString();

        //2.检查输入的网址是不是登录的网址
        if (requestURL.contains("/login")){
            log.info("登录请求直接放行");
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }



        //3.检查前端是否携带了令牌
        //获取请求头中的令牌
        String token = httpServletRequest.getHeader("token");
        //判断令牌是否为空
        if (token==null || token.isEmpty()){
            //向控制台反应错误
            log.info("令牌为空，无法操作");
            //向前端反应错误（使用response向前端响应401）
            httpServletResponse.setStatus(401);
            return;
        }
        //解析令牌
        try {
            Claims claims = JwtUtils.parseToken(token);
            /*Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);*/
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
        } catch (Exception e) {
            //向控制台反应错误
            log.info("解析令牌时出现错误,无法继续操作");
            //向前端反应错误（使用response向前端响应401状态码）
            httpServletResponse.setStatus(401);
            return;
        }

        //放行
        log.info("令牌正确，放行");
        filterChain.doFilter(httpServletRequest,httpServletResponse);

        //清空当前线程绑定的id
        CurrentHolder.remove();
    }
}
