package com.example.babydiarycompose.viewmodel

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.EventData
import com.example.babydiarycompose.data.uistate.DailyDiaryUiState
import com.example.babydiarycompose.data.uistate.RecordingUiState
import com.example.babydiarycompose.data.uistate.RecordingFooterUiState
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
    // イベントUI
    private var _recordingEventUiState = MutableStateFlow(
        RecordingUiState(
            eventList = arrayListOf()
        )
    )
    var recordingEventUiState = _recordingEventUiState.asStateFlow()

    // 日記UI
    private var _recordingDailyDiaryUiState = MutableStateFlow(
        DailyDiaryUiState(
            comment = "",
            picture = null
        )
    )
    var recordingDailyDiaryUiState = _recordingDailyDiaryUiState.asStateFlow()

    // フッターUI
    private var _recordingFooterState = MutableStateFlow(
        RecordingFooterUiState(
            iconList = Event.values()
        )
    )
    var recordingFooterState = _recordingFooterState.asStateFlow()

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

    suspend fun getDailyDiary(currentData: String) {
        eventRepository.getDailyDiary(currentData).let {
            _recordingDailyDiaryUiState.update {
                it.copy(
                    comment = it.comment,
                    picture = it.picture,
                )
            }
        }
    }

    suspend fun setPicture(currentData: String, image: Bitmap) {
        eventRepository.setPicture(currentData, image).let {
            _recordingDailyDiaryUiState.update {
                it.copy(
                    picture = image
                )
            }
        }
    }

    suspend fun setComment(currentData: String, comment: String) {
        eventRepository.setComment(currentData, comment).let {
            _recordingDailyDiaryUiState.update {
                it.copy(
                    comment = comment
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