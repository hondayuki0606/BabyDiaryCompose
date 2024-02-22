package com.example.babydiarycompose.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.GrowthCurveUiState
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
            growthWeight = listOf(),
            growthHeight = listOf(),
            tabList = arrayListOf("1歳まで", "2歳まで", "4歳まで", "12歳まで", "頭囲")
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

                    )
                }
            }
        }
    }
}