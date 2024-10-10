package com.example.babydiarycompose.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.babydiarycompose.adapter.Converter
import com.example.babydiarycompose.dao.DailyDiaryDao
import com.example.babydiarycompose.dao.EventDao
import com.example.babydiarycompose.model.DailyDiary
import com.example.babydiarycompose.model.Event

@TypeConverters(Converter::class)
@Database(entities = [Event::class, DailyDiary::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "room-sample.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    // Dao
    abstract fun eventDao(): EventDao
    abstract fun dailyDiaryDao(): DailyDiaryDao
}
