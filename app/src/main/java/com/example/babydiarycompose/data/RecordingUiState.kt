package com.example.babydiarycompose.data

import androidx.compose.runtime.Stable


@Stable
data class RecordingUiState(
    var eventList: List<Event>,
    val iconList: List<Icon>
)