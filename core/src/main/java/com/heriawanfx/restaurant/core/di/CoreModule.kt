package com.heriawanfx.restaurant.core.di

import androidx.room.Room
import com.heriawanfx.restaurant.core.data.RestaurantRepository
import com.heriawanfx.restaurant.core.data.source.local.LocalDataSource
import com.heriawanfx.restaurant.core.data.source.local.room.RestaurantDatabase
import com.heriawanfx.restaurant.core.data.source.remote.RemoteDataSource
import com.heriawanfx.restaurant.core.data.source.remote.network.ApiService
import com.heriawanfx.restaurant.core.domain.repository.IRestaurantRepository
import com.heriawanfx.restaurant.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("heriawanfx".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            RestaurantDatabase::class.java, "Restaurant.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "restaurant-api.dicoding.dev"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/hWR325mqh8kIxVsVJnfJGAsD+T5rjcqZU2UsBlVf4Qw=")
            .add(hostname, "sha256/cXjPgKdVe6iojP8s0YQJ3rtmDFHTnYZxcYvmYGFiYME=")
            .add(hostname, "sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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
    factory { AppExecutors() }
    single<IRestaurantRepository> { RestaurantRepository(get(), get(), get()) }
}