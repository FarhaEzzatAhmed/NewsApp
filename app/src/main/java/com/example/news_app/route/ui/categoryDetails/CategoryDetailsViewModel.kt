package com.example.news_app.route.ui.categoryDetails

import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news_app.route.api.ApiConstans
import com.example.news_app.route.api.ApiManager
import com.example.news_app.route.api.model.SourcesResponse.Source
import com.example.news_app.route.api.model.SourcesResponse.SourcesResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDetailsViewModel:ViewModel() {
 // Mutable a2dr a8erha
    val sourcesLiveData =MutableLiveData<List<Source?>?>()
    val showLoadingLayout= MutableLiveData<Boolean>()
    val showErrorLayout= MutableLiveData<String>()



     fun loadNewsSources(categoryId: String) {
       showLoadingLayout.value= true


        ApiManager
            .getApis()
            .getSources(ApiConstans.apiKey,categoryId)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {

                     showLoadingLayout.value= false
                    //viewBinding.loddingIndicator.visibility = View.GONE
                    // or
                    //viewBinding.loddingIndicator.isVisible = false
                    if (response.isSuccessful){
                        //we have data
                        sourcesLiveData.value = response.body()?.sources

                      // // bindSourcesInTabLayout(response.body()?.sources)
                        // response.body().sources
                    }else
                    {
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), SourcesResponse::class.java)
                       showErrorLayout.value = errorResponse.message?: ""


                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    showLoadingLayout.value=false
                    showErrorLayout.value=t.localizedMessage?:""

                }
            })

    }
    fun loadSources(categoryId:String){

    }


}