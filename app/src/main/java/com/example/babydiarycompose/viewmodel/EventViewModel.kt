package com.example.babydiarycompose.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.babydiarycompose.R
import com.example.babydiarycompose.data.ActionItem
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.Icon
import com.example.babydiarycompose.data.SessionDetailState
import com.example.babydiarycompose.database.AppDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor() : ViewModel() {
    val _uiState = MutableStateFlow(
        SessionDetailState(
            ActionItem(LocalDateTime.now(), null, "name", "detail")
        )
    )
    val _testUiState: Flow<SessionDetailState>? = MutableStateFlow(
        SessionDetailState(
            ActionItem(LocalDateTime.now(), null, "name", "detail")
        )
    )
    val uiState = _uiState.asStateFlow()
    fun getHomeEvents(applicationContext: Context): List<Event> {
        val db = AppDatabase.getDatabase(applicationContext)
        val eventDao = db.eventDao()
        eventDao.insertAll(
            com.example.babydiarycompose.model.Event(
                0,
                "15:00",
                R.drawable.milk_icon,
                "ミルク"
            )
        )
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { Log.d("User", "INSERT 成功")},
//                { e -> Log.e("User", "INSERT 失敗", e) }
//            )
//        compositeDisposable.add(disposable)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { Log.d("User", "INSERT 成功")},
//                { e -> Log.e("User", "INSERT 失敗", e) }
//            )
        val eventList = eventDao.getAll()

        return arrayListOf(
            Event("11:00", R.drawable.milk_icon, "ミルク", ""),
            Event("12:00", R.drawable.milk_icon, "ミルク", ""),
            Event("13:00", R.drawable.milk_icon, "ミルク", ""),
            Event("14:00", R.drawable.milk_icon, "ミルク", ""),
            Event("15:00", R.drawable.milk_icon, "ミルク", ""),
            Event("16:00", R.drawable.milk_icon, "ミルク", ""),
            Event("17:00", R.drawable.milk_icon, "ミルク", ""),
            Event("18:00", R.drawable.milk_icon, "ミルク", ""),
            Event("19:00", R.drawable.milk_icon, "ミルク", ""),
            Event("20:00", R.drawable.milk_icon, "ミルク", ""),
            Event("21:00", R.drawable.milk_icon, "ミルク", ""),
            Event("22:00", R.drawable.milk_icon, "ミルク", ""),
            Event("23:00", R.drawable.milk_icon, "ミルク", ""),
            Event("23:45", R.drawable.milk_icon, "ミルク", ""),
            Event("23:50", R.drawable.milk_icon, "ミルク", ""),
            Event("23:55", R.drawable.milk_icon, "ミルク", "50ml")
        )
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

    fun getFriendslistEvents(): List<Event> {
        return arrayListOf(
            Event("14:00", R.drawable.breast_milk, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:00", R.drawable.milk_icon, "フレンド", ""),
            Event("14:05", R.drawable.milk_icon, "母乳", "50ml")
        )
    }
}