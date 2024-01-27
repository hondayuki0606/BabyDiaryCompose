package com.example.babydiarycompose.repository

import android.content.Context
import com.example.babydiarycompose.R
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor() : EventRepository {
    override suspend fun addEventList(
        applicationContext: Context,
        eventList: List<Event>
    ): Boolean {
        var result = false
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val eventDao = db.eventDao()
            // データ生成
            for (i in 0..20) {
                eventDao.insertAll(
                    com.example.babydiarycompose.model.Event(
                        i,
                        "${i}:00",
                        R.drawable.milk_icon,
                        "ミルク"
                    ),
                )
            }
            result = true

        }.join()
        return result
    }

    override suspend fun getEventList(applicationContext: Context): List<Event> {
        val result = arrayListOf<Event>()
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val eventDao = db.eventDao()
            // データ生成
//            for (i in 0..3) {
//                eventDao.insertAll(
//                    com.example.babydiarycompose.model.Event(
//                        i,
//                        "${i}:00",
//                        R.drawable.milk_icon,
//                        "ミルク"
//                    ),
//                )
//            }
            val eventList = eventDao.getAll()
            eventList.forEach {
                result.add(Event(it.time ?: "", it.icon ?: 0, it.eventName ?: "", ""))
            }

        }.join()
        return result
    }
}