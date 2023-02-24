package com.example.news_app.route.api

import com.example.news_app.route.api.model.SourcesResponse.SourcesResponse
import com.example.news_app.route.api.model.newsResponse.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("/v2/top-headlines/sources")
    fun getSources(@Query("apiKey") apiKey:String,
    @Query("category")category:String):retrofit2.Call<SourcesResponse>
    @GET("v2/everything")
    fun getNews(@Query("apiKey") apiKey:String
                ,@Query("sources")sources:String): Call<NewsResponse>
}