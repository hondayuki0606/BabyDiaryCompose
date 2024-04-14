package com.example.babydiarycompose.data

data class Event(
    val yearAndMonthAndDay: String,
    val time: String,
    val imageUrl: Int,
    val eventName: String,
    val eventDetail: String
)