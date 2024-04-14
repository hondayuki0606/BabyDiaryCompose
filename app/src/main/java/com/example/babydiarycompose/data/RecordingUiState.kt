package com.example.babydiarycompose.data

import androidx.compose.runtime.Stable


data class RecordingUiState(
    val eventList: List<Event>,
    val iconList: List<Icon>
)