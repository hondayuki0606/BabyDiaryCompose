package com.example.babydiarycompose.viewmodel

import androidx.lifecycle.ViewModel
import com.example.babydiarycompose.R
import com.example.babydiarycompose.data.ActionItem
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.SessionDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class StateViewModel @Inject constructor() : ViewModel() {
    val _uiState = MutableStateFlow(
        SessionDetailState(
            ActionItem(LocalDateTime.now(), null, "name", "detail")
        )
    )
    val _testUiState: Flow<SessionDetailState>? = MutableStateFlow(
        SessionDetailState(
            ActionItem(LocalDateTime.now(), null, "name", "detail")
        )
    )
    val uiState = _uiState.asStateFlow()
    fun getEvents(): List<Event> {
        return arrayListOf(
            Event("14:00", R.drawable.profile_icon, "ミルク", ""),
            Event("14:00", R.drawable.profile_icon, "ミルク", ""),
            Event("14:00", R.drawable.profile_icon, "ミルク", ""),
            Event("14:00", R.drawable.profile_icon, "ミルク", ""),
            Event("14:00", R.drawable.profile_icon, "ミルク", ""),
            Event("14:00", R.drawable.profile_icon, "ミルク", ""),
            Event("14:00", R.drawable.profile_icon, "ミルク", ""),
            Event("14:00", R.drawable.profile_icon, "ミルク", ""),
            Event("14:00", R.drawable.profile_icon, "ミルク", ""),
            Event("14:00", R.drawable.profile_icon, "ミルク", ""),
            Event("14:00", R.drawable.profile_icon, "ミルク", ""),
            Event("14:00", R.drawable.profile_icon, "ミルク", ""),
            Event("14:00", R.drawable.profile_icon, "ミルク", ""),
            Event("14:05", R.drawable.profile_icon, "母乳", "50ml")
        )
    }
}