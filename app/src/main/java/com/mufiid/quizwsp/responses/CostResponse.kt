package com.mufiid.quizwsp.responses

import com.google.gson.annotations.SerializedName
import com.mufiid.quizwsp.models.City
import com.mufiid.quizwsp.models.Cost
import com.mufiid.quizwsp.models.Status

data class CostResponse(

	@field:SerializedName("rajaongkir")
	val rajaongkir: RajaongkirCost? = null
)

data class RajaongkirCost(

	@field:SerializedName("query")
	val query: Query? = null,

    @field:SerializedName("status")
    val status: Status? = null,

    @field:SerializedName("origin_details")
	val originDetails: City? = null,

	@field:SerializedName("destination_details")
	val destinationDetails: City? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null

)

data class Query(

	@field:SerializedName("origin")
	val origin: String? = null,

	@field:SerializedName("destination")
	val destination: String? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

    @field:SerializedName("courier")
    val courier: String? = null
)

data class ResultsItem(

    @field:SerializedName("code")
	val code: String? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("costs")
    val costs: List<Cost?>? = null
)


