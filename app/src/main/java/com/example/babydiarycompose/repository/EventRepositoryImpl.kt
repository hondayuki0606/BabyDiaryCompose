package com.example.babydiarycompose.repository

import android.content.Context
import com.example.babydiarycompose.dao.EventDao
import com.example.babydiarycompose.data.DailyDiaryData
import com.example.babydiarycompose.data.EventData
import com.example.babydiarycompose.database.AppDatabase
import com.example.babydiarycompose.model.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor() : EventRepository {

    private var eventDao: EventDao? = null

    override suspend fun init(context: Context) {
        eventDao = AppDatabase.getDatabase(context).eventDao()
    }

    override suspend fun addEventList(eventList: List<EventData>): Boolean {
        var result = false
        CoroutineScope(Dispatchers.IO).launch {
            // データ生成
            eventList.forEach {
                eventDao?.insertAll(
                    Event(
                        yearAndMonthAndDay = it.yearAndMonthAndDay,
                        timeStamp = it.timeStamp,
                        time = it.time,
                        icon = it.imageUrl,
                        eventName = it.eventName,
                        eventDetail = it.eventDetail,
                        rightTime = it.rightTime,
                        leftTime = it.leftTime,
                    )
                )
            }
            result = true

        }.join()
        return result
    }

    override suspend fun updateEventList(eventList: List<EventData>): Boolean {
        var result = false
        CoroutineScope(Dispatchers.IO).launch {
            // データ生成
            eventList.forEach {
                eventDao?.updateEvent(
                    yearAndMonthAndDay = it.yearAndMonthAndDay,
                    timeStamp = it.timeStamp ?: 0,
                    time = it.time,
                    icon = it.imageUrl,
                    eventName = it.eventName,
                    eventDetail = it.eventDetail,
                    uid = it.uid ?: 0
                )
            }
            result = true

        }.join()
        return result
    }

    override suspend fun getAll(): List<EventData> {
        val result = arrayListOf<EventData>()
        CoroutineScope(Dispatchers.IO).launch {
//            val db = AppDatabase.getDatabase(applicationContext)
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

    override suspend fun getEventList(currentData: String): List<EventData> {
        val result = arrayListOf<EventData>()
        CoroutineScope(Dispatchers.IO).launch {
            val eventList = eventDao?.getEvent(currentData)
            eventList?.forEach {
                result.add(
                    EventData(
                        uid = it.uid,
                        yearAndMonthAndDay = it.yearAndMonthAndDay ?: "",
                        timeStamp = it.timeStamp,
                        time = it.time ?: "",
                        imageUrl = it.icon ?: 0,
                        eventName = it.eventName ?: "",
                        eventDetail = it.eventDetail ?: "",
                        rightTime = it.rightTime ?: 0,
                        leftTime = it.leftTime ?: 0
                    )
                )
            }
        }.join()
        return result
    }

    override suspend fun getDailyDiary(currentData: String): DailyDiaryData {
        val result = DailyDiaryData("",0,"",0)
        CoroutineScope(Dispatchers.IO).launch {}.join()
        return result
    }
    override suspend fun deleteEvent(uid: Int): Boolean {
        var result = false
        CoroutineScope(Dispatchers.IO).launch {
            eventDao?.delete(uid)
            result = true
        }.join()
        return result
    }
}