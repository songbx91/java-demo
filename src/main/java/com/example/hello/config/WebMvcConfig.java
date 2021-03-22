package com.example.hello.config;

import com.example.hello.component.EnumConvertFactory;
import com.example.hello.component.IntegerCodeToEnumConverterFactory;
import com.example.hello.component.LoginUserHandlerMethodArgumentResolver;
import com.example.hello.component.StringCodeToEnumConverterFactory;
import com.example.hello.interceptor.JWTCheckInterceptor;
import com.example.hello.interceptor.UserAuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private UserAuthorizationInterceptor userAuthorizationInterceptor;
    @Autowired
    private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;
    @Autowired
    private JWTCheckInterceptor jwtCheckInterceptor;

    @Autowired
    private IntegerCodeToEnumConverterFactory integerCodeToEnumConverterFactory;
    @Autowired
    private StringCodeToEnumConverterFactory stringCodeToEnumConverterFactory;
    @Autowired
    private EnumConvertFactory enumConvertFactory;

    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverterFactory(integerCodeToEnumConverterFactory);
//        registry.addConverterFactory(stringCodeToEnumConverterFactory);
        registry.addConverterFactory(enumConvertFactory);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userAuthorizationInterceptor).addPathPatterns("/v1/user/**");
        registry.addInterceptor(jwtCheckInterceptor).addPathPatterns("/v1/jwt-*");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
    }
}
