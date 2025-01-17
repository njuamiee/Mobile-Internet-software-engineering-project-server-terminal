package org.example.server.service;

import org.example.server.po.User;
import org.example.server.vo.CommentVO;
import org.example.server.vo.NewVO;
import org.example.server.vo.UserVO;

import java.util.List;

public interface UserService {
    Boolean register(UserVO userVO) throws Exception;
    UserVO login(UserVO userVO) throws Exception;
    UserVO getInformation();
    Boolean updateInformation(UserVO userVO);
    UserVO getUserInfoById(Integer id);
    //添加到用户创建的新闻中
    Boolean addNewToUserCreated(Integer newId, Integer userId);
    //从用户创建的新闻中删除
    Boolean deleteNewFromUserCreated(Integer newId, Integer userId);
    List<NewVO> getNewsByUserCreated(Integer userId);

    List<NewVO> getLikeNews(Integer userId);
    Boolean likeNew(Integer newId, Integer userId);
    Boolean cancelLikeNew(Integer newId, Integer userId);

    List<NewVO> getCollectNews(Integer userId);
    Boolean collectNew(Integer newId, Integer userId);
    Boolean cancelCollectNew(Integer newId, Integer userId);

    Boolean addCommentToUserCreated(Integer commentId, Integer userId);
    Boolean deleteCommentFromUserCreated(Integer commentId, Integer userId);
    List<CommentVO> getCommentsByUserCreated(Integer userId);

    Boolean likeComment(Integer commentId, Integer userId);
    Boolean cancelLikeComment(Integer commentId, Integer userId);
    List<CommentVO> getLikeCommentsByUser(Integer userId);
}
