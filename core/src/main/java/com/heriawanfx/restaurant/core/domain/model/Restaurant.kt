package com.heriawanfx.restaurant.core.domain.model

import com.heriawanfx.restaurant.core.data.source.local.entity.RestaurantEntity
import com.heriawanfx.restaurant.core.data.source.remote.response.CategoryResponse
import com.heriawanfx.restaurant.core.data.source.remote.response.CustomerReviewResponse
import com.heriawanfx.restaurant.core.data.source.remote.response.MenuResponse

data class Restaurant(
    val id: String,
    val name: String? = null,
    val address: String? = null,
    val pictureId: String? = null,
    val city: String? = null,
    val rating: Double? = null,
    val description: String? = null,
    val menus: MenuResponse? = null,
    val categories: List<CategoryResponse> = listOf(),
    val customerReviews: List<CustomerReviewResponse> = listOf(),
    val isFavorite: Boolean = false
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
            isFavorite = isFavorite
        )

    }

    fun getPictureUrl(): String {
        return "https://restaurant-api.dicoding.dev/images/medium/$pictureId"
    }
}
