package com.example.babydiarycompose.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.babydiarycompose.activity.EventCard
import com.example.babydiarycompose.data.Event

    @Composable
    fun EventList(events: List<Event>) {
        LazyColumn {
            items(events) { event ->
                EventCard(event = event)
            }
        }
    }
