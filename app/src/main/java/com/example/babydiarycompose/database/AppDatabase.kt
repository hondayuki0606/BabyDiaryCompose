package com.example.babydiarycompose.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.babydiarycompose.dao.EventDao
import com.example.babydiarycompose.model.Event

@Database(entities = [Event::class], version = 1)
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
                    DB_NAME)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    // Dao
    abstract fun eventDao(): EventDao
}

//package com.example.babydiarycompose.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.babydiarycompose.dao.EventDao
//import com.example.babydiarycompose.model.Event
//
//@Database(entities = [Event::class], version = 1)
//abstract class AppDatabase : RoomDatabase() {
//    companion object {
//        private const val DB_NAME = "room-sample.db"
//
//        @Volatile
//        var INSTANCE: AppDatabase? = null
//
//        var test: AppDatabase? = null
//        fun getDatabase(context: Context): AppDatabase {
//            return if (instance == null) {
//                synchronized(this) {
//                instance = Room.databaseBuilder(
//                        context,
//                        AppDatabase::class.java,
//                        DB_NAME
//                    ).build()
//                }
//            }
//        }
//    }
//
//    // Dao
//    abstract fun eventDao(): EventDao
//}