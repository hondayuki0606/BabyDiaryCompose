package com.example.babydiarycompose.repository

import android.content.Context
import com.example.babydiarycompose.data.EventData

interface EventRepository {
    suspend fun getAll(): List<EventData>
    suspend fun getEventList(currentData: String): List<EventData>
    suspend fun addEventList(eventList: List<EventData>): Boolean
    suspend fun updateEventList(eventList: List<EventData>): Boolean
    suspend fun deleteEvent(uid: Int): Boolean
    suspend fun init(context: Context)
}