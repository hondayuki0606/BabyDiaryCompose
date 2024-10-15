package com.example.babydiarycompose.repository

import android.graphics.Bitmap
import com.example.babydiarycompose.dao.DailyDiaryDao
import com.example.babydiarycompose.dao.EventDao
import com.example.babydiarycompose.data.DailyDiaryData
import com.example.babydiarycompose.data.EventData
import com.example.babydiarycompose.model.DailyDiary
import com.example.babydiarycompose.model.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventDao: EventDao,
    private val dailyDiaryDao: DailyDiaryDao
) : EventRepository {

    override suspend fun addEventList(eventList: List<EventData>): Boolean {
        var result = false
        CoroutineScope(Dispatchers.IO).launch {
            // データ生成
            eventList.forEach { event ->
                eventDao.insertAll(
                    Event(
                        yearAndMonthAndDay = event.yearAndMonthAndDay,
                        timeStamp = event.timeStamp,
                        time = event.time,
                        icon = event.imageUrl,
                        eventName = event.eventName,
                        eventDetail = event.eventDetail,
                        rightTime = event.rightTime,
                        leftTime = event.leftTime,
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
                eventDao.updateEvent(
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
            val eventList = eventDao.getEvent(currentData)
            eventList.forEach { event ->
                result.add(
                    EventData(
                        uid = event.uid,
                        yearAndMonthAndDay = event.yearAndMonthAndDay ?: "",
                        timeStamp = event.timeStamp,
                        time = event.time ?: "",
                        imageUrl = event.icon ?: 0,
                        eventName = event.eventName ?: "",
                        eventDetail = event.eventDetail ?: "",
                        rightTime = event.rightTime ?: 0,
                        leftTime = event.leftTime ?: 0
                    )
                )
            }
        }.join()
        return result
    }

    override suspend fun getDailyDiary(currentData: String): DailyDiaryData {
        var result = DailyDiaryData("", null)
        CoroutineScope(Dispatchers.IO).launch {
            val dailyDiary = dailyDiaryDao.getDailyDiary(currentData) ?: return@launch
            result = DailyDiaryData(
                comment = dailyDiary.comment,
                picture = dailyDiary.picture
            )
        }.join()
        return result
    }

    override suspend fun setPicture(currentData: String, image: Bitmap): Boolean {
        var result = false
        val dailyDiary = DailyDiary(yearAndMonthAndDay = currentData, picture = image, comment = "")
        CoroutineScope(Dispatchers.IO).launch {
            dailyDiaryDao.insertDailyDiary(dailyDiary)
            result = true
        }.join()
        return result
    }

    override suspend fun setComment(currentData: String, comment: String): Boolean {
        var result = false
        val dailyDiary =
            DailyDiary(yearAndMonthAndDay = currentData, picture = null, comment = comment)
        CoroutineScope(Dispatchers.IO).launch {
            dailyDiaryDao.insertDailyDiary(dailyDiary)
            result = true
        }.join()
        return result
    }

    override suspend fun deleteEvent(uid: Int): Boolean {
        var result = false
        CoroutineScope(Dispatchers.IO).launch {
            eventDao.delete(uid)
            result = true
        }.join()
        return result
    }
}