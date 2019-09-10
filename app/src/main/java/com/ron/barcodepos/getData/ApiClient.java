package com.ron.barcodepos.getData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
//    public static final String BASE_URL = "https://192.168.43.173/RJSQ-POSNEW/retrofit/GET/";
    public static final String BASE_URL = "http://192.168.100.171/retrofit/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient(){
        if(retrofit==null){

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return  retrofit;
    }
}