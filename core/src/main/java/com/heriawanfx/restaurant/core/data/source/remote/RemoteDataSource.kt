package com.heriawanfx.restaurant.core.data.source.remote

import android.util.Log
import com.heriawanfx.restaurant.core.data.source.remote.network.ApiResponse
import com.heriawanfx.restaurant.core.data.source.remote.network.ApiService
import com.heriawanfx.restaurant.core.data.source.remote.response.RestaurantResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun fetchAllRestaurants(query: String?): Flow<ApiResponse<List<RestaurantResponse>>> {
        return flow {
            try {
                var response = apiService.getList()

                if(!query.isNullOrEmpty()){
                    response = apiService.search(query)
                }

                val data = response.restaurants
                if (data.isNotEmpty()){
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("fetchAllRestaurants", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


    suspend fun fetchRestaurant(id: String): Flow<ApiResponse<RestaurantResponse>> {
        return flow {
            try {
                val response = apiService.getDetail(id)
                val data = response.restaurant
                if (data != null){
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("fetchRestaurant", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}

