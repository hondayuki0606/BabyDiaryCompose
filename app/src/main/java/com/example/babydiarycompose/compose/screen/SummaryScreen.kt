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
                Datum(listOf(1,24), "2/11"),
                Datum(listOf(11), "2/12"),
                Datum(listOf(2,3), "2/13"),
                Datum(listOf(12), "2/14"),
                Datum(listOf(5), "2/15"),
                Datum(listOf(10), "2/16"),
                Datum(listOf(24), "2/17")
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}
