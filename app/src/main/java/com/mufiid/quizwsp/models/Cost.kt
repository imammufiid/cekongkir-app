package com.mufiid.quizwsp.models

import com.google.gson.annotations.SerializedName

data class Cost(

	@field:SerializedName("service")
	val service: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("cost")
	val cost: List<CostItem?>? = null
)
