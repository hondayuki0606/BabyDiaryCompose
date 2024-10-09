package com.example.babydiarycompose.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.babydiarycompose.model.DailyDiary

@Dao
interface DailyDiaryDao {

    @Query("SELECT comment,picture FROM dailydiary where dailydiary.year_and_month_and_day = :currentData")
    fun getDailyDiary(currentData: String): DailyDiary
}