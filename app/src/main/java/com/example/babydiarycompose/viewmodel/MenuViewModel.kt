package com.example.babydiarycompose.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.babydiarycompose.data.EventData
import com.example.babydiarycompose.data.GrowthCurveUiState
import com.example.babydiarycompose.models.User
import com.example.babydiarycompose.repository.EventRepository
import com.example.babydiarycompose.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    private var _uiState = MutableStateFlow(
        GrowthCurveUiState(
            ageList = arrayListOf(
                "0",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
                "12\n(ヵ月)"
            ),
            weightList = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "(kg)\n10"),
            cmList = arrayListOf("40", "45", "50", "55", "60", "65", "70", "75", "(cm)\n80"),
            growthWeight = listOf(),
            growthHeight = listOf(),
            tabList = arrayListOf("1歳まで", "2歳まで", "4歳まで", "12歳まで", "頭囲")
        )
    )
    var uiState = _uiState.asStateFlow()

    fun addEventList(appContext: Context, eventList: List<EventData>) {
        viewModelScope.launch(Dispatchers.IO) {
            eventRepository.addEventList(eventList).let { result ->
//                if (result) {
//                    getEventList(appContext)
//                }
            }
        }
    }

    fun createUser(userId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.createUser(User(userId)).let { result ->

            }
        }
    }

    fun getUserByName(){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUserByName().let { result ->
                val ret = result
            }
        }
    }
    fun loginUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.loginUser().let { result ->
                val ret = result
            }
        }
    }

    fun getEventList(appContext: Context, currentData: String) {
        viewModelScope.launch(Dispatchers.IO) {
            eventRepository.getEventList(currentData).let { eventList ->

                _uiState.update {
                    it.copy(

                    )
                }
            }
        }
    }
}