package com.heriawanfx.restaurant.home

import androidx.lifecycle.*
import com.heriawanfx.restaurant.core.data.Resource
import com.heriawanfx.restaurant.core.domain.model.Restaurant
import com.heriawanfx.restaurant.core.domain.usecase.RestaurantUseCase

class HomeViewModel(private val restaurantUseCase: RestaurantUseCase) : ViewModel() {
    private val _query = MutableLiveData("")
    fun getQuery() : LiveData<String> = _query
    fun setQuery(value: String) {
        _query.value = value
    }

    val restaurants: LiveData<Resource<List<Restaurant>>> = Transformations.switchMap(_query){
        restaurantUseCase.getAllRestaurants(it).asLiveData()
    }
}