package com.example.babydiarycompose.compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.babydiarycompose.ui.theme.Dark
import com.example.babydiarycompose.ui.theme.White

@Composable
fun MenuScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "ぴよログ操作ガイド", color = White)
        Text(text = "生後日数・満日数と数え日数の違いは？", color = White)
        Text(text = "詳細を見る", color = White)
        Text(text = "めいたん", color = White)
        Text(text = "2023年10月14日", color = White)
        Text(text = "赤ちゃんの追加・編集", color = White)
        Text(text = "アカウント", color = White)
        Text(text = "共有・引継ぎ・アカウントリンク", color = White)
        Text(text = "記録・日記の検索", color = White)
        Text(text = "記録の出力", color = White)
        Text(text = "PDF・テキスト・印刷サービスについて", color = White)
        Text(text = "食材リスト", color = White)
        Text(text = "離乳初期の時期・アレルギー", color = White)
        Text(text = "ヘルプ", color = White)
        Text(text = "設定", color = White)
        Text(text = "カスタマイズや設定の変更はここから", color = White)
        Text(text = "プレミアムプランについて", color = White)
        Text(text = "広告費表示・限定カラー追加など", color = White)

        Text(text = "お知らせ", color = White)
        Text(text = "このアプリをレビューする", color = White)
        Text(text = "お問い合わせ", color = White)
        Text(text = "ぴよログを友達に教える", color = White)
        Text(text = "利用規約", color = White)
        Text(text = "プライバシーポリシー", color = White)
        Text(text = "公式Twitterアカウント", color = White)
        Text(text = "ぴよログ性アプリ", color = White)
        Text(text = "陣痛タイマー by ぴよログ", color = White)
        Text(text = "陣痛間隔を計測、共有もできます", color = White)
        Text(text = "ぴよログ予防接種", color = White)
        Text(text = "ぴよログと連携して予防接種を管理", color = White)
        Text(text = "すくすくプラス", color = White)
        Text(text = "もじ・かず・ちえを学ぶ幼児向けアプリ", color = White)
        Text(text = "V7.13.0", color = White)
        Text(text = "PiyoLogId:71A1BE2-8261-4F2D-B723-9239F9903B57-2.13.0", color = White)
        Text(text = "11D372", color = White)
    }
}
