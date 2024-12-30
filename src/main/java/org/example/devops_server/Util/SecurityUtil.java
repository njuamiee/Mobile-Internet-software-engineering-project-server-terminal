package org.example.devops_server.Util;



import org.example.devops_server.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class SecurityUtil {

    @Autowired
    HttpServletRequest httpServletRequest;

    public User getCurrentUser(){
        return (User)httpServletRequest.getSession().getAttribute("currentUser");
    }
}
