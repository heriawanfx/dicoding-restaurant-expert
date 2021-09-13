package com.heriawanfx.restaurant.core.data.source.remote.network

import com.heriawanfx.restaurant.core.data.source.remote.response.BaseDetailResponse
import com.heriawanfx.restaurant.core.data.source.remote.response.BaseListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("list")
    suspend fun getList(): BaseListResponse

    @GET("detail/{id}")
    suspend fun getDetail(@Path("id") id: String): BaseDetailResponse

    @GET("search")
    suspend fun search(@Query("q") query: String?): BaseListResponse
}
