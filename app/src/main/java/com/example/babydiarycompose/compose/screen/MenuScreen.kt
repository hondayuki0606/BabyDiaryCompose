package com.example.babydiarycompose.compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        TopMenuButton("広告が非表示に！テーマカラーも増えます", "プレミアムプラン（最大1ヶ月無料）")
        MenuIconButton(
            painterResourceId = Icons.Default.KeyboardArrowRight,
            mainTitle = "めいたん",
            subTitle = "2023年10月14日"
        )
        MenuIconButton(
            painterResourceId = Icons.Default.Add, mainTitle = "赤ちゃんの追加・編集"
        )
        Spacer(modifier = Modifier.height(20.dp))
        MenuIconButton(
            painterResourceId = Icons.Default.AccountBox,
            mainTitle = "アカウント",
            subTitle = "共有・引継ぎ・アカウントリンク"
        )
        MenuIconButton(
            painterResourceId = Icons.Default.Search, mainTitle = "記録・日記の検索"
        )
        MenuIconButton(
            painterResourceId = Icons.Default.Create,
            mainTitle = "記録の出力",
            subTitle = "PDF・テキスト・印刷サービスについて"
        )
        MenuIconButton(
            painterResourceId = Icons.Default.Favorite,
            mainTitle = "食材リスト",
            subTitle = "離乳初期の時期・アレルギー"
        )
        MenuIconButton(painterResourceId = Icons.Default.Info, mainTitle = "ヘルプ")
        Spacer(modifier = Modifier.height(20.dp))
        MenuIconButton(
            painterResourceId = Icons.Default.Settings,
            mainTitle = "設定",
            subTitle = "カスタマイズや設定の変更はここから"
        )
        Spacer(modifier = Modifier.height(20.dp))
        MenuIconButton(
            painterResourceId = Icons.Default.KeyboardArrowRight,
            mainTitle = "プレミアムプランについて",
            subTitle = "広告費表示・限定カラー追加など"
        )
        Spacer(modifier = Modifier.height(20.dp))
        MenuButton(mainTitle = "お知らせ")
        MenuButton(mainTitle = "このアプリをレビューする")
        MenuButton(mainTitle = "お問い合わせ")
        MenuButton(mainTitle = "ぴよログを友達に教える")
        MenuButton(mainTitle = "利用規約")
        MenuButton(mainTitle = "プライバシーポリシー")
        Spacer(modifier = Modifier.height(20.dp))
        MenuIconButton(
            painterResourceId = Icons.Default.Call, mainTitle = "公式Twitterアカウント"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "ぴよログ性アプリ", color = White, style = TextStyle(fontSize = 12.sp))
        MenuIconButton(
            painterResourceId = Icons.Default.ThumbUp,
            mainTitle = "陣痛タイマー by ぴよログ",
            subTitle = "陣痛間隔を計測、共有もできます"
        )
        MenuIconButton(
            painterResourceId = Icons.Default.DateRange,
            mainTitle = "ぴよログ予防接種",
            subTitle = "ぴよログと連携して予防接種を管理"
        )
        MenuIconButton(
            painterResourceId = Icons.Default.Face,
            mainTitle = "すくすくプラス",
            subTitle = "もじ・かず・ちえを学ぶ幼児向けアプリ"
        )
        Text(
            text = "V7.13.0",
            color = White,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.align(Alignment.End)
        )
        Text(
            text = "PiyoLogId:71A1BE2-8261-4F2D-B723-9239F9903B57-2.13.0",
            color = White,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.align(Alignment.End)
        )
        Text(
            text = "11D372",
            color = White,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
fun TopMenuButton(mainTitle: String, subTitle: String = "") {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Default.KeyboardArrowRight),
                    contentDescription = null,
                    tint = Color.White
                )
                Column {
                    Text(
                        text = mainTitle,
                        color = Pink,
                        style = TextStyle(fontSize = 12.sp),
                    )
                    Text(
                        text = subTitle,
                        color = White,
                        style = TextStyle(fontSize = 12.sp),
                    )
                }
            }
            Text(text = "詳細を見る", color = Pink)
        }
    }
}

@Composable
fun MenuIconButton(painterResourceId: ImageVector, mainTitle: String, subTitle: String = "") {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBrown)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(end = 10.dp),
                    painter = rememberVectorPainter(image = painterResourceId),
                    contentDescription = null,
                    tint = Color.White
                )
                TextButton(mainTitle, subTitle)
            }
            ArrowBackImage()
        }
    }
}

@Composable
fun MenuButton(mainTitle: String, subTitle: String = "") {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBrown)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(mainTitle, subTitle)
            ArrowBackImage()
        }
    }
}

@Composable
fun TextButton(mainTitle: String, subTitle: String = "") {
    Column {
        Text(
            text = mainTitle, color = White
        )
        if (subTitle.isNotEmpty()) {
            Text(
                text = subTitle,
                color = White,
                style = TextStyle(fontSize = 12.sp),
            )
        }
    }
}

@Composable
fun ArrowBackImage() {
    Icon(
        painter = rememberVectorPainter(image = Icons.Default.KeyboardArrowRight),
        contentDescription = null,
        tint = Color.White
    )
}