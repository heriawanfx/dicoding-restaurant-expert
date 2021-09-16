package com.heriawanfx.restaurant.feature_favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.heriawanfx.restaurant.core.domain.model.Restaurant
import com.heriawanfx.restaurant.core.domain.usecase.RestaurantUseCase

class FavoriteViewModel(private val restaurantUseCase: RestaurantUseCase) : ViewModel() {
    val restaurants: LiveData<List<Restaurant>>
        get() = restaurantUseCase.getFavoriteRestaurants().asLiveData()

}