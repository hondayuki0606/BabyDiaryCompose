package com.example.babydiarycompose.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.SummaryUiState
import com.example.babydiarycompose.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(
        SummaryUiState(
            eventList = arrayListOf(),
            tabList = arrayListOf("食事", "睡眠", "排泄", "体温", "日記", "すべて")
        )
    )
    var uiState = _uiState.asStateFlow()

    fun addEventList(appContext: Context, eventList: List<Event>) {
        viewModelScope.launch(Dispatchers.IO) {
            eventRepository.addEventList(appContext, eventList).let { result ->
                if (result) {
                    getEventList(appContext)
                }
            }
        }
    }

    fun getEventList(appContext: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            eventRepository.getEventList(appContext).let { eventList ->
                _uiState.update {
                    it.copy(
                        eventList = eventList
                    )
                }
            }
        }
    }
}