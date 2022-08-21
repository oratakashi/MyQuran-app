package com.oratakashi.myquran.data.model.ayat

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oratakashi.myquran.domain.model.ayat.Ayat
import java.util.*

@Keep
@Entity
data class AyatEntity(
    val arabic: String,
    val translation: String,
    val nomor: Int,
    val latin: String,
    val idSurah: Int,
    @PrimaryKey(autoGenerate = false)
    val id: String
) {
    fun toAyat(): Ayat {
        return Ayat(
            arabic,
            translation,
            nomor,
            latin
        )
    }
}
