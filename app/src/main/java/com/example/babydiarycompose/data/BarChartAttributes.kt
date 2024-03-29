package com.example.babydiarycompose.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class BarChartAttributes(
    val yMin: Int? = null,
    val yMax: Int? = null,
    val barInterval: Dp = 64.dp, // distance between each bar
    val barWidth: Dp = 48.dp,
    val barColor: Color = Color(0xFF3F_51B5),
    val axisLineColor: Color = Color(0x6000_0000),
    val dataLabelTextColor: Color = Color(0xdd00_0000),
    val dataLabelTextSize: TextUnit = 12.sp,
    val gridLineColor: Color = Color(0x6000_0000),
    val gridValueTextColor: Color = Color(0xdd00_0000),
    val gridValueTextSize: TextUnit = 12.sp,
    val gridValueFormatPattern: String? = null,
    val dataValueTextColor: Color = Color(0xdd00_0000),
    val dataValueTextSize: TextUnit = 12.sp,
    val dataValueFormatPattern: String? = null
)