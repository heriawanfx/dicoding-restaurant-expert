package com.heriawanfx.restaurant.di

import com.heriawanfx.restaurant.core.domain.usecase.RestaurantInteractor
import com.heriawanfx.restaurant.core.domain.usecase.RestaurantUseCase
import com.heriawanfx.restaurant.detail.DetailViewModel
import com.heriawanfx.restaurant.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<RestaurantUseCase> { RestaurantInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}