package org.example.server.controller;

import org.example.server.enums.NewType;
import org.example.server.service.NewService;
import org.example.server.vo.NewVO;
import org.example.server.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewController {

    @Autowired
    NewService newService;

    @PostMapping("/create")
    public ResultVO<Boolean> addNew(@RequestBody NewVO newVO ){
        return ResultVO.buildSuccess(newService.createNew(newVO));
    }

    //删除新闻
    @PostMapping("/delete/{newId}")
    public ResultVO<Boolean> deleteNew(@PathVariable("newId") Integer id){
        return ResultVO.buildSuccess(newService.deleteNew(id));
    }

//    @GetMapping
//    public ResultVO<List<NewVO>> getpics(){}
    //获取所有新闻
    @GetMapping("/all")
    public ResultVO<List<NewVO>> getAllNews(){
        return ResultVO.buildSuccess(newService.getAllNews());
    }

    //获取某个新闻
    @GetMapping("/{newId}")
    public ResultVO<NewVO> getNewById(@PathVariable("newId") Integer id){
        return ResultVO.buildSuccess(newService.getNewById(id));
    }

    //获取不同类型的新闻
    @GetMapping("/type/{type}")
    public ResultVO<List<NewVO>> getNewsByType(@PathVariable("type") NewType type){
        return ResultVO.buildSuccess(newService.getNewsByType(type));
    }

}
