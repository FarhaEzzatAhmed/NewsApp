package com.example.news_app.route.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news_app.route.api.ApiConstans
import com.example.news_app.route.api.ApiManager
import com.example.news_app.route.api.model.newsResponse.News
import com.example.news_app.route.api.model.newsResponse.NewsResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel :ViewModel(){
val showLoading =  MutableLiveData<Boolean>()
val showError = MutableLiveData<String>()
val newsList =MutableLiveData<List<News?>?>()
    fun getNews(sourceId:String) {
        showLoading.value = true
        ApiManager
            .getApis()
            .getNews(ApiConstans.apiKey,sourceId)
            .enqueue(object : Callback<NewsResponse> {
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                 showError.value=t.localizedMessage


                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.isSuccessful){

                        // we have news to show
                       newsList.value= response.body()?.articles

                        return
                    }
                    val errorResponse = Gson().fromJson(
                        response.errorBody()?.string(), NewsResponse::class.java)
                showError.value = errorResponse.message?:""

                }
            })
    }
}