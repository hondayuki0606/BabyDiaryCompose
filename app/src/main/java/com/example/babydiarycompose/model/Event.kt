package com.example.babydiarycompose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "time") val time: String?,
    @ColumnInfo(name = "icon") val icon: Int?,
    @ColumnInfo(name = "event_name") val eventName: String?,
)