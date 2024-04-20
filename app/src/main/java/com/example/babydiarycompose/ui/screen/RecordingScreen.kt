package com.example.babydiarycompose.ui.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.babydiarycompose.ui.dialog.EventDetailDialog
import com.example.babydiarycompose.ui.dialog.EventTimeSettingDialog
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.ui.theme.BabyDiaryComposeTheme
import com.example.babydiarycompose.ui.theme.Pink
import com.example.babydiarycompose.viewmodel.RecordingViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordingScreen(
    viewModel: RecordingViewModel = hiltViewModel(),
    rightArrowValue: (Boolean) -> Unit,
    leftArrowValue: (Boolean) -> Unit,
    currentDateValue: (Int) -> Unit,
) {
    val current = LocalContext.current
    val times = (0..23).toList()
    var selectedDate by rememberSaveable { mutableStateOf("") }
    val myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    val uiState by viewModel.uiState.collectAsState()
    ConstraintLayout(
        modifier = Modifier
            .background(Color(0xFF9C4A4A))
            .fillMaxSize()
    ) {
        val (timeSchedule, verticalScroll, horizontalDivider, event) = createRefs()
        LazyColumn(
            modifier = Modifier
                .constrainAs(timeSchedule) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(verticalScroll.start)
                    height = Dimension.fillToConstraints
                }
                .width(24.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            items(times) {
                Text(
                    text = it.toString(),
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
        val oneYear = 10
        val pagerState = rememberPagerState(
            pageCount = {
                oneYear
            },
            initialPage = oneYear
        )

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                if (page == 0) {
                    leftArrowValue(false)
                } else {
                    leftArrowValue(true)
                }
                if (page == oneYear - 1) {
                    rightArrowValue(false)
                } else {
                    rightArrowValue(true)
                }
                val currentDay = oneYear - page - 1
                currentDateValue(currentDay)
                selectedDate =
                    myFormatObj.format(LocalDateTime.now().minusDays(currentDay.toLong()))
                viewModel.getEventList(current, selectedDate)
            }
        }
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .constrainAs(verticalScroll) {
                    top.linkTo(parent.top)
                    bottom.linkTo(event.top)
                    start.linkTo(timeSchedule.end)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .background(Color(0xFF272727))) { _ ->

            LazyColumn {
                items(uiState.eventList) {
                    EventCard(event = it, selectedDate = selectedDate)
                }
            }
        }
        HorizontalDivider(modifier = Modifier
            .constrainAs(horizontalDivider) {
                start.linkTo(timeSchedule.end)
                end.linkTo(parent.end)
                top.linkTo(verticalScroll.bottom)
                bottom.linkTo(event.top)
                width = Dimension.fillToConstraints
            }
            .background(Color(0xFF272727)),
            thickness = 5.dp, color = Color(0xFFEC7786))
        LazyRow(
            modifier = Modifier
                .constrainAs(event) {
                    start.linkTo(timeSchedule.end)
                    end.linkTo(parent.end)
                    top.linkTo(horizontalDivider.bottom)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
                .background(Color(0xFF272727))
        ) {
            items(uiState.iconList) {
                val eventTimeSettingDialog = remember { mutableStateOf(false) }
                val eventName = remember { mutableStateOf("") }
                val icon = remember { mutableIntStateOf(0) }
                val scope = rememberCoroutineScope()
                if (eventTimeSettingDialog.value)
                    EventTimeSettingDialog(eventName = eventName.value,
                        resIcon = icon.intValue,
                        selectedDate = selectedDate,
                        setShowDialog = {
                            scope.launch {
                                eventTimeSettingDialog.value = it
                                viewModel.getEventList(current, selectedDate)
                            }
                        }
                    )
                Column(
                    modifier = Modifier.clickable {
                        eventTimeSettingDialog.value = true
                        eventName.value = it.title
                        icon.intValue = it.icon
                    },
                ) {
                    Image(
                        modifier = Modifier
                            .padding(
                                start = 10.dp,
                                end = 10.dp,
                            )
                            .width(50.dp)
                            .height(50.dp),
                        contentScale = ContentScale.Fit,
                        painter = painterResource(it.icon),
                        contentDescription = "image"
                    )
                    Text(
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp),
                        color = Color.White,
                        text = it.title
                    )
                }
            }
        }
    }
}

@Composable
fun EventCard(event: Event, selectedDate: String) {
    val showDialog = remember { mutableStateOf(false) }
    val volumeValue = remember { mutableStateOf("10ml") }
    if (showDialog.value)
        EventDetailDialog(
            volumeValue = { volumeValue.value = it },
            selectedDate = selectedDate,
            setShowDialog = {
                showDialog.value = it
            },
            setValue = { Log.i("showDialog", "showDialog : $it") }
        )

    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = Modifier.clickable {
                showDialog.value = true
            }) {
            Text(
                color = Color.White,
                text = event.time,
                textAlign = TextAlign.End
            )
            Text(
                color = Pink,
                text = "",
                fontSize = 8.sp
            )
        }
        Image(
            painter = painterResource(event.imageUrl),
            contentDescription = "Contact profile picture",
        )
        Text(
            color = Color.White,
            text = event.eventName
        )
        Text(
            color = Pink,
            text = event.eventDetail
        )
    }
}

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = DividerDefaults.color,
) {
    Divider(modifier, thickness, color)
}

@Preview(showBackground = true)
@Composable
fun PreviewRecordingScreen() {
    BabyDiaryComposeTheme {
        RecordingScreen(
            leftArrowValue = {
            },
            rightArrowValue = {
            },
            currentDateValue = {
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEventCard() {
    BabyDiaryComposeTheme {
        val event = Event("2024/11/11", "22", 1, "1111", "111")
        EventCard(event, selectedDate = "")
    }
}
