package com.oratakashi.myquran.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oratakashi.myquran.data.model.ayat.AyatEntity
import com.oratakashi.myquran.data.model.surah.SurahEntity
import io.reactivex.Single

@Dao
interface SurahDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<SurahEntity>): Single<List<Long>>

    @Query("Select * from surahentity")
    fun getAll(): Single<List<SurahEntity>>
}