package com.example.babydiarycompose.singleton

import android.content.Context
import androidx.room.RoomDatabase
import com.example.babydiarycompose.database.AppDatabase

object Database {
     var db: RoomDatabase? = null

    fun createDB(applicationContext: Context) {
//        CoroutineScope(Dispatchers.IO).launch {
            db = AppDatabase.getDatabase(applicationContext)
//        }
    }
}