package org.example.server.service;

import org.example.server.vo.CommentVO;

import java.util.List;

public interface CommentService {

    Boolean createComment(CommentVO commentVO);
    Boolean deleteComment(Integer commentId);
    CommentVO getCommentById(Integer commentId);
    List<CommentVO> getCommentsByNew(Integer newId);
    Boolean addLikeCount(Integer commentId);
    Boolean subLikeCount(Integer commentId);
}
