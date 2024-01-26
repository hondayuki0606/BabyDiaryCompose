package com.example.babydiarycompose.repository

import android.content.Context
import com.example.babydiarycompose.data.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getEventList(applicationContext: Context): List<Event>
    suspend fun addEventList(applicationContext: Context, eventList: List<Event>): Boolean
}