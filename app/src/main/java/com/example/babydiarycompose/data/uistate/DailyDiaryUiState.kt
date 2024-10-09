package com.example.babydiarycompose.data.uistate

import androidx.compose.runtime.Stable

@Stable
data class DailyDiaryUiState(
    var comment: String,
    var picture: Int
)