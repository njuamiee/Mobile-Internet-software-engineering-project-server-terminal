package org.example.server.Exception;


public class Exception extends RuntimeException{

    public Exception(String message){
        super(message);
    }
    public static Exception userAlreadyExists(){
        return new Exception("用户已经存在!");
    }
    public static Exception fileUploadFail()
    {
        return new Exception("文件上传失败！");
    }
    public static Exception notLogin(){
        return new Exception("未登录!");
    }
    public static Exception UserNotExist(){
        return new Exception("用户不存在！");
    }

    public static Exception usernameOrPasswordError(){
        return new Exception("用户名或密码错误!");
    }


    public static Exception roleWrong() {return new Exception("身份错误！");}

    public static Exception UserAlreadyExists() {
    return  new Exception("用户已经存在！");
    }



}
