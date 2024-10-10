package com.example.babydiarycompose.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.babydiarycompose.model.DailyDiary
import com.example.babydiarycompose.model.Event

@Dao
interface DailyDiaryDao {

    @Query("SELECT * FROM dailydiary where dailydiary.year_and_month_and_day = :currentData")
    fun getDailyDiary(currentData: String): DailyDiary

    @Insert
    fun insertDailyDiary(vararg dailyDiary: DailyDiary)

}