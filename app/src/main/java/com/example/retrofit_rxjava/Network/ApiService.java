package com.example.retrofit_rxjava.Network;

import com.example.retrofit_rxjava.Bean.User;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    //Get请求
    @GET("fc/test")                 //路径
    Call<String> Get(@Query("test")String str);


    //Post请求
    @POST("fc/test")                 //路径
    @FormUrlEncoded
    Call<ResponseData<String>> Post(@Body User user);


    //RxPost请求
    @POST("fc/test")                 //路径
    @FormUrlEncoded
    Observable<ResponseData<String>> RxPost(@Field("test")String str);
}
