package com.example.babydiarycompose.data

import androidx.compose.runtime.Stable

@Stable
data class EventUiState(
    val eventList: List<Event>,
    val iconList: List<Icon>
)