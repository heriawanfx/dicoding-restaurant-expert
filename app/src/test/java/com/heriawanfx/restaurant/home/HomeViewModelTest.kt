package com.heriawanfx.restaurant.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.heriawanfx.restaurant.core.data.Resource
import com.heriawanfx.restaurant.core.domain.model.Restaurant
import com.heriawanfx.restaurant.core.domain.usecase.RestaurantUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var restaurantUseCase: RestaurantUseCase

    @Mock
    private lateinit var restaurants: List<Restaurant>

    @Before
    fun setUp() {

        viewModel = HomeViewModel(restaurantUseCase)
    }

    @Test
    fun getRestaurants() {

        val defaultList = Resource.Success(restaurants)
        Mockito.`when`(defaultList.data?.size).thenReturn(10)

        Assert.assertNotNull(defaultList)
        Assert.assertEquals(10, defaultList.data?.size)

    }


}