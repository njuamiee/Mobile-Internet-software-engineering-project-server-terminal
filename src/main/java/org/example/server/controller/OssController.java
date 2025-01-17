package org.example.server.controller;

import org.example.server.Util.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/oss")
public class OssController {
    @Autowired
    private OssUtil ossUtil;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 生成文件名
            String fileName = file.getOriginalFilename();
            if (fileName == null || fileName.isEmpty()) {
                return "文件名不能为空";
            }

            // 生成唯一的对象名称，防止文件名冲突
            String objectName = System.currentTimeMillis() + "_" + fileName;

            // 上传文件并获取URL
            String url = ossUtil.upload(objectName, file.getInputStream());
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败: " + e.getMessage();
        }
    }
}