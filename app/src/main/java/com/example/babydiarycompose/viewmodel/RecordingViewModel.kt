package com.example.babydiarycompose.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.babydiarycompose.R
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.EventData
import com.example.babydiarycompose.data.RecordingUiState
import com.example.babydiarycompose.data.Icon
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
            iconList = arrayListOf()
        )
    )

    var recordingFooterState = _recordingFooterState.asStateFlow()

    fun initController() {
        val iconList = makeIconList()
        _recordingFooterState.update {
            it.copy(
                iconList = iconList
            )
        }
    }

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

    suspend fun deleteEvent(uid: Int) {
        eventRepository.deleteEvent(uid)
    }

    private fun makeIconList(): ArrayList<Icon> {
        return arrayListOf(
            Icon(Event.BREAST_MILK.event, R.drawable.breast_milk),
            Icon(Event.MILK.event, R.drawable.milk_icon),
            Icon("寝る", R.drawable.sleep_icon),
            Icon("起きる", R.drawable.wake_up_icon),
            Icon("おしっこ", R.drawable.pee_icon),
            Icon("うんち", R.drawable.poop_icon),
            Icon("両方", R.drawable.peepoop),
            Icon("体温", R.drawable.temperature),
            Icon("身長", R.drawable.stature),
            Icon("体重", R.drawable.bodyweight),
            Icon("頭囲", R.drawable.pee_icon),
            Icon("搾母乳", R.drawable.mammal),
            Icon("離乳食", R.drawable.babyfood),
            Icon("おやつ", R.drawable.midafternoonsnack),
            Icon("ごはん", R.drawable.misjudgement),
            Icon("のみもの", R.drawable.drink),
            Icon("せき", R.drawable.cough),
            Icon("吐く", R.drawable.breatheout),
            Icon("発疹", R.drawable.rash),
            Icon("けが", R.drawable.hurt),
            Icon("お風呂", R.drawable.bath),
            Icon("くすり", R.drawable.drug),
            Icon("病院", R.drawable.hospital),
            Icon("予防接種", R.drawable.immunization),
            Icon("さんぽ", R.drawable.stroll),
            Icon("搾乳", R.drawable.milking),
            Icon("できた", R.drawable.itsdone),
            Icon("その他", R.drawable.other),
            // カスタム
            Icon("カスタム1", R.drawable.custom1),
            Icon("カスタム2", R.drawable.custom2),
            Icon("カスタム3", R.drawable.custom3),
            Icon("カスタム4", R.drawable.custom4),
            Icon("カスタム5", R.drawable.custom5),
            Icon("カスタム6", R.drawable.custom6),
            Icon("カスタム7", R.drawable.custom7),
            Icon("カスタム8", R.drawable.custom8),
            Icon("カスタム9", R.drawable.custom9),
            Icon("カスタム10", R.drawable.custom10),
        )
    }
}