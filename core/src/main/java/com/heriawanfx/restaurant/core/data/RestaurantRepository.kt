package com.heriawanfx.restaurant.core.data

import com.heriawanfx.restaurant.core.data.source.local.LocalDataSource
import com.heriawanfx.restaurant.core.data.source.remote.RemoteDataSource
import com.heriawanfx.restaurant.core.data.source.remote.network.ApiResponse
import com.heriawanfx.restaurant.core.data.source.remote.response.RestaurantResponse
import com.heriawanfx.restaurant.core.domain.model.Restaurant
import com.heriawanfx.restaurant.core.domain.repository.IRestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RestaurantRepository(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource): IRestaurantRepository {
    override fun getAllRestaurants(query: String?): Flow<Resource<List<Restaurant>>> {
        return object : NetworkBoundResource<List<Restaurant>, List<RestaurantResponse>>() {
            override fun loadFromDB(): Flow<List<Restaurant>> {
                return localDataSource.readAllRestaurants(query).map { list ->
                    list.map { it.asDomain() }
                }
            }

            override fun shouldFetch(data: List<Restaurant>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<RestaurantResponse>>> {
                return remoteDataSource.fetchAllRestaurants(query)
            }

            override suspend fun saveCallResult(data: List<RestaurantResponse>) {
                val entities = data.map { it.asEntity() }
                localDataSource.insertRestaurant(entities)
            }

        }.asFlow()
    }

    override fun getRestaurantDetail(id: String): Flow<Resource<Restaurant>> {
        return object : NetworkBoundResource<Restaurant, RestaurantResponse>() {
            override fun loadFromDB(): Flow<Restaurant> {
                return localDataSource.readRestaurant(id).map {
                    it.asDomain()
                }
            }

            override fun shouldFetch(data: Restaurant?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<RestaurantResponse>> {
                return remoteDataSource.fetchRestaurant(id)
            }

            override suspend fun saveCallResult(data: RestaurantResponse) {
                val entity = data.asEntity()
                localDataSource.insertRestaurant(listOf(entity))
            }

        }.asFlow()
    }

    override fun getFavoriteRestaurants(): Flow<List<Restaurant>> {
        return localDataSource.readFavoriteRestaurants().map { list ->
            list.map { it.asDomain() }
        }
    }

    override fun setFavoriteRestaurant(restaurant: Restaurant) {
        val entity = restaurant.asEntity()
        localDataSource.updateRestaurant(entity)
    }
}