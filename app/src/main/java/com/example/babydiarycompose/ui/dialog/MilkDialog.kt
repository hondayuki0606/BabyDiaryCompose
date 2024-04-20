package com.example.babydiarycompose.ui.dialog

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.ui.theme.BabyDiaryComposeTheme
import com.example.babydiarycompose.viewmodel.RecordingViewModel
import kotlinx.coroutines.launch

@Composable
fun MilkDialog(
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
            shape = RoundedCornerShape(16.dp), color = Color.DarkGray
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val times = arrayListOf("10ml", "20ml", "30ml")
                val scope = rememberCoroutineScope()
                LazyColumn(
                    modifier = Modifier.height(100.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    items(times) {
                        Text(text = it,
                            color = Color.Black,
                            fontSize = 12.sp,
                            modifier = Modifier.clickable {
                                scope.launch {
                                    val eventList = arrayListOf(
                                        Event(
                                            yearAndMonthAndDay = selectedDate,
                                            "${hour}:${String.format("%02d", minutes)}",
                                            resIcon,
                                            eventName,
                                            it
                                        )
                                    )
                                    viewModel.addEventList(applicationContext, eventList)
                                    setShowDialog(false)
                                    resultValue(true)
                                }
                            })
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Column {
                        Button(
                            onClick = {
                                resultValue(false)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = "キャンセル", modifier = Modifier.clickable {
                                setShowDialog(false)
                                resultValue(true)
                            })
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMilkDialog() {
    BabyDiaryComposeTheme {
        val showDialog = remember { mutableStateOf(false) }
        EventTimeSettingDialog(eventName = "", resIcon = 0, selectedDate = "", setShowDialog = {
            showDialog.value = it
        }
        )
    }
}