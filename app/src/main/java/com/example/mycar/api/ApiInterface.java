package com.example.mycar.api;

import com.example.mycar.model.User;
import com.example.mycar.pojo.Result;
import com.example.mycar.pojo.Services;
import com.example.mycar.pojo.Users;

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
            @Field("name") String name,
            @Field("lastName") String lastName,
            @Field("machineName") String machineName,
            @Field("numberplate") String numberplate,
            @Field("userName") String userName,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("getUser.php")
    Call<Users> getUser(
            @Field("userId") int userId
    );

    @FormUrlEncoded
    @POST("insertService.php")
    Call<Result> addService(
            @Field("name") String name,
            @Field("userId") int userId,
            @Field("serviceKilometer") String serviceKilometer,
            @Field("estimateKilometer") String estimateKilometer
    );

    @FormUrlEncoded
    @POST("getServices.php")
    Call<Services> getAllServices(
            @Field("userId") int userId,
            @Field("flag") int flag
    );

    @FormUrlEncoded
    @POST("getLastService.php")
    Call<Services> getLastService(
            @Field("userId") int userId,
            @Field("flag") int flag
    );
}
