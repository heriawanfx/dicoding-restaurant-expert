package com.heriawanfx.restaurant.core.domain.repository

import com.heriawanfx.restaurant.core.data.Resource
import com.heriawanfx.restaurant.core.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface IRestaurantRepository {
    fun getAllRestaurants(query: String?): Flow<Resource<List<Restaurant>>>
    fun getRestaurantDetail(id: String): Flow<Resource<Restaurant>>
    fun getFavoriteRestaurants(): Flow<List<Restaurant>>
    fun setFavoriteRestaurant(restaurant: Restaurant)

}