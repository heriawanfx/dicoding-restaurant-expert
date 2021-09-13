package com.heriawanfx.restaurant.core.domain.usecase

import com.heriawanfx.restaurant.core.data.Resource
import com.heriawanfx.restaurant.core.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantUseCase {
    fun getAllRestaurants(query: String?): Flow<Resource<List<Restaurant>>>
    fun getRestaurantDetail(id: String): Flow<Resource<Restaurant>>
    fun getFavoriteRestaurants(): Flow<List<Restaurant>>
    fun setFavoriteRestaurant(restaurant: Restaurant)
}