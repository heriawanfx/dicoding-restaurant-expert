package com.heriawanfx.restaurant

import android.app.Application
import com.heriawanfx.restaurant.core.di.databaseModule
import com.heriawanfx.restaurant.core.di.networkModule
import com.heriawanfx.restaurant.core.di.repositoryModule
import com.heriawanfx.restaurant.di.useCaseModule
import com.heriawanfx.restaurant.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}