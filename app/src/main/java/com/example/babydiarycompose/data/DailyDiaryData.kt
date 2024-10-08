package com.example.babydiarycompose.data

data class DailyDiaryData(
    val yearAndMonthAndDay: String,
    val timeStamp: Long?,
    val comment: String,
    val picture: Int,
)
