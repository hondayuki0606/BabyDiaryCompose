package com.example.babydiarycompose.viewmodel

import androidx.lifecycle.ViewModel
import com.example.babydiarycompose.data.ActionItem
import com.example.babydiarycompose.data.SessionDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class StateViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(
        SessionDetailState(
            ActionItem(LocalDateTime.now(), null, "name", "detail")
        )
    )

    val uiState = _uiState.asStateFlow()
    suspend fun getState(): SessionDetailState {
        coroutineScope {
        }
        return SessionDetailState(ActionItem(LocalDateTime.now(), null, "name", "detail"))
    }
}