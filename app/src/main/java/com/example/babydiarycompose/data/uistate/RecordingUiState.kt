package com.example.babydiarycompose.data.uistate

import androidx.compose.runtime.Stable
import com.example.babydiarycompose.data.EventData

@Stable
data class RecordingUiState(
    var eventList: List<EventData>,
)