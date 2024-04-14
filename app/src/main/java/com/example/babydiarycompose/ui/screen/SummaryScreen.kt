package com.example.babydiarycompose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.babydiarycompose.compose.BarChart
import com.example.babydiarycompose.compose.TabLayout
import com.example.babydiarycompose.data.Datum
import com.example.babydiarycompose.data.Item
import com.example.babydiarycompose.viewmodel.SummaryViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SummaryScreen(viewModel: SummaryViewModel = hiltViewModel()) {
    Column {
        val uiState = viewModel.uiState
        viewModel.getEventList(LocalContext.current, "")
        TabLayout(tabList = uiState.value.tabList, tabIndex = uiState.value.tabList.size - 1)
        BarChart(
            data = uiState.value.datumList,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}
