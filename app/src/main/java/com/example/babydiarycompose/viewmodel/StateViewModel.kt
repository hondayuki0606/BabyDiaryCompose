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
    fun getHomeEvents(): List<Event> {
        return arrayListOf(
            Event("11:00", R.drawable.milk_icon, "ミルク", ""),
            Event("12:00", R.drawable.milk_icon, "ミルク", ""),
            Event("13:00", R.drawable.milk_icon, "ミルク", ""),
            Event("14:00", R.drawable.milk_icon, "ミルク", ""),
            Event("15:00", R.drawable.milk_icon, "ミルク", ""),
            Event("16:00", R.drawable.milk_icon, "ミルク", ""),
            Event("17:00", R.drawable.milk_icon, "ミルク", ""),
            Event("18:00", R.drawable.milk_icon, "ミルク", ""),
            Event("19:00", R.drawable.milk_icon, "ミルク", ""),
            Event("20:00", R.drawable.milk_icon, "ミルク", ""),
            Event("21:00", R.drawable.milk_icon, "ミルク", ""),
            Event("22:00", R.drawable.milk_icon, "ミルク", ""),
            Event("23:00", R.drawable.milk_icon, "ミルク", ""),
            Event("23:45", R.drawable.milk_icon, "ミルク", ""),
            Event("23:50", R.drawable.milk_icon, "ミルク", ""),
            Event("23:55", R.drawable.milk_icon, "ミルク", "50ml")
        )
    }

    fun getFriendslistEvents(): List<Event> {
        return arrayListOf(
            Event("14:00", R.drawable.breast_milk, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:05", R.drawable.milk_icon, "母乳", "50ml")
        )
    }
}