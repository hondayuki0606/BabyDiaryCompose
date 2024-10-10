package com.example.babydiarycompose.data.uistate

import android.graphics.Bitmap
import androidx.compose.runtime.Stable

@Stable
data class DailyDiaryUiState(
    var comment: String,
    var picture: Bitmap?
)