package com.example.retrofit_rxjava.Network.response;

public interface IResponse <T>{
    T getData();
    String getMsg();
    String getCode();

    boolean isSuccess();
}
