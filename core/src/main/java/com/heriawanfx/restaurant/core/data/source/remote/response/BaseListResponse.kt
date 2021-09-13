package com.heriawanfx.restaurant.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BaseListResponse(

    @field:SerializedName("restaurants")
    val restaurants: List<RestaurantResponse> = emptyList(),

    @field:SerializedName("error")
    val error: Boolean = false,

    @field:SerializedName("message")
    val message: String? = null
)