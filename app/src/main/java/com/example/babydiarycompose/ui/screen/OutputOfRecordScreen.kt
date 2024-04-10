package com.example.babydiarycompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.babydiarycompose.ui.button.MenuButton
import com.example.babydiarycompose.ui.theme.Dark

@Composable
fun OutputOfRecordScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
            .verticalScroll(rememberScrollState())
    ) {
        MenuButton(
            mainTitle = "電子書籍(PDF)を作成する",
        )
        MenuButton(
            mainTitle = "電子書籍の作成と印刷について",
        )
        MenuButton(
            mainTitle = "製本サービスについて",
            subTitle = "製本直送.comの紹介"
        )
        Spacer(modifier = Modifier.height(20.dp))
        MenuButton(
            mainTitle = "電子書籍(PDF)を作成する",
            subTitle = "旧バージョン"
        )
        Spacer(modifier = Modifier.height(20.dp))
        MenuButton(
            mainTitle = "テキストでのエクスポート",
        )
    }
}

