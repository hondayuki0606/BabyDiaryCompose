package com.example.babydiarycompose.repository

import android.content.Context
import com.example.babydiarycompose.data.EventData
import com.example.babydiarycompose.database.AppDatabase
import com.example.babydiarycompose.model.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor() : EventRepository {
    override suspend fun addEventList(
        applicationContext: Context,
        eventList: List<EventData>
    ): Boolean {
        var result = false
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val eventDao = db.eventDao()
            // データ生成
            eventList.forEach {
                eventDao.insertAll(
                    Event(
                        yearAndMonthAndDay = it.yearAndMonthAndDay,
                        timeStamp = it.timeStamp,
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

    override suspend fun updateEventList(
        applicationContext: Context,
        eventList: List<EventData>
    ): Boolean {
        var result = false
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val eventDao = db.eventDao()
            // データ生成
            eventList.forEach {
                eventDao.insertAll(
                    Event(
                        yearAndMonthAndDay = it.yearAndMonthAndDay,
                        timeStamp = it.timeStamp,
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

    override suspend fun getAll(applicationContext: Context): List<EventData> {
        val result = arrayListOf<EventData>()
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
    ): List<EventData> {
        val result = arrayListOf<EventData>()
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val eventList = db.eventDao().getEvent(currentData)
            eventList.forEach {
                result.add(
                    EventData(
                        uid = it.uid,
                        yearAndMonthAndDay = it.yearAndMonthAndDay ?: "",
                        timeStamp = it.timeStamp,
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

    override suspend fun deleteEvent(applicationContext: Context, uid: Int): Boolean {
        var result = false
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(applicationContext)
            db.eventDao().delete(uid)
            result = true
        }.join()
        return result
    }
}