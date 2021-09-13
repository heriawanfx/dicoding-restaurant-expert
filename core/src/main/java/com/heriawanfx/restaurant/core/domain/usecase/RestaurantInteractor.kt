package com.heriawanfx.restaurant.core.domain.usecase

import com.heriawanfx.restaurant.core.data.Resource
import com.heriawanfx.restaurant.core.domain.model.Restaurant
import com.heriawanfx.restaurant.core.domain.repository.IRestaurantRepository
import kotlinx.coroutines.flow.Flow

class RestaurantInteractor(private val repository: IRestaurantRepository): RestaurantUseCase {
    override fun getAllRestaurants(query: String?): Flow<Resource<List<Restaurant>>> {
        return repository.getAllRestaurants(query)
    }

    override fun getRestaurantDetail(id: String): Flow<Resource<Restaurant>> {
        return repository.getRestaurantDetail(id)
    }

    override fun getFavoriteRestaurants(): Flow<List<Restaurant>> {
        return repository.getFavoriteRestaurants()
    }

    override fun setFavoriteRestaurant(restaurant: Restaurant) {
        repository.setFavoriteRestaurant(restaurant)
    }
}