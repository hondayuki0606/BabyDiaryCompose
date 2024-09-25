package com.example.babydiarycompose.data

data class EventData(
    val uid: Int? = null,
    val yearAndMonthAndDay: String,
    val timeStamp: Long?,
    val time: String,
    val imageUrl: Int,
    val eventName: String,
    val eventDetail: String,
    val leftTime: Int = 0,
    val rightTime: Int = 0,
    val listItem: Boolean = false,
)