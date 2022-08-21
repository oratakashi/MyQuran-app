package com.oratakashi.myquran.data.model.ayat

import com.google.gson.annotations.SerializedName
import com.oratakashi.myquran.domain.model.ayat.Ayat

data class AyatItem(

	@field:SerializedName("ar")
	val arabic: String? = null,

	@field:SerializedName("id")
	val translation: String? = null,

	@field:SerializedName("nomor")
	val nomor: String? = null,

	@field:SerializedName("tr")
	val latin: String? = null
) {
	fun toAyat(): Ayat {
		return Ayat(
			arabic.orEmpty(),
			translation.orEmpty(),
			nomor.orEmpty(),
			latin.orEmpty()
		)
	}

	fun toAyatEntity(idSurah: Int): AyatEntity {
		return AyatEntity(
			arabic.orEmpty(),
			translation.orEmpty(),
			nomor.orEmpty(),
			latin.orEmpty(),
			idSurah
		)
	}
}
