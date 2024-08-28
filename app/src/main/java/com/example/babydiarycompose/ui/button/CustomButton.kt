package com.example.babydiarycompose.ui.button

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.babydiarycompose.ui.theme.Gray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.babydiarycompose.listener.ScreenTransitionListener
import com.example.babydiarycompose.models.User
import com.example.babydiarycompose.ui.theme.BackButtonGray
import com.example.babydiarycompose.ui.theme.DarkBrown
import com.example.babydiarycompose.ui.theme.Pink
import com.example.babydiarycompose.ui.theme.White
import com.example.babydiarycompose.ui.theme.WhiteGray
import com.example.babydiarycompose.viewmodel.MenuViewModel


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
    screenName: String = "",
    viewModel: MenuViewModel,
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
                    screenName,
                    viewModel,
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
                httpsUrl = httpsUrl,
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
fun MultiToggleButton(
    mainTitle: String,
    currentSelection: String,
    toggleStates: List<String>,
    onToggleChange: ((String) -> Unit)?
) {
    val selectedTint = MaterialTheme.colorScheme.primary
    val unselectedTint = Color.Unspecified
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBrown)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                mainTitle, modifier = Modifier.padding(4.dp), color = White,
                style = TextStyle(fontSize = 12.sp)
            )
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .border(BorderStroke(1.dp, Color.LightGray))
            ) {
                toggleStates.forEachIndexed { index, toggleState ->
                    val isSelected = currentSelection.lowercase() == toggleState.lowercase()
                    val backgroundTint = if (isSelected) selectedTint else unselectedTint
                    val textColor = if (isSelected) Color.White else Color.Unspecified

                    if (index != 0) {
                        Divider(
                            color = Color.LightGray,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(1.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .background(backgroundTint)
                            .padding(vertical = 6.dp, horizontal = 8.dp)
                            .toggleable(
                                value = isSelected,
                                enabled = true,
                                onValueChange = { selected ->
                                    if (selected) {
                                        if (onToggleChange != null) {
                                            onToggleChange(toggleState)
                                        }
                                    }
                                })
                    ) {
                        Text(toggleState, color = textColor, modifier = Modifier.padding(4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ToggleButton(
    modifier: Modifier,
    currentSelection: String,
    toggleStates: List<String>,
    onToggleChange: ((String) -> Unit)?
) {
    val selectedTint = WhiteGray
    val unselectedTint = Gray
    Box(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .background(BackButtonGray)
        ) {
            toggleStates.forEach { toggleState ->
                val isSelected = currentSelection.lowercase() == toggleState.lowercase()
                val backgroundTint = if (isSelected) selectedTint else unselectedTint

                Row(
                    modifier = Modifier
                        .background(backgroundTint)
                        .padding(vertical = 6.dp, horizontal = 8.dp)
                        .toggleable(
                            value = isSelected,
                            enabled = true,
                            onValueChange = { selected ->
                                if (selected) {
                                    if (onToggleChange != null) {
                                        onToggleChange(toggleState)
                                    }
                                }
                            })
                ) {
                    Text(toggleState, color = Color.White, modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}

@Composable
fun TextButton(
    mainTitle: String,
    subTitle: String = "",
    packageName: String = "",
    httpsUrl: String = "",
    screenName: String = "",
    viewModel: MenuViewModel? = null,
    screenTransitionListener: ScreenTransitionListener? = null
) {
    val context = LocalContext.current
    Column(modifier = Modifier
        .clickable {
            if (mainTitle == "めいたん") {
                viewModel?.getUserByName()
            }
            if (mainTitle == "赤ちゃんの追加・編集") {
                viewModel?.createUser(1)
            }
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
            } else if (screenName.isNotEmpty()) {
                screenTransitionListener?.transitionListener(screenName)
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