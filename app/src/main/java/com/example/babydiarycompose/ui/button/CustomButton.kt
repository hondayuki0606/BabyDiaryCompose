package com.example.babydiarycompose.ui.button

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.babydiarycompose.listener.ScreenTransitionListener
import com.example.babydiarycompose.ui.theme.DarkBrown
import com.example.babydiarycompose.ui.theme.Pink
import com.example.babydiarycompose.ui.theme.White


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
fun MenuIconButton(
    painterResourceId: ImageVector,
    mainTitle: String,
    subTitle: String = "",
    packageName: String = "",
    httpsUrl: String = "",
    settings: Boolean = false,
    screenTransitionListener: ScreenTransitionListener? = null
) {
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
                TextButton(
                    mainTitle,
                    subTitle,
                    packageName,
                    httpsUrl,
                    settings,
                    screenTransitionListener
                )
            }
            ArrowBackImage()
        }
    }
}

@Composable
fun MenuButton(
    mainTitle: String,
    subTitle: String = "",
    packageName: String = "",
    httpsUrl: String = ""
) {
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
            TextButton(
                mainTitle = mainTitle,
                subTitle = subTitle,
                packageName = packageName,
                httpsUrl = httpsUrl
            )
            ArrowBackImage()
        }
    }
}

@Composable
fun ToggleButton(
    mainTitle: String,
    subTitle: String = "",
) {
    var checked by remember { mutableStateOf(true) }
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
            TextButton(
                mainTitle = mainTitle,
                subTitle = subTitle,
            )
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                }
            )
        }
    }
}

@Composable
fun TextButton(
    mainTitle: String,
    subTitle: String = "",
    packageName: String = "",
    httpsUrl: String = "",
    settings: Boolean = false,
    screenTransitionListener: ScreenTransitionListener? = null
) {
    val context = LocalContext.current
    Column(modifier = Modifier
        .clickable {
            if (packageName.isNotEmpty()) {
                val appUrl = "market://details?id=$packageName"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(appUrl)
                try {
                    context.startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    intent.data =
                        Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                    context.startActivity(intent)
                }
            } else if (httpsUrl.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(httpsUrl)
                try {
                    context.startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    intent.data =
                        Uri.parse(httpsUrl)
                    context.startActivity(intent)
                }
            } else if (settings) {
                screenTransitionListener?.settingsTransitionListener()
            }
        }) {
        Text(
            text = mainTitle, color = White
        )
        if (subTitle.isNotEmpty()) {
            Text(
                text = subTitle,
                color = White,
                style = TextStyle(fontSize = 12.sp)
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