package com.heriawanfx.restaurant.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BaseDetailResponse(

    @field:SerializedName("restaurant")
    val restaurant: RestaurantResponse? = null,

    @field:SerializedName("error")
    val error: Boolean = false,

    @field:SerializedName("message")
    val message: String? = null
)