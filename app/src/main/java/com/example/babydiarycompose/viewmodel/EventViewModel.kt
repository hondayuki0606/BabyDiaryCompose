package com.example.babydiarycompose.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.babydiarycompose.R
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.Icon
import com.example.babydiarycompose.database.AppDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor() : ViewModel() {
    private var _uiState = MutableStateFlow(mutableListOf<Event>())

    var uiState = _uiState.asStateFlow()
    fun getHomeEvents(applicationContext: Context) {
        val db = AppDatabase.getDatabase(applicationContext)
        val eventDao = db.eventDao()
        val list = mutableListOf<Event>()
        CoroutineScope(Dispatchers.IO).launch {
//            // データ生成
//            for (i in 1..10) {
//                eventDao.insertAll(
//                    com.example.babydiarycompose.model.Event(
//                        i,
//                        "${i}:00",
//                        R.drawable.milk_icon,
//                        "ミルク"
//                    ),
//                )
//            }

            val eventList = eventDao.getAll()
            eventList.forEach {
                list.add(Event(it.time ?: "", it.icon ?: 0, it.eventName ?: "", ""))
            }
            _uiState.update {
                list
            }
        }
    }

    fun getIconList(): ArrayList<Icon> {
        val icons =
            arrayListOf(
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

        return icons
    }
}