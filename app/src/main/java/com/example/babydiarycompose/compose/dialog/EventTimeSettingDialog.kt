package com.example.babydiarycompose.compose.dialog

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.babydiarycompose.compose.NumberPicker
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.ui.theme.BabyDiaryComposeTheme
import com.example.babydiarycompose.viewmodel.EventViewModel
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Composable
fun EventTimeSettingDialog(
    eventName: String,
    resIcon: Int,
    setShowDialog: (Boolean) -> Unit,
    setValue: (String) -> Unit
) {
    val viewModel: EventViewModel = hiltViewModel()
    val currentDateTime = LocalDateTime.now()
    ZonedDateTime.of(currentDateTime, ZoneId.of("Asia/Tokyo"))
    val hour = currentDateTime.hour
    val minute = currentDateTime.minute

    val hourState = remember { mutableStateOf(hour) }
    val minutesState = remember { mutableStateOf(minute) }
    val applicationContext = LocalContext.current

    val showMilkDialog = remember { mutableStateOf(false) }
    if (showMilkDialog.value)
        MilkDialog(eventName = eventName,
            resIcon = resIcon,
            hour = hourState.value,
            minutes = minutesState.value,
            setShowDialog = {
                showMilkDialog.value = it
            }) {
            if (it) {
                setShowDialog(false)
            }
        }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.DarkGray
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(
                                    start = 10.dp,
                                    end = 10.dp,
                                ),
                            contentScale = ContentScale.Fit,
                            painter = painterResource(resIcon),
                            contentDescription = "image"
                        )
                        Text(
                            text = eventName,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Image(
                            modifier = Modifier
                                .padding(
                                    start = 10.dp,
                                    end = 10.dp,
                                ),
                            contentScale = ContentScale.Fit,
                            painter = painterResource(resIcon),
                            contentDescription = "image"
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        NumberPicker(
                            state = hourState,
                            range = 0..23,
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        NumberPicker(
                            state = minutesState,
                            range = 0..59,
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Column {
                            Button(
                                onClick = {
                                    if (eventName == "ミルク") {
                                        showMilkDialog.value = true
                                    } else {
                                        val eventList = arrayListOf(
                                            Event(
                                                "${hourState.value}:${minutesState.value}",
                                                resIcon,
                                                eventName,
                                                ""
                                            )
                                        )
                                        viewModel.addEventList(applicationContext, eventList)
                                        setShowDialog(false)
                                    }

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
}

@Preview(showBackground = true)
@Composable
fun PreviewBreastfeedingDialog() {
    BabyDiaryComposeTheme {
        val showDialog = remember { mutableStateOf(false) }
        EventTimeSettingDialog(eventName = "",
            resIcon = 0,
            setShowDialog = {
                showDialog.value = it
            }) {
            Log.i("breastfeedingDialog", "showDialog : $it")
        }
    }
}