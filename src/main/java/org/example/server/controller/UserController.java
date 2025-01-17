package org.example.server.controller;


import org.example.server.service.UserService;
import org.example.server.vo.CommentVO;
import org.example.server.vo.NewVO;
import org.example.server.vo.ResultVO;
import org.example.server.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResultVO<Boolean> register(@RequestBody UserVO userVO ) throws Exception {
        return ResultVO.buildSuccess(userService.register(userVO));
    }



    @PostMapping("/login")
    public ResultVO<UserVO> login(@RequestBody UserVO userVO) throws Exception {
        return ResultVO.buildSuccess(userService.login(userVO));
    }

//    @GetMapping
//    public ResultVO<UserVO> getInformation(){
//        return ResultVO.buildSuccess(userService.getInformation());
//    }

    @GetMapping
    public ResultVO<UserVO> getUserInfoById(@RequestBody UserVO userVO){
        return ResultVO.buildSuccess(userService.getUserInfoById(userVO.getId()));
    }
    @PostMapping
    public ResultVO<Boolean> updateInformation(@RequestBody UserVO userVO){
        return ResultVO.buildSuccess(userService.updateInformation(userVO));
    }

    //获取用户创建的新闻
    @GetMapping("/user/{userId}")
    public ResultVO<List<NewVO>> getNewsByUser(@PathVariable("userId") Integer userId){
        return ResultVO.buildSuccess(userService.getNewsByUserCreated(userId));
    }

    //获取用户点赞的新闻
    @GetMapping("/like/{userId}")
    public ResultVO<List<NewVO>> getLikeNews(@PathVariable("userId") Integer userId){
        return ResultVO.buildSuccess(userService.getLikeNews(userId));
    }

    //获取用户收藏的新闻
    @GetMapping("/collect/{userId}")
    public ResultVO<List<NewVO>> getCollectNews(@PathVariable("userId") Integer userId){
        return ResultVO.buildSuccess(userService.getCollectNews(userId));
    }

    //用户点赞新闻
    @PostMapping("/like/{newId}/{userId}")
    public ResultVO<Boolean> likeNew(@PathVariable("newId") Integer newId, @PathVariable("userId") Integer userId){
        return ResultVO.buildSuccess(userService.likeNew(newId, userId));
    }

    //用户取消点赞新闻
    @PostMapping("/unlike/{newId}/{userId}")
    public ResultVO<Boolean> unlikeNew(@PathVariable("newId") Integer newId, @PathVariable("userId") Integer userId){
        return ResultVO.buildSuccess(userService.cancelLikeNew(newId, userId));
    }

    //用户收藏新闻
    @PostMapping("/collect/{newId}/{userId}")
    public ResultVO<Boolean> collectNew(@PathVariable("newId") Integer newId, @PathVariable("userId") Integer userId){
        return ResultVO.buildSuccess(userService.collectNew(newId, userId));
    }

    //用户取消收藏新闻
    @PostMapping("/uncollect/{newId}/{userId}")
    public ResultVO<Boolean> uncollectNew(@PathVariable("newId") Integer newId, @PathVariable("userId") Integer userId){
        return ResultVO.buildSuccess(userService.cancelCollectNew(newId, userId));
    }

//    //获取用户的所有评论
//    @GetMapping("/user/{userId}")
//    public ResultVO<List<CommentVO>> getCommentsByUser(@PathVariable("userId") Integer userId){
//        return ResultVO.buildSuccess(userService.getCommentsByUserCreated(userId));
//    }

    //用户点赞评论
    @GetMapping("/like/{commentId}/{userId}")
    public ResultVO<Boolean> likeComment(@PathVariable("commentId") Integer commentId, @PathVariable("userId") Integer userId){
        return ResultVO.buildSuccess(userService.likeComment(commentId, userId));
    }

    //用户取消点赞评论
    @GetMapping("/unlike/{commentId}/{userId}")
    public ResultVO<Boolean> unlikeComment(@PathVariable("commentId") Integer commentId, @PathVariable("userId") Integer userId){
        return ResultVO.buildSuccess(userService.cancelLikeComment(commentId, userId));
    }

    //获取用户点赞的评论
    @GetMapping("/user/like/{userId}")
    public ResultVO<List<CommentVO>> getLikeCommentsByUser(@PathVariable("userId") Integer userId){
        return ResultVO.buildSuccess(userService.getLikeCommentsByUser(userId));
    }
}