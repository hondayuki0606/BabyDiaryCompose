package com.example.babydiarycompose.repository

import android.content.Context
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class EventRepositoryImpl() : EventRepository {
    override suspend fun getHomeEvents(applicationContext: Context): Flow<MutableList<Event>> {
        return flow {
            CoroutineScope(Dispatchers.IO).launch {
                val db = AppDatabase.getDatabase(applicationContext)
                val eventDao = db.eventDao()
                //            // データ生成
//            for (i in 1..10) {
//                eventDao.insertAll(
//                    com.example.babydiarycompose.model.Event(
//                        i,
//                        "${i}:00",
//                        R.drawable.milk_icon,
//                        "ミルク"
//                    ),
//                )
//            }
                val list = mutableListOf<Event>()
                val eventList = eventDao.getAll()
                eventList.forEach {
                    list.add(Event(it.time ?: "", it.icon ?: 0, it.eventName ?: "", ""))
                }
                emit(list)
            }
        }
    }
}