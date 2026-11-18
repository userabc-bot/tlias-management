package org.example.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        //1.获取请求url
        String requestURL = httpServletRequest.getRequestURL().toString();

        //2.检查输入的网址是不是登录的网址
        if (requestURL.contains("/login")){
            log.info("登录请求直接放行");
            return true;
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
            return false;
        }
        //解析令牌
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            //向控制台反应错误
            log.info("解析令牌时出现错误,无法继续操作");
            //向前端反应错误（使用response向前端响应401状态码）
            httpServletResponse.setStatus(401);
            return false;
        }

        //放行
        log.info("令牌正确，放行");
        return true;
    }
}
