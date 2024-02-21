package com.example.babydiarycompose.compose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.babydiarycompose.compose.GrowthCurveChart
import com.example.babydiarycompose.compose.TabLayout
import com.example.babydiarycompose.viewmodel.GrowthCurveViewModel

@Composable
fun GrowthCurveScreen(viewModel: GrowthCurveViewModel = hiltViewModel()) {
    Column {
        val item = arrayListOf("1歳まで", "2歳まで", "4歳まで", "12歳まで", "頭囲")
        TabLayout(tabList = item, tabIndex = 0)
        GrowthCurveChart(
            data = listOf(),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}