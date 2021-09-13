package com.heriawanfx.restaurant.core.data.source.local

import com.heriawanfx.restaurant.core.data.source.local.entity.RestaurantEntity
import com.heriawanfx.restaurant.core.data.source.local.room.RestaurantDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val restaurantDao: RestaurantDao) {

    fun readAllRestaurants(query: String? = null): Flow<List<RestaurantEntity>> {
        return restaurantDao.readAllRestaurants(query)
    }

    fun readRestaurant(id: String): Flow<RestaurantEntity> {
        return restaurantDao.readRestaurant(id)
    }

    fun readFavoriteRestaurants(): Flow<List<RestaurantEntity>> {
        return restaurantDao.readFavoriteRestaurant()
    }

    suspend fun insertRestaurant(restaurants: List<RestaurantEntity>) {
        restaurantDao.insertRestaurants(restaurants)
    }

    fun updateRestaurant(restaurant: RestaurantEntity) {
        restaurant.isFavorite = !restaurant.isFavorite
        restaurantDao.updateRestaurant(restaurant)
    }
}