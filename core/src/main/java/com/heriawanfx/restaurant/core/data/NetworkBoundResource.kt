package com.heriawanfx.restaurant.core.data

import com.heriawanfx.restaurant.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<DomainType, ResponseType> {

    private var result: Flow<Resource<DomainType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        val apiResponse = createCall().first()
        if (shouldFetch(dbSource)) {
            //emit(Resource.Loading())
            when (apiResponse) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(
                        Resource.Error<DomainType>(apiResponse.errorMessage)
                    )
                }
            }
        } else {
            emitAll(loadFromDB().map {
                Resource.Success(it)
            })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<DomainType>

    protected abstract fun shouldFetch(data: DomainType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<ResponseType>>

    protected abstract suspend fun saveCallResult(data: ResponseType)

    fun asFlow(): Flow<Resource<DomainType>> = result
}