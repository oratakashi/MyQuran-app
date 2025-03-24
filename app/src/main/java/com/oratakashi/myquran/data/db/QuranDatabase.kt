package com.oratakashi.myquran.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oratakashi.myquran.data.db.dao.AyatDao
import com.oratakashi.myquran.data.db.dao.AyatRemoteKeyDao
import com.oratakashi.myquran.data.db.dao.SurahDao
import com.oratakashi.myquran.data.model.ayat.AyatEntity
import com.oratakashi.myquran.data.model.ayat.AyatRemoteKeyEntity
import com.oratakashi.myquran.data.model.surah.SurahEntity

@Database(
    entities = [
        SurahEntity::class,
        AyatEntity::class,
        AyatRemoteKeyEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class QuranDatabase: RoomDatabase() {

    abstract fun surah(): SurahDao
    abstract fun ayat(): AyatDao
    abstract fun ayatRemoteKey(): AyatRemoteKeyDao

    companion object {

        @Volatile
        private var INSTANCE: QuranDatabase? = null

        fun getAppDatabase(context: Context): QuranDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(
                        context,
                        QuranDatabase::class.java,
                        "QuranDatabase.db"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}