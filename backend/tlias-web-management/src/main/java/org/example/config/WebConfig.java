package org.example.config;

import org.example.interceptor.DemoInterceptor;
import org.example.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //注入DemoInterceptor对象
    /*@Autowired
    DemoInterceptor demoInterceptor;*/

    /*@Autowired
    TokenInterceptor tokenInterceptor;*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //使用InterceptorRegistry的对象添加了拦截器和需要拦截的路径
        //registry.addInterceptor(tokenInterceptor).addPathPatterns("/*").excludePathPatterns("/emps");
        //registry.addInterceptor(demoInterceptor).addPathPatterns("/*");
    }
}
