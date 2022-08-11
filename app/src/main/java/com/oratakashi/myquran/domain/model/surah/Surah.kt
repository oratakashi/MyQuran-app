package com.oratakashi.myquran.domain.model.surah

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Surah(
    val keterangan: String,
    val rukuk: String,
    val nama: String,
    val ayat: Int,
    val urut: String,
    val arti: String,
    val asma: String,
    val audio: String,
    val type: String,
    val nomor: String
): Parcelable