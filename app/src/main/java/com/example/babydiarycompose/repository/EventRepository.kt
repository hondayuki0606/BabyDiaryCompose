package com.example.babydiarycompose.repository

import android.content.Context
import com.example.babydiarycompose.data.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
     suspend fun getHomeEvents(applicationContext: Context) : Flow<MutableList<Event>>
}