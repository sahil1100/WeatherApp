package com.example.weatherapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("data/2.5/weather")
    Call<model> get_weather(
            @Query("q") String q, @Query("appid") String appid);

}
