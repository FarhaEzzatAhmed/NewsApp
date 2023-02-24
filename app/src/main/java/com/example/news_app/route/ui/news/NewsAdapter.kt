package com.example.news_app.route.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_app.R
import com.example.news_app.databinding.ItemNewsBinding
import com.example.news_app.route.api.model.newsResponse.News
import com.example.news_app.route.ui.categories.Category

class NewsAdapter(var items:List<News?>?) :RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var onClickListener : OnclickListener?=null
    class ViewHolder(val viewBinding: ItemNewsBinding)
        :RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return  ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)!!
        holder.viewBinding.author.text = item?.author
        holder.viewBinding.title.text = item?.title
        holder.viewBinding.time.text = item?.publishedAt
        Glide.with(holder.itemView)
            .load(item?.urlToImage)
            .placeholder(R.drawable.ic_image)
            .into(holder.viewBinding.image)
        holder.itemView.setOnClickListener{
            onClickListener?.onNewsClick(item!!)
        }


    }

    override fun getItemCount(): Int = items?.size?:0
    fun changeData(articles: List<News?>?) {
        items = articles
        notifyDataSetChanged()

    }
    interface  OnclickListener {
        fun onNewsClick(news:News)
    }

}