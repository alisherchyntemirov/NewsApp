package com.example.newsapp.data.remote;

import com.example.newsapp.data.model.MainResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("top-headlines")
    Call<MainResponse> getTopNews(
            @Query("countri") String country ,
            @Query("api Key") String apiKey
    );
    @GET("top-healines")
    Call<MainResponse> getNewsByCategory(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );
    @GET("everything")
    Call<MainResponse> getNewsByKeyWord(
            @Query("q") String keyWord,
            @Query("apiKey") String apiKey
    );
}
