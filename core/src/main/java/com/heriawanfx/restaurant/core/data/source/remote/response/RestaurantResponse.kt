package com.heriawanfx.restaurant.core.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.heriawanfx.restaurant.core.data.source.local.entity.RestaurantEntity

data class RestaurantResponse(

    @field:SerializedName("id")
    val id: String = "",

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("pictureId")
    val pictureId: String? = null,

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("rating")
    val rating: Double? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("menus")
    val menus: MenuResponse? = null,

    @field:SerializedName("categories")
    val categories: List<CategoryResponse> = emptyList(),

    @field:SerializedName("customerReviews")
    val customerReviews: List<CustomerReviewResponse> = emptyList(),

    ) {
    fun asEntity(): RestaurantEntity {
        return RestaurantEntity(
            id = id,
            name = name,
            address = address,
            pictureId = pictureId,
            city = city,
            rating = rating,
            description = description,
        )
    }

}


