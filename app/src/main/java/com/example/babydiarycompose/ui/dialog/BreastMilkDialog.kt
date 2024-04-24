package com.example.babydiarycompose.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.babydiarycompose.compose.NumberPicker
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.ui.theme.BabyDiaryComposeTheme
import com.example.babydiarycompose.ui.theme.Pink
import com.example.babydiarycompose.viewmodel.RecordingViewModel
import kotlinx.coroutines.launch

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
    val rightTime = remember { mutableIntStateOf(5) }
    val leftTime = remember { mutableIntStateOf(5) }
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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                ) {
                    Text(
                        text = "${eventName}:${hour}時${minutes}分",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.DarkGray),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "左", color = Color.White)
                            NumberPicker(
                                state = rightTime,
                                range = 0..120,
                            )
                        }
                        Spacer(
                            modifier = Modifier.width(50.dp),
                        )

                        Column {
                            Text(text = "右", color = Color.White)
                            NumberPicker(
                                state = leftTime,
                                range = 0..120,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                val scope = rememberCoroutineScope()
                Box {
                    Column {
                        Button(
                            onClick = {
                                scope.launch {
                                    val eventList = arrayListOf(
                                        Event(
                                            yearAndMonthAndDay = selectedDate,
                                            time = "${hour}:${String.format("%02d", minutes)}",
                                            imageUrl = resIcon,
                                            eventName = eventName,
                                            eventDetail = "右:${rightTime.intValue}分 / 左${leftTime.intValue}分"
                                        )
                                    )
                                    viewModel.addEventList(applicationContext, eventList)
                                    setShowDialog(false)
                                    resultValue(true)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = Color.DarkGray,
                                contentColor = Color.DarkGray
                            )
                        ) {
                            Text(text = "OK", color = Pink)
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            onClick = {
                                setShowDialog(false)
                                resultValue(true)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = Color.DarkGray,
                                contentColor = Color.DarkGray
                            )
                        ) {
                            Text(text = "キャンセル", color = Pink)
                        }
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