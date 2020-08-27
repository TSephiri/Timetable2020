package com.example.app.Retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static Retrofit instance_Post;
    private static String url = "http://192.168.8.105:3000/";//API ip address


    public static Retrofit getInstance(){
        if(instance_Post == null)
            instance_Post = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return instance_Post;
    }


    private static Retrofit instance_get;

    public static Retrofit getInstance_get() {
        if(instance_get == null)
        {
            instance_get = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance_get;
    }
}

