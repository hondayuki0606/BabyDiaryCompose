package com.example.babydiarycompose.repository

import android.content.Context
import com.example.babydiarycompose.data.Event

interface EventRepository {
    suspend fun getAll(applicationContext: Context): List<Event>
    suspend fun getEventList(applicationContext: Context, currentData: String): List<Event>
    suspend fun addEventList(applicationContext: Context, eventList: List<Event>): Boolean
}