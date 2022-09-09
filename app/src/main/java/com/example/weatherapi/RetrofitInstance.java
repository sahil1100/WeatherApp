package com.example.weatherapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private final static String BASEURL = "https://api.openweathermap.org/";

        public static Retrofit getRetrofit(){

            if(retrofit == null){
                    retrofit = new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
            }
            return retrofit;
        }
}
