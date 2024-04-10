package com.example.babydiarycompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.babydiarycompose.ui.button.MenuButton
import com.example.babydiarycompose.ui.theme.Blue
import com.example.babydiarycompose.ui.theme.Dark
import com.example.babydiarycompose.ui.theme.DarkBrown
import com.example.babydiarycompose.ui.theme.White

@Composable
fun AccountScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkBrown),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "ぴよログID", color = White)
            Text(text = "71A1BE2-8261-4F2D-B723-9239F9903B57-2", color = White)
            Text(text = "ユーザー名", color = White)
            Text(text = "なお", color = Blue)
            Text(text = "KFCL", color = White)
        }
        Spacer(modifier = Modifier.height(20.dp))
        MenuButton(
            mainTitle = "アカウントリンク",
        )
        MenuButton(
            mainTitle = "引継ぎコードの発行",
        )
        MenuButton(
            mainTitle = "引継ぎの手順",
        )
        Spacer(modifier = Modifier.height(20.dp))
        MenuButton(
            mainTitle = "連携サービス",
        )
        Spacer(modifier = Modifier.height(20.dp))
        MenuButton(
            mainTitle = "退会",
        )
    }
}
