package org.example.server.serviceImpl;

import org.example.server.Util.OssUtil;
import org.example.server.enums.NewType;
import org.example.server.po.New;
import org.example.server.repository.NewRepository;
import org.example.server.service.NewService;
import org.example.server.service.UserService;
import org.example.server.vo.NewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewServiceImpl implements NewService {

    @Autowired
    NewRepository newRepository;

    @Autowired
    UserService userService;

    @Autowired
    OssUtil ossUtil;



    @Override
    public Boolean createNew(NewVO newVO) {


        New newPO = newVO.toPO();

        newRepository.save(newPO);
        userService.addNewToUserCreated(newPO.getId(),newPO.getAuthorId());
        return true;
    }

    @Override
    public Boolean deleteNew(Integer newId) {
        New aNew = newRepository.findNewById(newId);
        userService.deleteNewFromUserCreated(newId,aNew.getAuthorId());
        newRepository.deleteById(newId);
        return true;
    }

    @Override
    public List<NewVO> getAllNews() {
        List<New> news = newRepository.findAll();
        List<NewVO> newVOS = new ArrayList<>();
        for (New aNew : news) {
            newVOS.add(aNew.toVO());
        }
        return newVOS;
    }

    @Override
    public NewVO getNewById(Integer newId) {
        New aNew = newRepository.findNewById(newId);
        return aNew.toVO();
    }

    @Override
    public List<NewVO> getNewsByType(NewType type) {
        List<New> news = newRepository.findByType(type);
        List<NewVO> newVOS = new ArrayList<>();
        for (New aNew : news) {
            newVOS.add(aNew.toVO());
        }
        return newVOS;
    }

    @Override
    public Boolean addLikeCount(Integer newId) {
        New aNew = newRepository.findNewById(newId);
        aNew.setLikeCount(aNew.getLikeCount()+1);
        newRepository.save(aNew);
        return true;
    }

    @Override
    public Boolean addCollectCount(Integer newId) {
        New aNew = newRepository.findNewById(newId);
        aNew.setCollectCount(aNew.getCollectCount()+1);
        newRepository.save(aNew);
        return true;
    }

    @Override
    public Boolean addCommentCount(Integer newId) {
        New aNew = newRepository.findNewById(newId);
        aNew.setCommentCount(aNew.getCommentCount()+1);
        newRepository.save(aNew);
        return true;
    }

    @Override
    public Boolean subLikeCount(Integer newId) {
        New aNew = newRepository.findNewById(newId);
        aNew.setLikeCount(aNew.getLikeCount()-1);
        newRepository.save(aNew);
        return true;
    }

    @Override
    public Boolean subCollectCount(Integer newId) {
        New aNew = newRepository.findNewById(newId);
        aNew.setCollectCount(aNew.getCollectCount()-1);
        newRepository.save(aNew);
        return true;
    }

    @Override
    public Boolean subCommentCount(Integer newId) {
        New aNew = newRepository.findNewById(newId);
        aNew.setCommentCount(aNew.getCommentCount()-1);
        newRepository.save(aNew);
        return true;
    }

}
