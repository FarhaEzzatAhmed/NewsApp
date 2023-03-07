package com.example.news_app.route.ui.itemDetails

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.news_app.R

class ItemDetailsActivity : AppCompatActivity() {
    var auther: TextView? = null
    var title:TextView? = null
    var time:TextView? = null
    var description:TextView? = null
    var image: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)
        auther = findViewById<TextView>(R.id.author)
        title = findViewById<TextView>(R.id.title)
        time = findViewById<TextView>(R.id.time)
        description = findViewById<TextView>(R.id.description)
        image= findViewById<ImageView>(R.id.item_image)

        val intent = intent
        //val intent = intent.extras

        auther?.setText(intent!!.getStringExtra("realName"))
        title?.setText(intent!!.getStringExtra("newsName"))
        time?.setText(intent!!.getStringExtra("newsBio"))
        description?.setText(intent!!.getStringExtra("description"))

        Glide.with(this)
            .load(intent!!.getStringExtra("newsImg"))
            .transition(
                DrawableTransitionOptions.withCrossFade(
                    DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                )
            )
            .into((image) as ImageView)

    }
}