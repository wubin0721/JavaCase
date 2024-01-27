package com.example.retrofit_rxjava.Network;

import com.example.retrofit_rxjava.Network.response.IResponse;

public class ResponseData <T> implements IResponse<T> {
    private String code;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private String msg;
    private T data;
}
