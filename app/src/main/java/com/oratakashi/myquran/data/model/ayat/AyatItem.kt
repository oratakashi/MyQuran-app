package com.oratakashi.myquran.data.model.ayat

import com.google.gson.annotations.SerializedName
import com.oratakashi.myquran.domain.model.ayat.Ayat

data class AyatItem(

	@field:SerializedName("arabic")
	val arabic: String? = null,

	@field:SerializedName("translation")
	val translation: String? = null,

	@field:SerializedName("nomor")
	val nomor: Int? = null,

	@field:SerializedName("latin")
	val latin: String? = null,

	@field:SerializedName("idSurah")
	val idSurah: Int? = null,

	@field:SerializedName("id")
	val id: String? = null
) {
	fun toAyat(): Ayat {
		return Ayat(
			arabic.orEmpty(),
			translation.orEmpty(),
			nomor ?: 0,
			latin.orEmpty()
		)
	}

	fun toAyatEntity(): AyatEntity {
		return AyatEntity(
			arabic.orEmpty(),
			translation.orEmpty(),
			nomor ?: 0,
			latin.orEmpty(),
			idSurah ?: 0,
			id.orEmpty()
		)
	}
}
