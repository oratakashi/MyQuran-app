package com.oratakashi.myquran.data.model.surah

import com.google.gson.annotations.SerializedName
import com.oratakashi.myquran.domain.model.surah.Surah

data class SurahItem(

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("rukuk")
	val rukuk: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("ayat")
	val ayat: Int? = null,

	@field:SerializedName("urut")
	val urut: String? = null,

	@field:SerializedName("arti")
	val arti: String? = null,

	@field:SerializedName("asma")
	val asma: String? = null,

	@field:SerializedName("audio")
	val audio: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("nomor")
	val nomor: String? = null
) {
	fun toSurahEntity(): SurahEntity {
		return SurahEntity(
			keterangan.orEmpty(),
			rukuk.orEmpty(),
			nama.orEmpty(),
			ayat ?: 0,
			urut.orEmpty(),
			arti.orEmpty(),
			asma.orEmpty(),
			audio.orEmpty(),
			type.orEmpty(),
			nomor.orEmpty()
		)
	}
	fun toSurah(): Surah {
		return Surah(
			keterangan.orEmpty(),
			rukuk.orEmpty(),
			nama.orEmpty(),
			ayat ?: 0,
			urut.orEmpty(),
			arti.orEmpty(),
			asma.orEmpty(),
			audio.orEmpty(),
			type.orEmpty(),
			nomor.orEmpty()
		)
	}
}
