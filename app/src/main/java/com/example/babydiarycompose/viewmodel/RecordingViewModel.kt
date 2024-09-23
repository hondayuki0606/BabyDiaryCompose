package com.example.babydiarycompose.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.EventData
import com.example.babydiarycompose.data.RecordingUiState
import com.example.babydiarycompose.data.RecordingFooterUiState
import com.example.babydiarycompose.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecordingViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {
    private var _recordingEventUiState = MutableStateFlow(
        RecordingUiState(
            eventList = arrayListOf()
        )
    )
    var recordingEventUiState = _recordingEventUiState.asStateFlow()

    private var _recordingFooterState = MutableStateFlow(
        RecordingFooterUiState(
            iconList = Event.values()
        )
    )

    var recordingFooterState = _recordingFooterState.asStateFlow()

    suspend fun initDao(context: Context) {
        eventRepository.init(context)
    }

    suspend fun addEventList(eventList: List<EventData>) {
        eventRepository.addEventList(eventList)
    }

    suspend fun updateEventList(eventList: List<EventData>) {
        eventRepository.updateEventList(eventList)
    }

    suspend fun getEventList(currentData: String) {
        eventRepository.getEventList(currentData).let { eventList ->
            _recordingEventUiState.update {
                it.copy(
                    eventList = eventList
                )
            }
        }
    }

    fun clearEventList() {
        _recordingEventUiState.update {
            it.copy(
                eventList = emptyList()
            )
        }
    }

    suspend fun deleteEvent(uid: Int) {
        eventRepository.deleteEvent(uid)
    }
}