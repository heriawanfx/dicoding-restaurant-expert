package com.heriawanfx.restaurant.feature_favorite.di

import com.heriawanfx.restaurant.feature_favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}