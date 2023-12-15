package com.wsm.proj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Result {
    private Integer code;//响应码，1代表成功;0代表失败private
    private String msg;//响应信息描述字符串
    private Object data; //逐回的数据
    //培删改成功响应
    public static Result success () { return new Result(1," success", null);}//查询成功响应
        public static Result success(Object data) { return new Result(1,"success" ,data); }
        public static Result error(String msg) { return new Result(0,msg,null);}

    }
