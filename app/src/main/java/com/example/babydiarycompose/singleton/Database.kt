package com.example.babydiarycompose.singleton

import android.content.Context
import androidx.room.Room
import com.example.babydiarycompose.database.AppDatabase

open class SingletonHolder<T, A>(creator: (A) -> T) {
    companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, AppDatabase::class.java, "room-sample.db")
            .build()
    })

    private var creator: ((A) -> T)? = creator
    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}