package com.oratakashi.myquran.data.model.ayat

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
data class AyatRemoteKeyEntity(
    @PrimaryKey val id: String,
    val currentPage: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
