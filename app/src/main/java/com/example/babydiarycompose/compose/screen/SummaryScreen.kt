package com.example.babydiarycompose.compose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.babydiarycompose.compose.BarChart
import com.example.babydiarycompose.compose.TabLayout
import com.example.babydiarycompose.data.Datum

@Composable
fun SummaryScreen() {
    Column {
        TabLayout()
        BarChart(
            data = listOf(
                Datum(1, "d1"),
                Datum(-2, "d2"),
                Datum(3, "d3"),
                Datum(-4, "d4"),
                Datum(5, "d5"),
                Datum(-6, "d6")
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}
