package com.example.babydiarycompose.repository

import android.content.Context
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
            val searchList = eventDao.getAll()
            // データ生成
            eventList.forEach{
                eventDao.insertAll(
                    com.example.babydiarycompose.model.Event(
                        searchList.size,
                        it.time,
                        it.imageUrl,
                        it.eventName,
                        it.ml
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
            val eventList = db.eventDao().getAll()
            eventList.forEach {
                result.add(Event(it.time ?: "", it.icon ?: 0, it.eventName ?: "", ""))
            }

        }.join()
        return result
    }
}