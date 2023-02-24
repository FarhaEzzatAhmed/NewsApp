package com.example.news_app.route.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.news_app.databinding.FragmentCategoriesBinding

class CategoriesFragment:Fragment() {

    lateinit var  viewBinding :FragmentCategoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCategoriesBinding.inflate(inflater,container,false)
        return viewBinding.root
    }
     val adapter = CategoriesRecyclerAdapter(Category.categoriesList())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.categoriesRecycler.adapter = adapter
        adapter.onItemClickListener = object :CategoriesRecyclerAdapter.OnItemClickListener{
            override fun onItemClick(pos: Int, item: Category) {
                onCategoryClickListener?.oncategoryClick(item)

            }
        }
    }
    var onCategoryClickListener:OnCategoryClickListener?=null
    interface  OnCategoryClickListener{
        fun oncategoryClick(category: Category)
    }
}