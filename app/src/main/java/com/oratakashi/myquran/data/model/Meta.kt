package com.oratakashi.myquran.data.model

import com.google.gson.annotations.SerializedName

data class Meta(

	@field:SerializedName("totalData")
	val totalData: Int? = null,

	@field:SerializedName("totalPage")
	val totalPage: Int? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null
)
