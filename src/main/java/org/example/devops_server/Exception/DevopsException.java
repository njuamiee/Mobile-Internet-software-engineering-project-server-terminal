package org.example.devops_server.Exception;


public class DevopsException extends RuntimeException{

    public DevopsException(String message){
        super(message);
    }
    public static DevopsException userAlreadyExists(){
        return new DevopsException("用户已经存在!");
    }
    public static DevopsException fileUploadFail()
    {
        return new DevopsException("文件上传失败！");
    }
    public static DevopsException notLogin(){
        return new DevopsException("未登录!");
    }
    public static DevopsException UserNotExist(){
        return new DevopsException("用户不存在！");
    }

    public static DevopsException usernameOrPasswordError(){
        return new DevopsException("用户名或密码错误!");
    }


    public static DevopsException roleWrong() {return new DevopsException("身份错误！");}

    public static DevopsException UserAlreadyExists() {
    return  new DevopsException("用户已经存在！");
    }



}
