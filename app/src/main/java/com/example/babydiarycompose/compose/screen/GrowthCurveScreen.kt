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
import com.example.babydiarycompose.data.Item

@Composable
fun GrowthCurveScreen() {
    Column {
        val item = arrayListOf("1歳まで", "2歳まで", "4歳まで", "12歳まで", "頭囲")
        TabLayout(tabList = item, tabIndex = 0)
        BarChart(
            data = listOf(
                Datum(listOf(Item("a", 1), Item("b", 1), Item("b", 24)), "2/11"),
                Datum(listOf(Item("a", 1), Item("a", 21), Item("a", 23)), "2/12"),
                Datum(listOf(Item("a", 2), Item("a", 3)), "2/13"),
                Datum(listOf(Item("a", 12)), "2/14"),
                Datum(listOf(Item("a", 12)), "2/15"),
                Datum(listOf(Item("a", 10)), "2/16"),
                Datum(listOf(Item("a", 24)), "2/17")
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}