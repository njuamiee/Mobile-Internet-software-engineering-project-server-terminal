package org.example.server.service;

import org.example.server.enums.NewType;
import org.example.server.vo.NewVO;

import java.util.List;

public interface NewService {

    Boolean createNew(NewVO newVO);
    Boolean deleteNew(Integer newId);

    List<NewVO> getAllNews();
    NewVO getNewById(Integer newId);
    List<NewVO> getNewsByType(NewType type);

    Boolean addLikeCount(Integer newId);
    Boolean addCollectCount(Integer newId);
    Boolean addCommentCount(Integer newId);
    Boolean subLikeCount(Integer newId);
    Boolean subCollectCount(Integer newId);
    Boolean subCommentCount(Integer newId);
}
