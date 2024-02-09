package com.example.babydiarycompose.compose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.babydiarycompose.compose.BarChart
import com.example.babydiarycompose.compose.TabLayout

@Composable
fun SummaryScreen() {
    Column {
        TabLayout()
        BarChart(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}
