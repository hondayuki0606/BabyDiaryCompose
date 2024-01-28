package com.example.babydiarycompose.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.babydiarycompose.R
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.EventUiState
import com.example.babydiarycompose.data.Icon
import com.example.babydiarycompose.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(
        EventUiState(
            eventList = arrayListOf(),
            iconList = arrayListOf(
                Icon("母乳", R.drawable.breast_milk),
                Icon("ミルク", R.drawable.milk_icon),
                Icon("寝る", R.drawable.sleep_icon),
                Icon("起きる", R.drawable.wake_up_icon),
                Icon("おしっこ", R.drawable.pee_icon),
                Icon("うんち", R.drawable.poop_icon),
                Icon("母乳", R.drawable.breast_milk),
                Icon("ミルク", R.drawable.milk_icon),
                Icon("寝る", R.drawable.sleep_icon),
                Icon("起きる", R.drawable.wake_up_icon),
                Icon("おしっこ", R.drawable.pee_icon),
                Icon("うんち", R.drawable.poop_icon),
            )
        )
    )
    var uiState = _uiState.asStateFlow()


    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun addEventList(applicationContext: Context, eventList: List<Event>) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            eventRepository.addEventList(applicationContext, eventList).let { result ->
                if (result) {
                    getEventList(applicationContext)
                }
            }
        }
    }

    fun getEventList(applicationContext: Context) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            eventRepository.getEventList(applicationContext).let { eventList ->
                _uiState.update {
                    it.copy(
                        eventList = eventList
                    )
                }
            }
        }
    }
}