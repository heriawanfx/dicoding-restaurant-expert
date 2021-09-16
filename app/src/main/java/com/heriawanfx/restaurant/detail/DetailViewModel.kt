package com.heriawanfx.restaurant.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.heriawanfx.restaurant.core.domain.model.Restaurant
import com.heriawanfx.restaurant.core.domain.usecase.RestaurantUseCase

class DetailViewModel(private val restaurantUseCase: RestaurantUseCase): ViewModel() {
    private val _restaurantId = MutableLiveData("")
    fun setRestaurantId(value: String){
        _restaurantId.value = value
    }

    val restaurantDetail = Transformations.switchMap(_restaurantId){
        restaurantUseCase.getRestaurantDetail(it).asLiveData()
    }

    fun toggleFavorite(item: Restaurant){
        restaurantUseCase.setFavoriteRestaurant(item)
    }
}