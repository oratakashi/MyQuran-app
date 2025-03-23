package com.oratakashi.myquran.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oratakashi.myquran.data.model.ayat.AyatEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface AyatDao {
    @Query("Select * from ayatentity")
    fun getAll(): Single<List<AyatEntity>>

    @Query("Select * from ayatentity where idSurah = :idSurah")
    fun getAll(idSurah: Int): Single<List<AyatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<AyatEntity>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: AyatEntity): Single<Long>
}