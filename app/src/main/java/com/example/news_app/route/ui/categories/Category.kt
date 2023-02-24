package com.example.news_app.route.ui.categories

import com.example.news_app.R

data class Category(
    val id:String,
    val name :String,
    val imageId:Int,
    val backgroundcolorId:Int

){
    companion object{
    fun categoriesList():List<Category>{


        return listOf(
            Category(
                id ="sports",
                name ="Sports"
                , imageId = R.drawable.sports,
                  backgroundcolorId = R.color.red
            ),
            Category(
                id ="entertainment",
                name ="Entertainment"
                , imageId = R.drawable.politics,
                backgroundcolorId = R.color.darkblue
            ),
            Category(
                id ="health",
                name ="Health"
                , imageId = R.drawable.health,
                backgroundcolorId = R.color.pink
            ),
            Category(
                id ="bussiness",
                name ="Bussiness"
                , imageId = R.drawable.bussines,
                backgroundcolorId = R.color.brown
            ),
            Category(
                id ="technology",
                name ="Technology"
                , imageId = R.drawable.environment,
                backgroundcolorId = R.color.blue
            ),
            Category(
                id ="science",
                name ="Science"
                , imageId = R.drawable.science,
                backgroundcolorId = R.color.yellow
            )
        )

    }
}

}
