package com.example.hello.interceptor;

import com.example.hello.annotation.UserTokenCheck;
import com.example.hello.model.User;
import com.example.hello.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
@Slf4j
public class UserAuthorizationInterceptor implements HandlerInterceptor {
    public static final String USER_INFO = "USER_INFO";
    @Autowired
    IUserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        UserTokenCheck annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(UserTokenCheck.class);
        } else {
            return true;
        }

        if (annotation == null || annotation.validate() == false) {
            return true;
        }
        String token = request.getHeader("token");
        if (token == null) {
            log.info("缺少token，拒绝访问");
            return false;
        }
        Optional<User> user = userService.findByToken(token);
        if (!user.isPresent()) {
            log.info("无效的token");
            return false;
        }
        String id = request.getParameter("id");
        return true;
    }
}