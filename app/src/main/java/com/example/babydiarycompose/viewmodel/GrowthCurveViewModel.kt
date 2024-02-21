package com.example.babydiarycompose.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.GrowthCurveUiState
import com.example.babydiarycompose.data.GrowthData
import com.example.babydiarycompose.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GrowthCurveViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(
        GrowthCurveUiState(
            ageList = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"),
            weightList = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"),
            cmList = arrayListOf("40", "45", "50", "55", "60", "65", "70", "75", "80"),
            recordList = listOf(),
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
                val tmp = mutableListOf(
                    GrowthData(1, 12),
                    GrowthData(2, 13),
                    GrowthData(3, 15),
                    GrowthData(4, 18),
                    GrowthData(5, 19),
                    GrowthData(6, 21),
                    GrowthData(7, 24),
                )
                _uiState.update {
                    it.copy(
                        recordList = tmp
                    )
                }
            }
        }
    }
}