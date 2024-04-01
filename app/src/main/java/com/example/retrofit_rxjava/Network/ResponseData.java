package com.example.retrofit_rxjava.Network;

import com.example.retrofit_rxjava.Network.response.IResponse;

public class ResponseData <T> implements IResponse<T> {

    //返回值
    private String code;

    //应答体数据
    private T data;

    //错误信息
    private String msg;


    public String getCode() {
        return code;
    }

    @Override
    public boolean isSuccess() {
        return "200".equals(code);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }




}
