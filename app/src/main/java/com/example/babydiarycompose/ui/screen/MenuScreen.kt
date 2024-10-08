package com.example.babydiarycompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.babydiarycompose.data.Screen
import com.example.babydiarycompose.listener.ScreenTransitionListener
import com.example.babydiarycompose.ui.button.MenuButton
import com.example.babydiarycompose.ui.button.MenuIconButton
import com.example.babydiarycompose.ui.button.TopMenuButton
import com.example.babydiarycompose.ui.theme.Dark
import com.example.babydiarycompose.ui.theme.White
import com.example.babydiarycompose.viewmodel.MenuViewModel

@Composable
fun MenuScreen(
    viewModel: MenuViewModel = hiltViewModel(),
    screenTransitionListener: ScreenTransitionListener
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
            .verticalScroll(rememberScrollState())
    ) {
        TopMenuButton("広告が非表示に！テーマカラーも増えます", "プレミアムプラン（最大1ヶ月無料）")
        MenuIconButton(
            painterResourceId = Icons.Default.KeyboardArrowRight,
            mainTitle = "めいたん",
            subTitle = "2023年10月14日",
            viewModel = viewModel
        )
        MenuIconButton(
            painterResourceId = Icons.Default.Add,
            mainTitle = "赤ちゃんの追加・編集",
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.height(20.dp))
        MenuIconButton(
            painterResourceId = Icons.Default.AccountBox,
            mainTitle = "アカウント",
            subTitle = "共有・引継ぎ・アカウントリンク",
            screenName = Screen.ACCOUNT.route,
            viewModel = viewModel,
            screenTransitionListener = screenTransitionListener,
        )
        MenuIconButton(
            painterResourceId = Icons.Default.Search,
            mainTitle = "記録・日記の検索",
            screenName = Screen.SEARCH_OF_RECORD_AND_DIARY.route,
            viewModel = viewModel,
            screenTransitionListener = screenTransitionListener,
        )
        MenuIconButton(
            painterResourceId = Icons.Default.Create,
            mainTitle = "記録の出力",
            subTitle = "PDF・テキスト・印刷サービスについて",
            screenName = Screen.OUTPUT_OF_RECORD.route,
            viewModel = viewModel,
            screenTransitionListener = screenTransitionListener,
        )
        MenuIconButton(
            painterResourceId = Icons.Default.Favorite,
            mainTitle = "食材リスト",
            subTitle = "離乳初期の時期・アレルギー",
            viewModel = viewModel,
        )
        MenuIconButton(painterResourceId = Icons.Default.Info, mainTitle = "ヘルプ",  viewModel = viewModel,)
        Spacer(modifier = Modifier.height(20.dp))
        MenuIconButton(
            painterResourceId = Icons.Default.Settings,
            mainTitle = "設定",
            subTitle = "カスタマイズや設定の変更はここから",
            screenName = Screen.SETTINGS.route,
            viewModel = viewModel,
            screenTransitionListener = screenTransitionListener,
        )
        Spacer(modifier = Modifier.height(20.dp))
        MenuIconButton(
            painterResourceId = Icons.Default.KeyboardArrowRight,
            mainTitle = "プレミアムプランについて",
            subTitle = "広告費表示・限定カラー追加など",
            viewModel = viewModel,
        )
        Spacer(modifier = Modifier.height(20.dp))
        MenuButton(mainTitle = "お知らせ")
        MenuButton(mainTitle = "このアプリをレビューする", packageName = "jp.co.sakabou.piyolog")
        MenuButton(mainTitle = "お問い合わせ")
        MenuButton(mainTitle = "ぴよログを友達に教える")
        MenuButton(
            mainTitle = "利用規約",
            httpsUrl = "https://www.piyolog.com/app/piyolog/eula.html"
        )
        MenuButton(
            mainTitle = "プライバシーポリシー",
            httpsUrl = "https://www.piyolog.com/app/piyolog/privacy.html"
        )
        Spacer(modifier = Modifier.height(20.dp))
        MenuIconButton(
            painterResourceId = Icons.Default.Call,
            mainTitle = "公式Twitterアカウント",
            httpsUrl = "https://twitter.com/piyolog_app",
            viewModel = viewModel,
        )
        MenuButton(
            mainTitle = "半田ぴよログスポーツパーク",
            httpsUrl = "https://park.piyolog.com/"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "ぴよログ性アプリ", color = White, style = TextStyle(fontSize = 12.sp))
        MenuIconButton(
            painterResourceId = Icons.Default.ThumbUp,
            mainTitle = "陣痛タイマー by ぴよログ",
            subTitle = "陣痛間隔を計測、共有もできます",
            packageName = "jp.co.sakabou.paintimer",
            viewModel = viewModel,
        )
        MenuIconButton(
            painterResourceId = Icons.Default.DateRange,
            mainTitle = "ぴよログ予防接種",
            subTitle = "ぴよログと連携して予防接種を管理",
            packageName = "jp.co.sakabou.vaccine",
            viewModel = viewModel,
        )
        MenuIconButton(
            painterResourceId = Icons.Default.Face,
            mainTitle = "すくすくプラス",
            subTitle = "もじ・かず・ちえを学ぶ幼児向けアプリ",
            packageName = "com.piyolog.sukusukuplus",
            viewModel = viewModel,
        )
        Text(
            text = "V7.13.0\n" +
                    "PiyoLogId:71A1BE2-8261-4F2D-B723-9239F9903B57-2.13.0\n" +
                    "11D372",
            color = White,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.align(Alignment.End)
        )
    }
}
