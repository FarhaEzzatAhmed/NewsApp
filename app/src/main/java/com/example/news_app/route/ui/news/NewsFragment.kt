package com.example.news_app.route.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.news_app.databinding.FragmentNewsBinding
import com.example.news_app.route.api.ApiConstans
import com.example.news_app.route.api.ApiManager
import com.example.news_app.route.api.model.SourcesResponse.Source
import com.example.news_app.route.api.model.newsResponse.News
import com.example.news_app.route.api.model.newsResponse.NewsResponse
import com.example.news_app.route.ui.itemDetails.ItemDetailsActivity
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment :Fragment(){

    companion object{
        fun getInstance(source:Source):NewsFragment{
        val newNewsFragment = NewsFragment()
            newNewsFragment .source = source
            return newNewsFragment

        }
    }
    lateinit var  source :Source
    lateinit var viewBinding: FragmentNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentNewsBinding.inflate(inflater,
        container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getNews()

    }
    val newsAdapter = NewsAdapter(null)
    private fun initRecyclerView() {
        viewBinding.newsRecycler.adapter = newsAdapter
        newsAdapter.onClickListener=object :NewsAdapter.OnclickListener{
            override fun onNewsClick(news: News) {
                val intent = Intent(requireContext(),ItemDetailsActivity::class.java).apply {
                    putExtra("realName",news.author)
                    putExtra("heroName",news.title)
                    putExtra("heroBio",news.publishedAt)
                    putExtra("description",news.content)
                    putExtra("heroImg",news.urlToImage)

                }
                startActivity(intent)
            }

        }
    }

    private fun getNews() {
        showLoadingLayout()
        ApiManager
            .getApis()
            .getNews(ApiConstans.apiKey,source.id?:"")
            .enqueue(object :Callback<NewsResponse>{
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                   showErrorLayout(t.localizedMessage)


                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.isSuccessful){
                        // we have news to show
                        bindNewsList(response.body()?.articles)
                        return
                    }
                      val errorResponse = Gson().fromJson(
                         response.errorBody()?.string(), NewsResponse::class.java)
                        showErrorLayout(errorResponse.message)

                }
            })
    }

    private fun bindNewsList(articles: List<News?>?) {
        // show news in recycler view
        viewBinding.loddingIndicator.isVisible = false
        viewBinding.errorLayout.isVisible = false
       newsAdapter.changeData(articles)


    }

    private fun showLoadingLayout(){
        viewBinding.loddingIndicator.isVisible = true
        viewBinding.errorLayout.isVisible = false
    }

    private fun showErrorLayout(message: String?) {
        viewBinding.errorLayout.isVisible = true
        viewBinding.loddingIndicator.isVisible = false
        viewBinding.errorMessage.text=(message)



    }



}