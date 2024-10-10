package com.example.babydiarycompose.model

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity @Parcelize
data class DailyDiary(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "year_and_month_and_day") val yearAndMonthAndDay: String?,
    @ColumnInfo(name = "comment") val comment: String?,
    @ColumnInfo(name = "picture") val picture: Bitmap?,
): Parcelable