package org.example.server.serviceImpl;

import org.example.server.Util.OssUtil;
import org.example.server.po.Comment;
import org.example.server.repository.CommentRepository;
import org.example.server.service.CommentService;
import org.example.server.service.NewService;
import org.example.server.service.UserService;
import org.example.server.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserService userService;

    @Autowired
    NewService newService;

    @Autowired
    OssUtil ossUtil;

    @Override
    public Boolean createComment(CommentVO commentVO) {
        Comment comment = commentVO.toPO();
        commentRepository.save(comment);
        userService.addCommentToUserCreated(comment.getId(),comment.getAuthorId());
        newService.addCommentCount(comment.getNewId());
        return true;
    }

    @Override
    public Boolean deleteComment(Integer commentId) {
        Comment comment = commentRepository.findCommentById(commentId);
        userService.deleteCommentFromUserCreated(commentId,comment.getAuthorId());
        newService.subCommentCount(comment.getNewId());
        commentRepository.deleteById(commentId);
        return true;
    }

    @Override
    public CommentVO getCommentById(Integer commentId) {
        return commentRepository.findCommentById(commentId).toVO();
    }

    @Override
    public List<CommentVO> getCommentsByNew(Integer newId) {
        List<Comment> comments = commentRepository.findByNewId(newId);
        List<CommentVO> commentVOS = new ArrayList<>();
        for (Comment comment : comments) {
            commentVOS.add(comment.toVO());
        }
        return commentVOS;
    }

    @Override
    public List<CommentVO> getReplyByComment(Integer commentId) {
        Comment comment = commentRepository.findCommentById(commentId);
        List<Integer> comments = comment.getReplyCommentList();
        List<CommentVO> commentVOS = new ArrayList<>();
        for (Integer commentId1 : comments) {
            commentVOS.add(commentRepository.findCommentById(commentId1).toVO());
        }
        return commentVOS;
    }

    @Override
    public Boolean addLikeCount(Integer commentId) {
        Comment comment = commentRepository.findCommentById(commentId);
        comment.setLikeCount(comment.getLikeCount()+1);
        commentRepository.save(comment);
        return true;
    }

    @Override
    public Boolean subLikeCount(Integer commentId) {
        Comment comment = commentRepository.findCommentById(commentId);
        comment.setLikeCount(comment.getLikeCount()-1);
        commentRepository.save(comment);
        return true;
    }

}
