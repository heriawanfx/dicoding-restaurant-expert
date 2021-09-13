package com.heriawanfx.restaurant.core.di

import androidx.room.Room
import com.heriawanfx.restaurant.core.data.RestaurantRepository
import com.heriawanfx.restaurant.core.data.source.local.LocalDataSource
import com.heriawanfx.restaurant.core.data.source.local.room.RestaurantDatabase
import com.heriawanfx.restaurant.core.data.source.remote.RemoteDataSource
import com.heriawanfx.restaurant.core.data.source.remote.network.ApiService
import com.heriawanfx.restaurant.core.domain.repository.IRestaurantRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<RestaurantDatabase>().restaurantDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            RestaurantDatabase::class.java, "Restaurant.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://restaurant-api.dicoding.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IRestaurantRepository> { RestaurantRepository(get(), get()) }
}