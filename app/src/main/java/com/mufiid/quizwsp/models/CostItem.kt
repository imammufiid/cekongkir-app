package com.mufiid.quizwsp.models

import com.google.gson.annotations.SerializedName

data class CostItem(

    @field:SerializedName("etd")
    val etd: String? = null,

    @field:SerializedName("value")
    val value: Int? = null,

    @field:SerializedName("note")
    val note: String? = null
)