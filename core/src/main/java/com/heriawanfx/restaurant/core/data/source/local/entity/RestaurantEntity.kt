package com.heriawanfx.restaurant.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.heriawanfx.restaurant.core.domain.model.Restaurant

@Entity(tableName = "tbl_restaurant")
data class RestaurantEntity(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    var id: String = "0",
    var name: String? = null,
    var address: String? = null,
    var pictureId: String? = null,
    var city: String? = null,
    var rating: Double? = null,
    var description: String? = null,
    var isFavorite: Boolean = false
) {
    fun asDomain(): Restaurant {
        return Restaurant(
            id = id,
            name = name,
            address = address,
            pictureId = pictureId,
            city = city,
            rating = rating,
            description = description,
            isFavorite = isFavorite
        )

    }
}
