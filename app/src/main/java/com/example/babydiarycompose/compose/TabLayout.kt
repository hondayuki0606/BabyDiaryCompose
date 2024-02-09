package com.example.babydiarycompose.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import com.example.babydiarycompose.ui.theme.Pink40
import com.example.babydiarycompose.utils.Pink

@Composable
fun TabLayout() {
    var selectedTabIndex by remember { mutableStateOf(0) }

    TabRow(
        selectedTabIndex = selectedTabIndex,
    ) {
        val item = arrayListOf("食事", "睡眠", "排泄", "体温", "日記", "すべて")
        item.forEachIndexed { index, tabName ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                },
                text = {
                    Text(
                        text = tabName,
                        fontWeight = FontWeight.Bold,
                        color = if (selectedTabIndex == index) Pink else Pink40
                    )
                }
            )
        }
    }
}