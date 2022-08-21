package com.oratakashi.myquran.data.model.surah

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oratakashi.myquran.domain.model.surah.Surah

@Keep
@Entity
data class SurahEntity(
    val keterangan: String,
    val rukuk: String,
    val nama: String,
    val ayat: Int,
    val urut: String,
    val arti: String,
    val asma: String,
    val audio: String,
    val type: String,
    @PrimaryKey(autoGenerate = false)
    val id: String
) {
    fun toSurah(): Surah {
        return Surah(
            keterangan,
            rukuk,
            nama,
            ayat,
            urut,
            arti,
            asma,
            audio,
            type,
            id
        )
    }
}
