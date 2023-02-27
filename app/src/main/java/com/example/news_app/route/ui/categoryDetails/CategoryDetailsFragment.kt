package com.example.news_app.route.ui.categoryDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.news_app.R
import com.example.news_app.databinding.FragmentDetailsCategoryBinding

import com.example.news_app.route.api.ApiConstans
import com.example.news_app.route.api.ApiManager
import com.example.news_app.route.api.model.SourcesResponse.Source
import com.example.news_app.route.api.model.SourcesResponse.SourcesResponse
import com.example.news_app.route.ui.categories.Category
import com.example.news_app.route.ui.news.NewsFragment
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDetailsFragment:Fragment() {
    val apiKey = "76dead7874004ebe922e3d9575a505bb"
    lateinit var viewBinding :FragmentDetailsCategoryBinding
    lateinit var viewModel: CategoryDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         viewBinding = FragmentDetailsCategoryBinding.inflate(
         inflater,container,false
     )
        return viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryDetailsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadNewsSources(category.id)
        subscribeToLiveData()

    }
    fun subscribeToLiveData(){
        viewModel.sourcesLiveData.observe(viewLifecycleOwner){
            bindSourcesInTabLayout(it)
        }

        viewModel.showLoadingLayout.observe(viewLifecycleOwner){show->
             if(show)
            showLoadingLayout()
            else hideLoading()
        }
        viewModel.showErrorLayout.observe(viewLifecycleOwner) {
            showErrorLayout(it)
        }

    }
    fun changeNewsFragment(source: Source){
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,NewsFragment.getInstance(source))
            .commit()

    }


    private fun showLoadingLayout(){
        viewBinding.loddingIndicator.isVisible = true
        viewBinding.errorLayout.isVisible = false
    }
    private fun hideLoading(){
        viewBinding.loddingIndicator.isVisible =false

    }
    private fun showErrorLayout(message: String?) {
        viewBinding.errorLayout.isVisible = true
        viewBinding.loddingIndicator.isVisible = false
        viewBinding.errorMessage.text=(message)



    }
    fun bindSourcesInTabLayout(sourcesList : List<Source?>?){
        sourcesList?.forEach { source->
            val tab = viewBinding.tabLayout.newTab()
            tab.text = source?.name
            tab.setTag(source)
            viewBinding.tabLayout.addTab(tab)
            val layoutparams = LinearLayout.LayoutParams(tab.view.layoutParams)
            layoutparams.marginEnd = 15
            layoutparams.marginStart=15
            tab.view.layoutParams =layoutparams

        }
        viewBinding.tabLayout
            .addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{


                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source
                    changeNewsFragment(source)
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                       val source = tab?.tag as Source
                    changeNewsFragment(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    
                }
            })
            viewBinding.tabLayout.getTabAt(0)?.select()

    }

    lateinit var  category: Category

    companion object{
        fun getInstance(category: Category):CategoryDetailsFragment {
            val fragment = CategoryDetailsFragment()
            fragment.category = category
            return fragment
        }

    }

}