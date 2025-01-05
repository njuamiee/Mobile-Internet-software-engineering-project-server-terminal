package org.example.server.controller;

import org.example.server.service.CommentService;
import org.example.server.vo.CommentVO;
import org.example.server.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    //用户创建评论
    @GetMapping("/create")
    public ResultVO<Boolean> addComment(@RequestBody CommentVO commentVO){
        return ResultVO.buildSuccess(commentService.createComment(commentVO));
    }

    //删除评论
    @GetMapping("/delete/{commentId}")
    public ResultVO<Boolean> deleteComment(@PathVariable("commentId") Integer commentId){
        return ResultVO.buildSuccess(commentService.deleteComment(commentId));
    }

    //获取某新闻的评论
    @GetMapping("/{newId}")
    public ResultVO<List<CommentVO>> getCommentsByNew(@PathVariable("newId") Integer newId){
        return ResultVO.buildSuccess(commentService.getCommentsByNew(newId));
    }

    //获取某评论的回复
    @GetMapping("/reply/{commentId}")
    public ResultVO<List<CommentVO>> getReplyByComment(@PathVariable("commentId") Integer commentId){
        return ResultVO.buildSuccess(commentService.getReplyByComment(commentId));
    }
}
