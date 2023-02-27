package com.example.news_app.route.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import com.example.news_app.R
import com.example.news_app.databinding.ActivityMainBinding
import com.example.news_app.route.ui.categories.CategoriesFragment
import com.example.news_app.route.ui.categories.Category
import com.example.news_app.route.ui.categoryDetails.CategoryDetailsFragment
import com.example.news_app.route.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity() ,
    CategoriesFragment.OnCategoryClickListener{
    override fun oncategoryClick(category: Category) {
        showCategoryDetailsFragment(category)

    }
    lateinit var viewBinding: ActivityMainBinding
    val categoriesFragment= CategoriesFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding =DataBindingUtil.setContentView(this,R.layout.activity_main)
        val toggle =  ActionBarDrawerToggle(
            this,viewBinding.drawer,viewBinding.toolbar,
            R.string.navigation_drawer_open,
            R.string.naviggation_drawer_close


        )
        viewBinding.drawer.addDrawerListener(toggle)
        toggle.syncState()
        viewBinding.navView.setNavigationItemSelectedListener{ item->
            when(item.itemId){
                R.id.nav_categories->{
                    showCategoryFragment()
                }
                R.id.nav_settings->{
                    showSettingsFragment()

                }
            }
            viewBinding.drawer.closeDrawers()

            return@setNavigationItemSelectedListener true

        }

        categoriesFragment.onCategoryClickListener = this
        showCategoryFragment()

    }


         // ApiManager.getApis()
           // .getSources(apiKey)
            //.enqueue(object :Callback<SourcesResponse>{
              //  override fun onResponse(
                //    call: Call<SourcesResponse>,
                  //  response: Response<SourcesResponse>
                //) {
                  //  Log.e("response",response.body().toString())

                //}

                //override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    //Log.e("error",t.localizedMessage?:"")
                //}


            //})



    fun showCategoryFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,
                categoriesFragment)
            .commit()

    }

    fun showSettingsFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,
                SettingsFragment())
            .commit()

    }
    fun showCategoryDetailsFragment(category: Category){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,
                CategoryDetailsFragment.getInstance(category))
            .addToBackStack(null)
            .commit()

    }
}