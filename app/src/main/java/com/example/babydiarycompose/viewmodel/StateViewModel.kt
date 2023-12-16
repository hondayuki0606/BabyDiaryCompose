package com.example.babydiarycompose.viewmodel

import androidx.lifecycle.ViewModel
import com.example.babydiarycompose.data.ActionItem
import com.example.babydiarycompose.data.SessionDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime

@HiltViewModel
class StateViewModel : ViewModel() {

    //    var state = Flow<SessionDetailStateState>
    suspend fun getState(): SessionDetailState {
        return SessionDetailState(ActionItem(LocalDateTime.now(), null, "name", "detail"))
    }
}