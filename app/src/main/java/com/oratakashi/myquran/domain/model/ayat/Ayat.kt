package com.oratakashi.myquran.domain.model.ayat

data class Ayat(
	var arabic: String,
	val translation: String,
	val nomor: Int,
	val latin: String
)

