package com.heriawanfx.restaurant.core.data.source.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.heriawanfx.restaurant.core.data.source.remote.response.CategoryResponse

class Converters {

    @TypeConverter
    fun categoriesToJson(value: List<CategoryResponse>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToCategories(value: String): List<CategoryResponse> {
        return Gson().fromJson(value, Array<CategoryResponse>::class.java).toList()
    }
}