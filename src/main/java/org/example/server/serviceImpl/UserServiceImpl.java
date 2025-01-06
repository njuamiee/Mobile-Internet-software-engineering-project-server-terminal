package org.example.server.serviceImpl;

import org.example.server.Exception.Exception;
import org.example.server.Util.AESUtil;
import org.example.server.Util.SecurityUtil;
import org.example.server.Util.TokenUtil;
import org.example.server.enums.RoleEnum;
import org.example.server.po.New;
import org.example.server.po.User;
import org.example.server.repository.UserRepository;
import org.example.server.service.CommentService;
import org.example.server.service.NewService;
import org.example.server.service.UserService;
import org.example.server.vo.CommentVO;
import org.example.server.vo.NewVO;
import org.example.server.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    NewService newService;

    @Autowired
    CommentService commentService;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    AESUtil aesUtil;

    @Autowired
    SecurityUtil securityUtil;
    
    @Override
    public Boolean register(UserVO userVO) throws java.lang.Exception {
        User user = userRepository.findByUsername(userVO.getUsername());
        if (user != null) {
            throw Exception.UserAlreadyExists();
        }
        User newUser = userVO.toPO();
        newUser.setCreateTime(new Date());
        newUser.setRole(RoleEnum.USER);
        String password = aesUtil.encrypt(userVO.getPassword());
        newUser.setPassword(password);
        userRepository.save(newUser);
        return true;
    }

    @Override
    public String login(String usernameOrEmail, String password) throws java.lang.Exception {
        User user = userRepository.findByUsername(usernameOrEmail);
        String Password=user.getPassword();
        if (Objects.equals(aesUtil.decrypt(Password), password)) {
            return tokenUtil.getToken(user);
        }else {
            throw Exception.usernameOrPasswordError();
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

    @Override
    public Boolean addNewToUserCreated(Integer newId, Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Integer> news = user.getCreateNewsList();
        if (news.contains(newId)) {
            return false;
        }
        news.add(newId);
        user.setCreateNewsList(news);
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean deleteNewFromUserCreated(Integer newId, Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Integer> news = user.getCreateNewsList();
        if (!news.contains(newId)) {
            return false;
        }
        news.remove(newId);
        user.setCreateNewsList(news);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<NewVO> getNewsByUserCreated(Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Integer> news = user.getCreateNewsList();
        List<NewVO> newVOS = new ArrayList<>();
        for (Integer newId : news) {
            NewVO aNewVO = newService.getNewById(newId);
            newVOS.add(aNewVO);
        }
        return newVOS;
    }

    @Override
    public List<NewVO> getLikeNews(Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Integer> news = user.getLikeNewsList();
        List<NewVO> newVOS = new ArrayList<>();
        for (Integer newId : news) {
            NewVO aNewVO = newService.getNewById(newId);
            newVOS.add(aNewVO);
        }
        return newVOS;
    }

    @Override
    public Boolean likeNew(Integer newId, Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Integer> news = user.getLikeNewsList();
        if (news.contains(newId)) {
            return false;
        }
        news.add(newId);
        user.setLikeNewsList(news);
        userRepository.save(user);
        newService.addLikeCount(newId);
        return true;
    }

    @Override
    public Boolean cancelLikeNew(Integer newId, Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Integer> news = user.getLikeNewsList();
        if (!news.contains(newId)) {
            return false;
        }
        news.remove(newId);
        user.setLikeNewsList(news);
        userRepository.save(user);
        newService.subLikeCount(newId);
        return true;
    }

    @Override
    public List<NewVO> getCollectNews(Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Integer> news = user.getCollectNewsList();
        List<NewVO> newVOS = new ArrayList<>();
        for (Integer newId : news) {
            NewVO aNewVO = newService.getNewById(newId);
            newVOS.add(aNewVO);
        }
        return newVOS;
    }

    @Override
    public Boolean collectNew(Integer newId, Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Integer> news = user.getCollectNewsList();
        if (news.contains(newId)) {
            return false;
        }
        news.add(newId);
        user.setCollectNewsList(news);
        userRepository.save(user);
        newService.addCollectCount(newId);
        return true;
    }

    @Override
    public Boolean cancelCollectNew(Integer newId, Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Integer> news = user.getCollectNewsList();
        if (!news.contains(newId)) {
            return false;
        }
        news.remove(newId);
        user.setCollectNewsList(news);
        userRepository.save(user);
        newService.subCollectCount(newId);
        return true;
    }

    @Override
    public Boolean addCommentToUserCreated(Integer commentId, Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Integer> comments = user.getCreateCommentList();
        if (comments.contains(commentId)) {
            return false;
        }
        comments.add(commentId);
        user.setCreateCommentList(comments);
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean deleteCommentFromUserCreated(Integer commentId, Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Integer> comments = user.getCreateCommentList();
        if (!comments.contains(commentId)) {
            return false;
        }
        comments.remove(commentId);
        user.setCreateCommentList(comments);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<CommentVO> getCommentsByUserCreated(Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Integer> comments = user.getCreateCommentList();
        List<CommentVO> commentVOS = new ArrayList<>();
        for (Integer commentId : comments) {
            CommentVO commentVO = commentService.getCommentById(commentId);
            commentVOS.add(commentVO);
        }
        return commentVOS;
    }

    @Override
    public Boolean likeComment(Integer commentId, Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Integer> comments = user.getLikeCommentList();
        if (comments.contains(commentId)) {
            return false;
        }
        comments.add(commentId);
        user.setLikeCommentList(comments);
        userRepository.save(user);
        commentService.addLikeCount(commentId);
        return true;
    }

    @Override
    public Boolean cancelLikeComment(Integer commentId, Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Integer> comments = user.getLikeCommentList();
        if (!comments.contains(commentId)) {
            return false;
        }
        comments.remove(commentId);
        user.setLikeCommentList(comments);
        userRepository.save(user);
        commentService.subLikeCount(commentId);
        return true;
    }

    @Override
    public List<CommentVO> getLikeCommentsByUser(Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Integer> comments = user.getLikeCommentList();
        List<CommentVO> commentVOS = new ArrayList<>();
        for (Integer commentId : comments) {
            CommentVO commentVO = commentService.getCommentById(commentId);
            commentVOS.add(commentVO);
        }
        return commentVOS;
    }

}
