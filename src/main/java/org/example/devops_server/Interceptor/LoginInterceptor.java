package org.example.devops_server.Interceptor;



import org.example.devops_server.Exception.DevopsException;
import org.example.devops_server.Util.TokenUtil;
import org.example.devops_server.annotations.RoleAllowed;
import org.example.devops_server.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    TokenUtil tokenUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        //System.out.println(token);
        if (token != null && tokenUtil.verifyToken(token)) {
            request.getSession().setAttribute("currentUser",tokenUtil.getUser(token));
            RoleEnum role = tokenUtil.getClaims(token);

            if (handler.getClass().isAnnotationPresent(RoleAllowed.class)) {

                RoleAllowed roleAllowed = handler.getClass().getAnnotation(RoleAllowed.class);
                for (RoleEnum requiredRole : roleAllowed.value()) {
                    if (role.equals(requiredRole)) {
                        return true;
                    }
                }
                throw DevopsException.roleWrong();
            } else if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                if (handlerMethod.getMethod().isAnnotationPresent(RoleAllowed.class)) {
                    RoleAllowed roleAllowed = handlerMethod.getMethod().getAnnotation(RoleAllowed.class);
                    for (RoleEnum requiredRole : roleAllowed.value()) {
                        if (role.equals(requiredRole)) {

                            return true;
                        }
                    }
                    throw DevopsException.roleWrong();
                }
            }
        }else {
            throw DevopsException.notLogin();
        }

        return true;

    }
}
