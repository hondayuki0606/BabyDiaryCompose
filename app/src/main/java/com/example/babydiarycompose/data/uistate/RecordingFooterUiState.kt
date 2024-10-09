package com.example.babydiarycompose.data.uistate

import androidx.compose.runtime.Stable
import com.example.babydiarycompose.data.Event

@Stable
data class RecordingFooterUiState(
    val iconList: Array<Event>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RecordingFooterUiState

        return iconList.contentEquals(other.iconList)
    }

    override fun hashCode(): Int {
        return iconList.contentHashCode()
    }
}