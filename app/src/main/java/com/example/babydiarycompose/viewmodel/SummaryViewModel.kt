package com.example.babydiarycompose.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.babydiarycompose.data.Datum
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.Item
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
            datumList =  mutableListOf(
                Datum(listOf(Item("a", 1), Item("b", 1), Item("b", 24)), "2/11"),
                Datum(listOf(Item("a", 1), Item("a", 21), Item("a", 23)), "2/12"),
                Datum(listOf(Item("a", 2), Item("a", 3)), "2/13"),
                Datum(listOf(Item("a", 12)), "2/14"),
                Datum(listOf(Item("a", 12)), "2/15"),
                Datum(listOf(Item("a", 10)), "2/16"),
                Datum(listOf(Item("a", 24)), "2/17")
            ),
            tabList = arrayListOf("食事", "睡眠", "排泄", "体温", "日記", "すべて")
        )
    )
    var uiState = _uiState.asStateFlow()

    fun addEventList(appContext: Context, eventList: List<Event>) {
        viewModelScope.launch(Dispatchers.IO) {
            eventRepository.addEventList(appContext, eventList).let { result ->
//                if (result) {
//                    getEventList(appContext)
//                }
            }
        }
    }

    fun getEventList(appContext: Context, currentData: String) {
        viewModelScope.launch(Dispatchers.IO) {
            eventRepository.getEventList(appContext, currentData).let { eventList ->
                val tmp = mutableListOf(
                    Datum(listOf(Item("a", 1), Item("b", 1), Item("b", 24)), "2/11"),
                    Datum(listOf(Item("a", 1), Item("a", 21), Item("a", 23)), "2/12"),
                    Datum(listOf(Item("a", 2), Item("a", 3)), "2/13"),
                    Datum(listOf(Item("a", 12)), "2/14"),
                    Datum(listOf(Item("a", 12)), "2/15"),
                    Datum(listOf(Item("a", 10)), "2/16"),
                    Datum(listOf(Item("a", 24)), "2/17")
                )
                _uiState.update {
                    it.copy(
                        datumList = tmp
                    )
                }
            }
        }
    }
}