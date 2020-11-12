package com.mufiid.quizwsp.models

import com.google.gson.annotations.SerializedName

data class City(

	@field:SerializedName("city_id")
	val cityId: String? = null,

	@field:SerializedName("province_id")
	val provinceId: String? = null,

	@field:SerializedName("province")
	val province: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("city_name")
	val cityName: String? = null,

	@field:SerializedName("postal_code")
	val postalCode: String? = null
)
