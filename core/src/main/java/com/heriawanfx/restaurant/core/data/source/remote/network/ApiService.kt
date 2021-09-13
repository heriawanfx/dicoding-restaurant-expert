package com.heriawanfx.restaurant.core.data.source.remote.network

import com.heriawanfx.restaurant.core.data.source.remote.response.BaseDetailResponse
import com.heriawanfx.restaurant.core.data.source.remote.response.BaseListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("list")
    suspend fun getList(): BaseListResponse

    @GET("detail/{id}")
    suspend fun getDetail(@Query("id") id: String): BaseDetailResponse

    @GET("search?q={query}")
    suspend fun search(@Query("query") query: String?): BaseListResponse
}
