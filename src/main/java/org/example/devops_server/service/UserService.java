package org.example.devops_server.service;

import org.example.devops_server.vo.UserVO;

public interface UserService {
    Boolean register(UserVO userVO);

    String login(String phone, String password);

    UserVO getInformation();

    Boolean updateInformation(UserVO userVO);
}
