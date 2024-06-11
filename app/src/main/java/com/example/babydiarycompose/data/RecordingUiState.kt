package com.example.babydiarycompose.data

import androidx.compose.runtime.Stable


@Stable
data class RecordingUiState(
    var eventList: List<EventData>,
    val iconList: List<Icon>
)