package org.example.devops_server.serviceImpl;

import org.example.devops_server.Exception.DevopsException;
import org.example.devops_server.Util.SecurityUtil;
import org.example.devops_server.Util.TokenUtil;
import org.example.devops_server.enums.RoleEnum;
import org.example.devops_server.po.User;
import org.example.devops_server.repository.UserRepository;
import org.example.devops_server.service.UserService;
import org.example.devops_server.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    SecurityUtil securityUtil;
    
    @Override
    public Boolean register(UserVO userVO) {
        User user = userRepository.findByUsername(userVO.getUsername());

        if (user != null) {
            throw DevopsException.UserAlreadyExists();
        }else if (userRepository.findByEmail(userVO.getEmail())!=null)
        {
            throw DevopsException.UserAlreadyExists();
        }

        User newUser = userVO.toPO();

        newUser.setCreateTime(new Date());
        newUser.setRole(RoleEnum.USER);
        userRepository.save(newUser);
        return true;
    }

    @Override
    public String login(String usernameOrEmail, String password) {
        User user = userRepository.findByUsername(usernameOrEmail);
        if (user==null){
            if ((user=userRepository.findByEmail(usernameOrEmail))==null) {
                throw DevopsException.UserNotExist();
            }
        }
        System.out.println("433434243242");
        String Password=user.getPassword();
        if (Objects.equals(password, Password)) {
            return tokenUtil.getToken(user);
        }else {
            throw DevopsException.usernameOrPasswordError();
        }
    }

    @Override
    public UserVO getInformation() {
        return null;
    }

    @Override
    public Boolean updateInformation(UserVO userVO) {
        return null;
    }

}
