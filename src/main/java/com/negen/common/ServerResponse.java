package com.negen.common;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 22:47 2020/3/5
 * @ Description：统一返回类
 * @ Modified By：
 * @Version: 1.0
 */
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
@Data
public class ServerResponse {
    Integer code;       //响应状态码
    String message;			//响应信息
    Object data;		//响应数据

    public static  ServerResponse getInstance() {
        return new ServerResponse();
    }

    public  ServerResponse code(Integer code){
        this.code = code;
        return (ServerResponse) this;
    }

    public  ServerResponse message(String message){
        this.message = message;
        return (ServerResponse) this;
    }

    public  ServerResponse data(Object data){
        this.data = data;
        return (ServerResponse) this;
    }

    public  ServerResponse responseEnum(ResponseEnum responseEnum){
        this.code = responseEnum.code;
        this.message = responseEnum.message;
        return (ServerResponse) this;
    }

    @Override
    public String toString() {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", this.code);
        resultJson.put("message", this.message);
        resultJson.put("data", this.data);
        return resultJson.toString();
    }

    //测试
    public static void main(String[] args) {
        System.out.println(ServerResponse.getInstance().responseEnum(ResponseEnum.LOGIN_SUCCESS));
    }
}

