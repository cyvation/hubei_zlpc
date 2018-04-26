package com.start.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SessionConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      // registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/");
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns("/account/signIn");
       super.addInterceptors(registry);
    }
}
