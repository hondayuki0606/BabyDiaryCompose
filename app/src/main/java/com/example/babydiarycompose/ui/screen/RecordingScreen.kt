package com.example.babydiarycompose.ui.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.rememberVectorPainter
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
import com.example.babydiarycompose.R
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.ui.dialog.EventTimeSettingDialog
import com.example.babydiarycompose.data.EventData
import com.example.babydiarycompose.ui.dialog.DailyDialog
import com.example.babydiarycompose.ui.dialog.EventEditDialog
import com.example.babydiarycompose.ui.theme.BabyDiaryComposeTheme
import com.example.babydiarycompose.ui.theme.DarkBrown
import com.example.babydiarycompose.ui.theme.Gray
import com.example.babydiarycompose.ui.theme.Pink
import com.example.babydiarycompose.ui.theme.White
import com.example.babydiarycompose.viewmodel.RecordingViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.FileNotFoundException
import java.io.IOException
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordingScreen(
    viewModel: RecordingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val times = (0..23).toList()
    var selectedDate by rememberSaveable { mutableStateOf("") }
    val myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    val eventUiState by viewModel.recordingEventUiState.collectAsState()
    val dailyDiaryUiState by viewModel.recordingDailyDiaryUiState.collectAsState()
    val footerUiState by viewModel.recordingFooterState.collectAsState()
    var isDisplayedRightArrow by rememberSaveable { mutableStateOf(true) }
    var isDisplayedLeftArrow by rememberSaveable { mutableStateOf(true) }
    var currentData by rememberSaveable { mutableStateOf("") }
    val topFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd(E)")
    currentData = topFormatObj.format(LocalDateTime.now())
    ConstraintLayout(
        modifier = Modifier
            .background(Gray)
            .fillMaxSize()
    ) {
        val (topBar, eventSummery, timeSchedule, verticalScroll, horizontalDivider, event) = createRefs()
        val oneYear = 30
        val pagerState = rememberPagerState(
            pageCount = {
                oneYear
            },
            initialPage = oneYear
        )
        Row(modifier = Modifier
            .constrainAs(topBar) {
                top.linkTo(parent.top)
                bottom.linkTo(timeSchedule.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .fillMaxWidth()
            .background(DarkBrown),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val coroutineScope = rememberCoroutineScope()
            if (isDisplayedLeftArrow) {
                IconButton(onClick = {
                    coroutineScope.launch {
                        pagerState.scrollToPage(pagerState.currentPage - 1)
                    }
                }) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.ArrowBack),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            } else {
                Text(text = "")
            }
            Column {
                val oldDate = LocalDate.parse("2023-10-14")
                val nowDate = LocalDate.now()
                var month = nowDate.monthValue - oldDate.monthValue
                var year = nowDate.year - oldDate.year
                if (month < 0) {
                    year -= 1
                }
                month += ((nowDate.year - oldDate.year) * 12)
                val nowDay = nowDate.toString().split("-")[2].toInt()
                val oldDay = oldDate.toString().split("-")[2].toInt()
                var day = nowDay - oldDay
                if (day < 0) {
                    day = 17 - nowDay
                }
                Text(
                    color = Color.White,
                    text = "めいたん👶${year}歳${month}か月${day}日",
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    fontSize = 12.sp,
                )
                Text(
                    color = Pink,
                    text = currentData,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                )
            }
            if (isDisplayedRightArrow) {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    }
                ) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.ArrowForward),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            } else {
                Column {
                    Text(
                        color = Color.White,
                        text = "生まれてから",
                        fontSize = 8.sp,
                    )
                    Row {
                        val oldDay = LocalDate.parse("2023-10-14")
                        val now = LocalDate.now()
                        val diffDay =
                            Duration.between(oldDay.atStartOfDay(), now.atStartOfDay()).toDays()
                        Text(
                            color = Color.White,
                            text = diffDay.toString()
                        )
                        Text(
                            color = Color.White,
                            text = "日目",
                            fontSize = 8.sp,
                        )
                    }
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .constrainAs(timeSchedule) {
                    top.linkTo(topBar.bottom)
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
                if (it != 23) {
                    HorizontalDivider(color = White)
                }
            }
        }

        Row(
            modifier = Modifier
                .constrainAs(eventSummery) {
                    top.linkTo(topBar.bottom)
                    bottom.linkTo(verticalScroll.top)
                    start.linkTo(timeSchedule.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .fillMaxWidth()
                .background(DarkBrown),
        ) {
            EventSummary(eventUiState.eventList)
        }
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { pageIndex ->
                viewModel.clearEventList()
                when (pageIndex) {
                    0 -> {
                        isDisplayedLeftArrow = false
                    }

                    oneYear - 1 -> {
                        isDisplayedRightArrow = false
                    }

                    else -> {
                        isDisplayedLeftArrow = true
                        isDisplayedRightArrow = true
                    }
                }
                val currentDay = oneYear - pageIndex - 1
                currentData =
                    topFormatObj.format(LocalDateTime.now().minusDays(currentDay.toLong()))
                selectedDate =
                    myFormatObj.format(LocalDateTime.now().minusDays(currentDay.toLong()))
                viewModel.getEventList(selectedDate)
                viewModel.getDailyDiary(selectedDate)
            }
        }
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .constrainAs(verticalScroll) {
                    top.linkTo(eventSummery.bottom)
                    bottom.linkTo(event.top)
                    start.linkTo(timeSchedule.end)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .background(DarkBrown)) { _ ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState())
            ) {
                Column {
                    eventUiState.eventList.forEach { event ->
                        Log.d("", "honda LazyColumn event=$event")
                        EventCard(
                            event = event,
                            selectedDate = selectedDate,
                            viewModel = viewModel
                        )
                    }
                }
                val isDisplayedDailyDialog = remember { mutableStateOf(false) }
                Column {
                    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Row {
                            Image(
                                modifier = Modifier
                                    .padding(
                                        start = 10.dp,
                                        end = 10.dp,
                                    )
                                    .width(20.dp)
                                    .height(20.dp),
                                contentScale = ContentScale.Fit,
                                painter = painterResource(R.drawable.book),
                                contentDescription = "image"
                            )
                            Text(text = "育児日記", color = Color.White)
                        }
                        val scope = rememberCoroutineScope()
                        val startForResult =
                            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                                if (result.resultCode == Activity.RESULT_OK) {
                                    try {
                                        result.data?.data?.also { uri: Uri ->
                                            val inputStream =
                                                context.contentResolver?.openInputStream(uri)
                                            bitmap.value =
                                                BitmapFactory.decodeStream(inputStream)
                                            scope.launch {
                                                viewModel.setPicture(
                                                    currentData = currentData,
                                                    image = bitmap.value as Bitmap
                                                )
                                                viewModel.getDailyDiary(currentData)
                                            }
                                        }
                                    } catch (e: FileNotFoundException) {
                                        e.printStackTrace()
                                    } catch (e: IOException) {
                                        e.printStackTrace()
                                    }
                                }
                            }
                        Row {
                            Image(
                                modifier = Modifier
                                    .padding(
                                        start = 10.dp,
                                        end = 10.dp,
                                    )
                                    .width(20.dp)
                                    .height(20.dp)
                                    .clickable {
                                        isDisplayedDailyDialog.value = true
                                    },
                                contentScale = ContentScale.Fit,
                                painter = painterResource(R.drawable.pencil),
                                contentDescription = "image"
                            )
                            if (dailyDiaryUiState.picture == null) {
                                Image(
                                    modifier = Modifier
                                        .padding(
                                            start = 10.dp,
                                            end = 10.dp,
                                        )
                                        .width(20.dp)
                                        .height(20.dp)
                                        .clickable {
                                            val intent =
                                                Intent(Intent.ACTION_OPEN_DOCUMENT)
                                            intent.addCategory(Intent.CATEGORY_OPENABLE)
                                            intent.type = "image/*"
                                            startForResult.launch(intent)
                                        },
                                    contentScale = ContentScale.Fit,
                                    painter = painterResource(R.drawable.camera),
                                    contentDescription = "image"
                                )
                            }
                        }
                    }
                    if (dailyDiaryUiState.comment.isNotEmpty()) {
                        Text(text = dailyDiaryUiState.comment, color = White)
                    }
                    if (dailyDiaryUiState.picture != null) {
                        Image(
                            modifier = Modifier
                                .padding(
                                    start = 10.dp,
                                    end = 10.dp,
                                )
                                .fillMaxWidth()
                                .height(300.dp)
                                .clickable {},
                            contentScale = ContentScale.Fit,
                            bitmap = bitmap.value!!.asImageBitmap(),
                            contentDescription = "image"
                        )
                    }
                    val scope = rememberCoroutineScope()
                    if (isDisplayedDailyDialog.value) {
                        DailyDialog(message = { message ->
                            isDisplayedDailyDialog.value = false
                            scope.launch {
                                viewModel.setComment(
                                    currentData = currentData,
                                    comment = message
                                )
                                viewModel.getDailyDiary(currentData)
                            }
                            dailyDiaryUiState.comment = message
                        })
                    }
                    Box(modifier = Modifier.height(100.dp))
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
            .background(DarkBrown),
            thickness = 5.dp, color = Pink
        )
        LazyRow(
            modifier = Modifier
                .constrainAs(event) {
                    start.linkTo(timeSchedule.end)
                    end.linkTo(parent.end)
                    top.linkTo(horizontalDivider.bottom)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
                .background(DarkBrown)
        ) {
            items(footerUiState.iconList) {
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
                                viewModel.getEventList(selectedDate)
                            }
                        }
                    )
                Column(
                    modifier = Modifier.clickable {
                        eventTimeSettingDialog.value = true
                        eventName.value = it.event
                        icon.intValue = it.iconId
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
                        painter = painterResource(it.iconId),
                        contentDescription = "image"
                    )
                    Text(
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp),
                        color = Color.White,
                        text = it.event
                    )
                }
            }
        }
    }
}

