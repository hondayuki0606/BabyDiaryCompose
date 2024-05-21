package com.example.babydiarycompose.ui.dialog

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.babydiarycompose.R
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.ui.button.ToggleButton
import com.example.babydiarycompose.ui.theme.BabyDiaryComposeTheme
import com.example.babydiarycompose.ui.theme.DialogBackDark
import com.example.babydiarycompose.ui.theme.DialogBackGray
import com.example.babydiarycompose.ui.theme.Pink
import com.example.babydiarycompose.viewmodel.RecordingViewModel
import kotlinx.coroutines.launch

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = DividerDefaults.color,
) = Canvas(
    modifier
        .fillMaxHeight()
        .height(10.dp)
        .width(thickness)
) {
    drawLine(
        color = color,
        strokeWidth = thickness.toPx(),
        start = Offset(thickness.toPx() / 2, 0f),
        end = Offset(thickness.toPx() / 2, size.height),
    )
}

@Composable
fun BreastMilkDialog(
    eventName: String,
    selectedDate: String,
    hour: Int,
    minutes: Int,
    resIcon: Int,
    setShowDialog: (Boolean) -> Unit,
    resultValue: (Boolean) -> Unit
) {
    val viewModel: RecordingViewModel = hiltViewModel()
    val applicationContext = LocalContext.current
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            color = Color(0x00000000),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .background(Color(0x00000000))
                    .fillMaxSize()
            ) {
                val (eventTitle, leftScroll, verticalDivider, rightScroll, toggleButton, buttonArea) = createRefs()
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .background(DialogBackDark)
                        .constrainAs(eventTitle) {
                            top.linkTo(parent.top)
                            bottom.linkTo(verticalDivider.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {
                    Text(
                        text = "${eventName}:${hour}時${minutes}分",
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                }
                val minuteList = arrayListOf("なし")
                for (minute in 1..120) {
                    minuteList.add("${minute}分")
                }
                var leftCheckedState by remember { mutableStateOf("なし") }
                Column(
                    modifier = Modifier
                        .background(DialogBackDark)
                        .fillMaxWidth(.5f)
                        .constrainAs(leftScroll) {
                            top.linkTo(eventTitle.bottom)
                            bottom.linkTo(toggleButton.top)
                            start.linkTo(parent.start)
                            end.linkTo(verticalDivider.start)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints

                        },
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DialogBackDark)
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "左",
                            color = Color.White
                        )
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DialogBackGray)
                    ) {
                        items(minuteList) { itemName ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 10.dp)
                                    .clickable(
                                        onClick = {
                                            leftCheckedState = itemName
                                        }
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(text = itemName, color = Color.White)
                                if (leftCheckedState == itemName) {
                                    Image(
                                        contentScale = ContentScale.Fit,
                                        painter = painterResource(R.drawable.check_mark),
                                        contentDescription = "image"
                                    )
                                } else {
                                    Text(text = "")
                                }
                            }
                        }
                    }
                }
                VerticalDivider(
                    thickness = 2.dp, color = Color.White,
                    modifier = Modifier.constrainAs(verticalDivider) {
                        top.linkTo(eventTitle.bottom)
                        bottom.linkTo(toggleButton.top)
                        start.linkTo(leftScroll.end)
                        end.linkTo(rightScroll.start)
                        height = Dimension.fillToConstraints

                    },
                )
                var rightCheckedState by remember { mutableStateOf("なし") }
                Column(
                    modifier = Modifier
                        .background(DialogBackDark)
                        .fillMaxWidth(.5f)
                        .constrainAs(rightScroll) {
                            top.linkTo(eventTitle.bottom)
                            bottom.linkTo(toggleButton.top)
                            start.linkTo(verticalDivider.end)
                            end.linkTo(parent.end)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        },
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DialogBackDark)
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "右",
                            color = Color.White
                        )
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DialogBackGray)
                    ) {
                        items(minuteList) { itemName ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 10.dp)
                                    .clickable(
                                        onClick = {
                                            rightCheckedState = itemName
                                        }
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(text = itemName, color = Color.White)
                                if (rightCheckedState == itemName) {
                                    Image(
                                        contentScale = ContentScale.Fit,
                                        painter = painterResource(R.drawable.check_mark),
                                        contentDescription = "image"
                                    )
                                } else {
                                    Text(text = "")
                                }
                            }
                        }
                    }
                }
                var breastfeedingInputSelection by remember { mutableStateOf("順序なし") }
                ToggleButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .constrainAs(toggleButton) {
                            top.linkTo(rightScroll.bottom)
                            bottom.linkTo(buttonArea.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    currentSelection = breastfeedingInputSelection,
                    toggleStates = listOf("順序なし", "左から", "右から"),
                    onToggleChange = {
                        breastfeedingInputSelection = it
                    }
                )
                val scope = rememberCoroutineScope()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(buttonArea) {
                            top.linkTo(toggleButton.bottom)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {
                            scope.launch {
                                val rightValue = if (rightCheckedState == "なし") {
                                    ""
                                } else {
                                    "右:${rightCheckedState}"
                                }
                                val leftValue = if (leftCheckedState == "なし") {
                                    ""
                                } else {
                                    "左${leftCheckedState}"
                                }

                                val breastfeedingSelectionValue = when (breastfeedingInputSelection) {
                                    "順序なし" -> {
                                        " / "
                                    }
                                    "右から" -> {
                                        " ← "
                                    }
                                    else -> {
                                        " → "
                                    }
                                }
                                val eventDetail =
                                    if (rightValue.isNotEmpty() && leftValue.isNotEmpty()) {
                                        "$leftValue$breastfeedingSelectionValue$rightValue"
                                    } else {
                                        leftValue + breastfeedingSelectionValue + rightValue
                                    }
                                val eventList = arrayListOf(
                                    Event(
                                        yearAndMonthAndDay = selectedDate,
                                        time = "${hour}:${String.format("%02d", minutes)}",
                                        imageUrl = resIcon,
                                        eventName = eventName,
                                        eventDetail = eventDetail
                                    )
                                )
                                viewModel.addEventList(applicationContext, eventList)
                                setShowDialog(false)
                                resultValue(true)
                            }
                        },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = DialogBackGray,
                            contentColor = DialogBackGray
                        )
                    ) {
                        Text(text = "OK", color = Pink)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {
                            setShowDialog(false)
                            resultValue(true)
                        },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = DialogBackGray,
                            contentColor = DialogBackGray
                        )
                    ) {
                        Text(text = "キャンセル", color = Pink)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBreastMilkDialog() {
    BabyDiaryComposeTheme {
        val showDialog = remember { mutableStateOf(false) }
        EventTimeSettingDialog(eventName = "", resIcon = 0, selectedDate = "", setShowDialog = {
            showDialog.value = it
        })
    }
}