package com.example.babydiarycompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.babydiarycompose.ui.button.MenuButton
import com.example.babydiarycompose.ui.button.MultiToggleButton
import com.example.babydiarycompose.ui.button.ToggleButton
import com.example.babydiarycompose.ui.theme.Dark

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
            .verticalScroll(rememberScrollState())
    ) {
        MenuButton(
            mainTitle = "項目の並び替え",
        )
        MenuButton(
            mainTitle = "カスタム項目の編集",
        )
        var drinkInputSelection by remember { mutableStateOf("選択方式") }
        MultiToggleButton(
            mainTitle = "飲み物の入力方式",
            currentSelection = drinkInputSelection,
            toggleStates = listOf("選択方式", "テンキー"),
            onToggleChange = {
                drinkInputSelection = it
            }
        )
        var measureInputSelection by remember { mutableStateOf("選択方式") }
        MultiToggleButton(
            mainTitle = "身長/体重入力方式",
            currentSelection = measureInputSelection,
            toggleStates = listOf("選択方式", "テンキー"),
            onToggleChange = {
                measureInputSelection = it
            }
        )
        ToggleButton(
            mainTitle = "授乳タイマーボタン",
        )
        ToggleButton(
            mainTitle = "検索ボタン",
        )
        ToggleButton(
            mainTitle = "日記編集ボタン",
        )
    }
}
