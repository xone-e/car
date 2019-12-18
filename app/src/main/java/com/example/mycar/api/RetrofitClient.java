package com.example.mycar.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String URL = "http://192.168.1.192/car/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;
    //public static final String login  = URL + "Login.php/";
    //public static String SignUp  = URL + "SignUp.php";

    private RetrofitClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }

        return mInstance;
    }

    public ApiInterface getApi() {
        return retrofit.create(ApiInterface.class);
    }
}
