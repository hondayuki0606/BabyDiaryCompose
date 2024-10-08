package com.example.babydiarycompose.dao

import androidx.room.Query
import com.example.babydiarycompose.model.Event

interface DailyDiaryDao {

    @Query("SELECT * FROM event where event.year_and_month_and_day = :currentData ORDER BY event.time_stamp ASC")
    fun getDailyDiary(currentData: String): List<Event>
}