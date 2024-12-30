package org.example.devops_server.controller;


import org.example.devops_server.service.UserService;
import org.example.devops_server.vo.ResultVO;
import org.example.devops_server.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResultVO<Boolean> register(@RequestBody UserVO userVO ){
        return ResultVO.buildSuccess(userService.register(userVO));
    }

    @PostMapping("/login")
    public ResultVO<String> login(@RequestParam("username") String username, @RequestParam("password") String password){
        return ResultVO.buildSuccess(userService.login(username, password));
    }



    @GetMapping
    public ResultVO<UserVO> getInformation(){
        return ResultVO.buildSuccess(userService.getInformation());
    }

    @PostMapping
    public ResultVO<Boolean> updateInformation(@RequestBody UserVO userVO){
        return ResultVO.buildSuccess(userService.updateInformation(userVO));
    }



}