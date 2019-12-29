package com.liveinbits.androidpaginationfromserver;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String url="http://192.168.0.122/displayimages/";
    private static  Retrofit instance=null;

    public static Retrofit getInstance(){
        if(instance==null){
            instance=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return instance;
    }
}
