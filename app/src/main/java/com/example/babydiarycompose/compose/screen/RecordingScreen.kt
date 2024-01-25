package com.example.babydiarycompose.compose.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.babydiarycompose.compose.dialog.EventDialog
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.viewmodel.EventViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordingScreen(
    eventViewModel: EventViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    eventViewModel.getHomeEvents(context)

    val icons = eventViewModel.getIconList()
    val times = (0..23).toList()
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
        val list = listOf("aaa", "bbb", "ccc", "ddd", "eee", "fff")
        val pagerState = rememberPagerState(pageCount = {
            list.size
        })
        HorizontalPager(state = pagerState,
            modifier = Modifier
                .constrainAs(verticalScroll) {
                    top.linkTo(parent.top)
                    bottom.linkTo(event.top)
                    start.linkTo(timeSchedule.end)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .background(Color(0xFF272727))) { page ->
            LazyColumn {
                items(eventViewModel.uiState.value.eventList) {
                    EventCard(event = it)
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
            items(icons) {
                val showDialog = remember { mutableStateOf(false) }
                val volumeValue = remember { mutableStateOf("10ml") }
                if (showDialog.value)
                    EventDialog(
                        volumeValue = { volumeValue.value = it },
                        setShowDialog = {
                            showDialog.value = it
                        },
                        setValue = { Log.i("showDialog", "showDialog : $it") }
                    )
                Column(
                    modifier = Modifier.clickable {
                        showDialog.value = true
                    },
                ) {
                    Image(
                        modifier = Modifier
                            .padding(
                                start = 10.dp,
                                end = 10.dp,
                            ),
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
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = DividerDefaults.color,
) {
    Divider(modifier, thickness, color)
}

@Composable
fun EventCard(event: Event) {
    val showDialog = remember { mutableStateOf(false) }
    val volumeValue = remember { mutableStateOf("10ml") }
    if (showDialog.value)
        EventDialog(
            volumeValue = { volumeValue.value = it },
            setShowDialog = {
                showDialog.value = it
            },
            setValue = { Log.i("showDialog", "showDialog : $it") }
        )

    Row(modifier = Modifier.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        showDialog.value = true
    }) {
        Column {
            Text(
                color = Color.White,
                text = event.time,
                textAlign = TextAlign.End
            )
            Text(
                color = Color(0xFFEC7786),
                text = "10時間45分前",
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
            color = Color.White,
            text = event.ml
        )
    }
}