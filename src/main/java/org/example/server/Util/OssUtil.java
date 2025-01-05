package org.example.server.Util;

//导入所需的类和接口。OSS、OSSClientBuilder 是阿里云 OSS SDK 提供的类，用于与 OSS 服务进行交互，@Getter、@Setter、@NoArgsConstructor 是 Lombok 注解，用于自动生成 getter、setter 方法和无参构造方法
import com.aliyun.oss.*;
import com.aliyun.oss.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;

/**
 * @Author: DingXiaoyu
 * @Date: 12:00 2023/12/13
 * 这个类实现了文件上传。
 * @ConfigurationProperties注解能够读取配置文件。
 * 利用了阿里云OSS服务。
 */

//使用 Spring 的注解将这个类标记为一个组件，并通过 @ConfigurationProperties 注解读取配置文件中的属性，前缀为 aliyun.oss。
@Component
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties("aliyun.oss")
public class OssUtil {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;


    //定义了一个名为 upload 的方法，用于上传文件到 OSS。该方法接受两个参数：objectName 表示上传到 OSS 的对象名称，inputStream 表示要上传的文件内容。
    public String upload(String objectName, InputStream inputStream) {
        //创建一个 OSSClient 对象，用于与 OSS 服务进行交互。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //创建了一个上传文件的请求对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
        //调用 putObject 方法上传文件到 OSS。
        try {
            ossClient.putObject(putObjectRequest);
        }finally {
            //确保在上传完成后关闭 OSS 客户端，释放资源
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        //生成一个带签名的 URL，用于访问上传的文件，并返回该 URL。
        return ossClient.generatePresignedUrl(bucketName, objectName, new Date()).toString().split("\\?Expires")[0];
    }
}

