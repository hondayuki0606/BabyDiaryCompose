package com.example.babydiarycompose.repository

import android.content.Context
import android.graphics.Bitmap
import com.example.babydiarycompose.data.DailyDiaryData
import com.example.babydiarycompose.data.EventData

interface EventRepository {
    suspend fun getAll(): List<EventData>
    suspend fun getEventList(currentData: String): List<EventData>
    suspend fun getDailyDiary(currentData: String): DailyDiaryData
    suspend fun setPicture(currentData: String, image: Bitmap): Boolean
    suspend fun setComment(currentData: String, comment: String): Boolean
    suspend fun addEventList(eventList: List<EventData>): Boolean
    suspend fun updateEventList(eventList: List<EventData>): Boolean
    suspend fun deleteEvent(uid: Int): Boolean
}