package com.example.babydiarycompose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "year_and_month_and_day") val yearAndMonthAndDay: String?,
    @ColumnInfo(name = "time_stamp") val timeStamp: Long?,
    @ColumnInfo(name = "time") val time: String?,
    @ColumnInfo(name = "icon") val icon: Int?,
    @ColumnInfo(name = "event_name") val eventName: String?,
    @ColumnInfo(name = "event_detail") val eventDetail: String?,
    @ColumnInfo(name = "right_time") val rightTime: Int?,
    @ColumnInfo(name = "left_time") val leftTime: Int?,
)