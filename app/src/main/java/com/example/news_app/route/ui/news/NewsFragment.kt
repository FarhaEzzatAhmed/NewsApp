package com.example.news_app.route.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

    }


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
        viewModel.getNews(source.id?:"")
        subscribeToLiveData()

    }

       fun subscribeToLiveData() {
           viewModel.newsList.observe(viewLifecycleOwner){
               bindNewsList(it)
           }
           viewModel.showError.observe(viewLifecycleOwner){
               showErrorLayout(it)
           }
           viewModel.showLoading.observe(viewLifecycleOwner){
               if (it)showLoadingLayout()
               else hideLoadingLayout()
           }

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
    private fun hideLoadingLayout(){
        viewBinding.loddingIndicator.isVisible = false
    }

    private fun showErrorLayout(message: String?) {
        viewBinding.errorLayout.isVisible = true
        viewBinding.loddingIndicator.isVisible = false
        viewBinding.errorMessage.text=(message)



    }



}