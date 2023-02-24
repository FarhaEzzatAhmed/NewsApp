package com.example.news_app.route.api.model.SourcesResponse

import com.google.gson.annotations.SerializedName

 class Source(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)