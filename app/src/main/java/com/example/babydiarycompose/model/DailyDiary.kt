package com.example.babydiarycompose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailyDiary(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "year_and_month_and_day") val yearAndMonthAndDay: String?,
    @ColumnInfo(name = "time_stamp") val timeStamp: Long?,
    @ColumnInfo(name = "comment") val rightTime: String?,
    @ColumnInfo(name = "left_time") val leftTime: Int?,
)