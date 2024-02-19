package com.example.babydiarycompose.compose.dialog

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.babydiarycompose.compose.NumberPicker
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.ui.theme.BabyDiaryComposeTheme
import com.example.babydiarycompose.viewmodel.RecordingViewModel

@Composable
fun BreastMilkDialog(
    eventName: String,
    hour: Int,
    minutes: Int,
    resIcon: Int,
    setShowDialog: (Boolean) -> Unit,
    resultValue: (Boolean) -> Unit
) {
    val rightTime = remember { mutableStateOf(5) }
    val leftTime = remember { mutableStateOf(5) }
    val viewModel: RecordingViewModel = hiltViewModel()
    val applicationContext = LocalContext.current
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.DarkGray,
            modifier = Modifier.height(200.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "${eventName}:${hour}時${minutes}分", color = Color.White)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
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
                        Spacer(modifier = Modifier.width(20.dp))

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

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Column {
                        Button(
                            onClick = {
                                val eventList = arrayListOf(
                                    Event(
                                        "${hour}:${String.format("%02d", minutes)}",
                                        resIcon,
                                        eventName,
                                        "右:${rightTime.value}分 / 左${leftTime.value}分"
                                    )
                                )
                                viewModel.addEventList(applicationContext, eventList)
                                setShowDialog(false)
                                resultValue(true)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = "OK")
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            onClick = {
                                setShowDialog(false)
                                resultValue(true)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = "キャンセル")
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
        EventTimeSettingDialog(eventName = "", resIcon = 0, setShowDialog = {
            showDialog.value = it
        }) {
            Log.i("breastfeedingDialog", "showDialog : $it")
        }
    }
}