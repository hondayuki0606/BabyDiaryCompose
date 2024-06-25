package com.example.babydiarycompose.repository

import android.content.Context
import com.example.babydiarycompose.data.EventData

interface EventRepository {
    suspend fun getAll(applicationContext: Context): List<EventData>
    suspend fun getEventList(applicationContext: Context, currentData: String): List<EventData>
    suspend fun addEventList(applicationContext: Context, eventList: List<EventData>): Boolean
    suspend fun updateEventList(applicationContext: Context, eventList: List<EventData>): Boolean
    suspend fun deleteEvent(applicationContext: Context, uid: Int): Boolean
}