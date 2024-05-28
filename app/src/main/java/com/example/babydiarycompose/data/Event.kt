package com.example.babydiarycompose.data

data class Event(
    val yearAndMonthAndDay: String,
    val timeStamp: Long?,
    val time: String,
    val imageUrl: Int,
    val eventName: String,
    val eventDetail: String,
    val listItem: Boolean = false,
)