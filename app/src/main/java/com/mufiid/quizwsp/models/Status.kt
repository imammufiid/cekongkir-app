package com.mufiid.quizwsp.models

import com.google.gson.annotations.SerializedName

data class Status(
    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("description")
    val description: String? = null
)