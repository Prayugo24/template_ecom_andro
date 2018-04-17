package com.indokoding.apiholder;


import com.indokoding.model.DataRegister;
import com.indokoding.model.FeedUser;
import com.indokoding.model.LoginUser;

import com.indokoding.model.FeedLogin;
import com.indokoding.model.registerUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Admin on 14/02/2018.
 */

public interface baseApiService {

    @POST("login")
    Call<FeedLogin> loginRequest(@Body LoginUser login);


    @GET("profile")
    Call<FeedUser> getAllProfile(@Header("Content-Type") String contenType,
                                 @Header("Accept") String Accept,
                                 @Header("Authorization") String authToken);

    @POST("register")
    Call<DataRegister> registerReques(@Body registerUser registerUser);


}
