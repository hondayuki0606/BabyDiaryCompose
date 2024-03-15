package com.example.babydiarycompose.ui.screen

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
    val growthHeight = viewModel.uiState.value.growthHeight
    val growthWeight = viewModel.uiState.value.growthWeight
    val cmList = viewModel.uiState.value.cmList
    val ageList = viewModel.uiState.value.ageList
    val weightList = viewModel.uiState.value.weightList
    val tabList = viewModel.uiState.value.tabList
    Column {
        TabLayout(tabList = tabList, tabIndex = 0)
        GrowthCurveChart(
            growthHeight = growthHeight,
            growthWeight = growthWeight,
            cmList = cmList,
            ageList = ageList,
            weightList = weightList,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}