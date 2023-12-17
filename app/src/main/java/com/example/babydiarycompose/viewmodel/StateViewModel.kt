package com.example.babydiarycompose.viewmodel

import androidx.lifecycle.ViewModel
import com.example.babydiarycompose.data.ActionItem
import com.example.babydiarycompose.data.SessionDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class StateViewModel @Inject constructor() : ViewModel() {

    //    var state = Flow<SessionDetailStateState>
    suspend fun getState(): SessionDetailState {
        coroutineScope {
        }
        return SessionDetailState(ActionItem(LocalDateTime.now(), null, "name", "detail"))
    }
}