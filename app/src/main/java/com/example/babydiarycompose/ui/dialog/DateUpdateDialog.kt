package com.example.babydiarycompose.ui.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.babydiarycompose.compose.ListItemPicker
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.EventData
import com.example.babydiarycompose.ui.theme.DialogBackGray
import com.example.babydiarycompose.ui.theme.Pink
import com.example.babydiarycompose.viewmodel.RecordingViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun DateUpdateDialog(
    eventName: String,
    selectedDate: String,
    time: String,
    resIcon: Int,
    uid: Int? = null,
    setShowDialog: (Boolean) -> Unit,
    resultValue: (Boolean) -> Unit,
    editMode: Boolean = false
) {
    val viewModel: RecordingViewModel = hiltViewModel()
    val split = time.split(":")
    val hour = split[0]
    val minutes = split[1]
    var hourState by remember { mutableStateOf(hour) }
    var minutesState by remember { mutableStateOf(minutes) }
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
                val (eventTitle, picker, buttonArea) = createRefs()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .background(DialogBackGray)
                        .constrainAs(eventTitle) {
                            top.linkTo(parent.top)
                            bottom.linkTo(picker.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
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
                        ),
                        color = Color.White
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .background(DialogBackGray)
                        .constrainAs(picker) {
                            top.linkTo(eventTitle.bottom)
                            bottom.linkTo(buttonArea.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val hourArrayListOf = arrayListOf<String>()
                    for (hourElement in 0..23) {
                        hourArrayListOf.add(hourElement.toString())
                    }
                    val minuteArrayListOf = arrayListOf<String>()
                    for (minuteElement in 0..59) {
                        minuteArrayListOf.add(minuteElement.toString())
                    }
                    ListItemPicker(
                        value = hourState,
                        onValueChange = { hourState = it },
                        list = hourArrayListOf,
                        dividersColor = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    ListItemPicker(
                        value = minutesState,
                        onValueChange = { minutesState = it },
                        list = minuteArrayListOf,
                        dividersColor = Color.Gray
                    )
                }
                val scope = rememberCoroutineScope()
                Column(modifier = Modifier
                    .constrainAs(buttonArea) {
                        top.linkTo(picker.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                    Button(
                        onClick = {
                            scope.launch {
                                when (eventName) {
                                    Event.BREAST_MILK.event -> {
                                        setShowDialog(true)
                                    }

                                    Event.MILK.event -> {
                                        setShowDialog(true)
                                    }

                                    else -> {
                                        val formatter =
                                            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")
                                        val hourValue = String.format("%02d", hour)
                                        val minutesValue = String.format("%02d", minutes)
                                        val date = "$selectedDate $hourValue:$minutesValue"
                                        val localDateTime = LocalDateTime.parse(date, formatter)
                                        val unixTime =
                                            localDateTime.atZone(ZoneId.systemDefault())
                                                .toEpochSecond()

                                        val eventList = arrayListOf(
                                            EventData(
                                                yearAndMonthAndDay = selectedDate,
                                                timeStamp = unixTime,
                                                time = "${hourState.toInt()}:${
                                                    String.format(
                                                        "%02d",
                                                        minutesState.toInt()
                                                    )
                                                }",
                                                imageUrl = resIcon,
                                                eventName = eventName,
                                                eventDetail = ""
                                            )
                                        )
                                        viewModel.addEventList(
                                            eventList
                                        )
                                        resultValue(true)
                                    }
                                }
                            }

                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
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
                            resultValue(false)
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