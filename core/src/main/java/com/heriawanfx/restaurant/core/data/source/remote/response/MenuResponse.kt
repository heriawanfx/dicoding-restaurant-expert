package com.heriawanfx.restaurant.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class MenuResponse(

    @field:SerializedName("foods")
    val foods: List<FoodResponse> = emptyList(),

    @field:SerializedName("drinks")
    val drinks: List<DrinkResponse> = emptyList()
)

data class FoodResponse(

    @field:SerializedName("name")
    val name: String? = null
)

data class DrinkResponse(

    @field:SerializedName("name")
    val name: String? = null
)
