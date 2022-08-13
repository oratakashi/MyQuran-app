package com.oratakashi.myquran.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oratakashi.myquran.data.model.surah.SurahEntity

@Database(
    entities = [
        SurahEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class QuranDatabase: RoomDatabase() {

    abstract fun surah(): SurahDao

    companion object {

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