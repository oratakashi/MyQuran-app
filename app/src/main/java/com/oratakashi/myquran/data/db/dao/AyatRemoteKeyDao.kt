package com.oratakashi.myquran.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oratakashi.myquran.data.model.ayat.AyatRemoteKeyEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface AyatRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteKeys: List<AyatRemoteKeyEntity>)

    @Query("SELECT * FROM ayatremotekeyentity WHERE id = :id")
    fun getRemoteKeyByAyatId(id: String): AyatRemoteKeyEntity?

    @Query("DELETE FROM ayatremotekeyentity")
    fun clearRemoteKeys()
}