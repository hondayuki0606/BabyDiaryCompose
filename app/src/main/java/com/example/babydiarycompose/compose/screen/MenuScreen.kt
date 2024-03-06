package com.example.babydiarycompose.compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.babydiarycompose.R
import com.example.babydiarycompose.ui.theme.Dark
import com.example.babydiarycompose.ui.theme.DarkBrown
import com.example.babydiarycompose.ui.theme.White
import com.example.babydiarycompose.utils.Pink

@Composable
fun MenuScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Row {
                Image(
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                        ),
                    contentScale = ContentScale.Fit,
                    painter = painterResource(R.drawable.profile_icon),
                    contentDescription = "image"
                )
                Column {
                    Text(
                        text = "ぴよログ操作ガイド",
                        color = Pink,
                        style = TextStyle(fontSize = 12.sp),
                    )
                    Text(
                        text = "生後日数・満日数と数え日数の違いは？",
                        color = White,
                        style = TextStyle(fontSize = 12.sp),
                    )
                }
                Text(text = "詳細を見る", color = Pink)
            }
        }

        Box(modifier = Modifier.background(DarkBrown)) {
            Row {
                Image(
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                        ),
                    contentScale = ContentScale.Fit,
                    painter = painterResource(R.drawable.profile_icon),
                    contentDescription = "image"
                )
                Column {
                    Text(
                        text = "めいたん",
                        color = White
                    )
                    Text(
                        text = "2023年10月14日",
                        color = Pink,
                        style = TextStyle(fontSize = 12.sp),
                    )
                }
                Image(
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                        ),
                    contentScale = ContentScale.Fit,
                    painter = painterResource(R.drawable.profile_icon),
                    contentDescription = "image"
                )
            }
        }
        MenuIconButton(mainTitle = "赤ちゃんの追加・編集")
        MenuIconButton(mainTitle = "アカウント", subTitle = "共有・引継ぎ・アカウントリンク")
        MenuIconButton(mainTitle = "記録・日記の検索")
        MenuIconButton(mainTitle = "記録の出力", subTitle = "PDF・テキスト・印刷サービスについて")
        MenuIconButton(mainTitle = "食材リスト", subTitle = "離乳初期の時期・アレルギー")
        MenuIconButton(mainTitle = "ヘルプ")
        MenuIconButton(mainTitle = "設定", subTitle = "カスタマイズや設定の変更はここから")
        MenuIconButton(mainTitle = "プレミアムプランについて", subTitle = "広告費表示・限定カラー追加など")
        MenuButton(mainTitle = "お知らせ")
        MenuButton(mainTitle = "このアプリをレビューする")
        MenuButton(mainTitle = "お問い合わせ")
        MenuButton(mainTitle = "ぴよログを友達に教える")
        MenuButton(mainTitle = "利用規約")
        MenuButton(mainTitle = "プライバシーポリシー")
        MenuIconButton(mainTitle = "公式Twitterアカウント")
        Text(text = "ぴよログ性アプリ", color = White)
        MenuIconButton(mainTitle = "陣痛タイマー by ぴよログ", subTitle = "陣痛間隔を計測、共有もできます")
        MenuIconButton(mainTitle = "ぴよログ予防接種", subTitle = "ぴよログと連携して予防接種を管理")
        MenuIconButton(mainTitle = "すくすくプラス", subTitle = "もじ・かず・ちえを学ぶ幼児向けアプリ")
        Text(text = "V7.13.0", color = White)
        Text(text = "PiyoLogId:71A1BE2-8261-4F2D-B723-9239F9903B57-2.13.0", color = White)
        Text(text = "11D372", color = White)
    }
}

@Composable
fun MenuIconButton(mainTitle: String, subTitle: String = "") {
    Box(modifier = Modifier.background(DarkBrown)) {
        Row {
            Image(
                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                    ),
                contentScale = ContentScale.Fit,
                painter = painterResource(R.drawable.profile_icon),
                contentDescription = "image"
            )
            Column {
                Text(
                    text = mainTitle,
                    color = White
                )
                if (subTitle.isNotEmpty()) {
                    Text(
                        text = subTitle,
                        color = White,
                        style = TextStyle(fontSize = 12.sp),
                    )
                }
            }
            Image(
                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                    ),
                contentScale = ContentScale.Fit,
                painter = painterResource(R.drawable.profile_icon),
                contentDescription = "image"
            )
        }
    }
}


@Composable
fun MenuButton(mainTitle: String, subTitle: String = "") {
    Box(modifier = Modifier.background(DarkBrown)) {
        Row {
            Column {
                Text(
                    text = mainTitle,
                    color = White
                )
                if (subTitle.isNotEmpty()) {
                    Text(
                        text = subTitle,
                        color = White,
                        style = TextStyle(fontSize = 12.sp),
                    )
                }
            }
            Image(
                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                    ),
                contentScale = ContentScale.Fit,
                painter = painterResource(R.drawable.profile_icon),
                contentDescription = "image"
            )
        }
    }
}
