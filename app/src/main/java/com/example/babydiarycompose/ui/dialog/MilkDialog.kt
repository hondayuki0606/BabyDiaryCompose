package com.example.babydiarycompose.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.EventData
import com.example.babydiarycompose.ui.theme.BabyDiaryComposeTheme
import com.example.babydiarycompose.ui.theme.DialogBackGray
import com.example.babydiarycompose.ui.theme.Pink
import com.example.babydiarycompose.viewmodel.RecordingViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

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
                val (milkTitle, mlList, buttonArea) = createRefs()
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .background(DialogBackGray)
                        .constrainAs(milkTitle) {
                            top.linkTo(parent.top)
                            bottom.linkTo(mlList.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {
                    Text(
                        text = Event.MILK.event,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                }
                val mls = arrayListOf<String>()
                for (ml in 0..15 step 5) {
                    mls.add("${ml}ml")
                }
                for (ml in 20..500 step 10) {
                    mls.add("${ml}ml")
                }
                val scope = rememberCoroutineScope()
                LazyColumn(
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .background(DialogBackGray)
                        .constrainAs(mlList) {
                            top.linkTo(milkTitle.bottom)
                            bottom.linkTo(buttonArea.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        },
                ) {
                    items(mls) { ml ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 5.dp)
                                .clickable {
                                    scope.launch {
                                        val formatter =
                                            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")
                                        val hourValue = String.format("%02d", hour)
                                        val minutesValue = String.format("%02d", minutes)
                                        val date = "$selectedDate $hourValue:$minutesValue"
                                        val localDateTime = LocalDateTime.parse(date, formatter)
                                        val unixTime =
                                            localDateTime
                                                .atZone(ZoneId.systemDefault())
                                                .toEpochSecond()
                                        val eventList = arrayListOf(
                                            EventData(
                                                uid = null,
                                                yearAndMonthAndDay = selectedDate,
                                                timeStamp = unixTime,
                                                "$hour:${String.format("%02d", minutes)}",
                                                resIcon,
                                                eventName,
                                                ml
                                            )
                                        )
                                        viewModel.addEventList(eventList)
                                        setShowDialog(false)
                                        resultValue(true)
                                    }
                                }
                        ) {
                            Text(
                                text = ml,
                                color = Color.White,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .wrapContentSize(Alignment.CenterStart)
                            )
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(2.dp)
                                    .background(color = Color.Gray)
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .constrainAs(buttonArea) {
                            top.linkTo(mlList.bottom)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                ) {
                    Button(
                        onClick = {
                            resultValue(false)
                        },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = DialogBackGray,
                            contentColor = DialogBackGray
                        ),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "キャンセル",
                            color = Pink,
                            modifier = Modifier.clickable {
                                setShowDialog(false)
                                resultValue(true)
                            }
                        )
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