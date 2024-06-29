package com.example.babydiarycompose.database

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.babydiarycompose.dao.EventDao
import com.example.babydiarycompose.model.Event
import kotlinx.coroutines.withContext

@Database(entities = [Event::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "room-sample.db"

        @Volatile
        var INSTANCE: AppDatabase? = null

        var test: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
//            return if (instance == null) {
////                synchronized(this) {
//                instance = Room.databaseBuilder(
//                        context,
//                        AppDatabase::class.java,
//                        DB_NAME
//                    ).build()
//
////                }
//            }
//            return instance
            return test ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                test = instance
                instance
            }
        }
    }

    // Dao
    abstract fun eventDao(): EventDao
    override fun clearAllTables() {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun createOpenHelper(config: DatabaseConfiguration): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }
}