package com.mufiid.quizwsp.responses

import com.google.gson.annotations.SerializedName
import com.mufiid.quizwsp.models.City
import com.mufiid.quizwsp.models.Status

data class CityResponse(

	@field:SerializedName("rajaongkir")
	val rajaongkir: RajaongkirCity? = null
)

data class RajaongkirCity(

	@field:SerializedName("query")
	val query: List<Any?>? = null,

	@field:SerializedName("status")
	val status: Status? = null,

	@field:SerializedName("results")
	val results: List<City>? = null

)
