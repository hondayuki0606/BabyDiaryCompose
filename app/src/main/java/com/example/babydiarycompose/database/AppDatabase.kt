package com.example.babydiarycompose.database

import androidx.room.Database
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

    // Dao
    abstract fun eventDao(): EventDao
    abstract fun dailyDiaryDao(): DailyDiaryDao
}
