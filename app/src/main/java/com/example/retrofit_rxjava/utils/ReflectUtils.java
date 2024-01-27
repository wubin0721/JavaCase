package com.example.retrofit_rxjava.utils;

import com.example.retrofit_rxjava.Network.response.IResponse;

//反射工具类
public class ReflectUtils{

    public static <T> Class<?> analysisClassInfo(IResponse<T> tiResponse) {
        return tiResponse.getClass();
    }
}
