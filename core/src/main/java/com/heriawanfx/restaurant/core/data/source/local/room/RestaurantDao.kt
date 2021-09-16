package com.heriawanfx.restaurant.core.data.source.local.room

import androidx.room.*
import com.heriawanfx.restaurant.core.data.source.local.entity.RestaurantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM tbl_restaurant WHERE name LIKE '%'|| :query || '%' ORDER BY name")
    fun readAllRestaurants(query: String? = null): Flow<List<RestaurantEntity>>

    @Query("SELECT * FROM tbl_restaurant where id = :id")
    fun readRestaurant(id: String): Flow<RestaurantEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(restaurants: List<RestaurantEntity>)

    @Update
    fun updateRestaurant(restaurant: RestaurantEntity)

    @Query("SELECT * FROM tbl_restaurant where isFavorite = 1")
    fun readFavoriteRestaurant(): Flow<List<RestaurantEntity>>

}
