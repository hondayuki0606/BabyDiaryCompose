package com.example.babydiarycompose.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.babydiarycompose.activity.EventCard
import com.example.babydiarycompose.data.Event

@Composable
fun RecordingScreen(events: List<Event>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .size(100.dp)
            .verticalScroll(rememberScrollState())
    ) {
        events.forEach {
            EventCard(event = it)
        }
    }
}
