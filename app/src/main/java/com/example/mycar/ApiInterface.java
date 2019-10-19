package com.example.mycar;

import com.example.mycar.model.User;
import com.example.mycar.pojo.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("Login.php")
    Call<User> postLogin(
            @Field("userName") String userName,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("SignUp.php")
    Call<Result> postSignup(
            @Field("name")  String name,
            @Field("lastName")  String lastName,
            @Field("carModel")  String carModel,
            @Field("numberplate") String numberplate,
            @Field("userName") String userName,
            @Field("password") String password
    );

}
