package com.heriawanfx.restaurant.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class CustomerReviewResponse(

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("review")
    val review: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)