@Composable
fun EventSummary(eventList: List<EventData>) {
    Event.values().forEach {
        val eventSum = eventList.filter { event -> event.eventName == it.event }
        if (eventSum.isNotEmpty()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .padding(
                            start = 2.dp,
                            end = 2.dp,
                        )
                        .width(20.dp)
                        .height(20.dp),
                    painter = painterResource(it.iconId),
                    contentDescription = null
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${eventSum.size}回",
                        color = White,
                        fontSize = 9.sp
                    )
                    if (eventSum.firstOrNull()?.eventName == Event.MILK.event) {
                        var sumValue = 0
                        eventSum.forEach {
                            sumValue += it.eventDetail.filter { eventDetail -> eventDetail.isDigit() }
                                .toInt()
                        }
                        Text(
                            text = "${sumValue}ml",
                            color = White,
                            fontSize = 9.sp
                        )
                    } else if (eventSum.firstOrNull()?.eventName == Event.BREAST_MILK.event) {
                        var sumRightValue = 0
                        var sumLeftValue = 0
                        eventSum.forEach {
                            sumRightValue += it.rightTime
                            sumLeftValue += it.leftTime
                        }
                        Text(
                            text = "${sumLeftValue}分/${sumRightValue}分",
                            color = White,
                            fontSize = 9.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EventCard(
    event: EventData,
    selectedDate: String,
    viewModel: RecordingViewModel,
) {
    val editDialog = remember { mutableStateOf(false) }
    if (editDialog.value) {
        val scope = rememberCoroutineScope()
        EventEditDialog(
            event = event,
            selectedDate = selectedDate,
            setShowDialog = {
                scope.launch {
                    viewModel.getEventList(selectedDate)
                    editDialog.value = it
                }
            }
        )
    }
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(
            onClick = {
                editDialog.value = true
            }
        )
    ) {
        Column {
            Text(
                color = Color.White,
                text = event.time,
                textAlign = TextAlign.End,
                modifier = Modifier.padding(start = 7.dp)
            )
            if (event.listItem) {
                Text(
                    color = Pink,
                    text = event.yearAndMonthAndDay,
                    fontSize = 8.sp
                )
            }
        }
        Image(
            painter = painterResource(event.imageUrl),
            contentDescription = "Contact profile picture",
            modifier = Modifier.padding(start = 7.dp)
        )
        Text(
            color = Color.White,
            text = event.eventName,
            modifier = Modifier.padding(start = 7.dp)
        )
        Text(
            color = Pink,
            text = event.eventDetail,
            modifier = Modifier.padding(start = 7.dp)
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
        RecordingScreen()
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewEventCard() {
//    BabyDiaryComposeTheme {
//        val event = EventData(null, "2024/11/11", 1, "22", 1, "1111", "111")
//        EventCard(event, selectedDate = "")
//    }
//}
