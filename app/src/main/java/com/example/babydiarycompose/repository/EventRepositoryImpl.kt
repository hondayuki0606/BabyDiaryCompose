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
            eventList.forEach {
                eventDao.insertAll(
                    com.example.babydiarycompose.model.Event(
                        searchList.size,
                        yearAndMonthAndDay = it.yearAndMonthAndDay,
                        time = it.time,
                        icon = it.imageUrl,
                        eventName = it.eventName,
                        eventDetail = it.eventDetail,
                    ),
                )
            }
            result = true

        }.join()
        return result
    }

    override suspend fun getAll(applicationContext: Context): List<Event> {
        val result = arrayListOf<Event>()
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(applicationContext)
//            val eventList = db.eventDao().getEvent(currentData)
//            eventList.forEach {
//                result.add(
//                    Event(
//                        yearAndMonthAndDay = it.yearAndMonthAndDay ?: "",
//                        time = it.time ?: "",
//                        imageUrl = it.icon ?: 0,
//                        eventName = it.eventName ?: "",
//                        eventDetail = it.eventDetail ?: ""
//                    )
//                )
//            }
        }.join()
        return result
    }

    override suspend fun getEventList(
        applicationContext: Context,
        currentData: String
    ): List<Event> {
        val result = arrayListOf<Event>()
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val eventList = db.eventDao().getEvent(currentData)
            eventList.forEach {
                result.add(
                    Event(
                        yearAndMonthAndDay = it.yearAndMonthAndDay ?: "",
                        time = it.time ?: "",
                        imageUrl = it.icon ?: 0,
                        eventName = it.eventName ?: "",
                        eventDetail = it.eventDetail ?: ""
                    )
                )
            }
        }.join()
        return result
    }
}