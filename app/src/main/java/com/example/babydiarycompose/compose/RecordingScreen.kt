package com.example.babydiarycompose.compose

import android.content.Intent
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
import com.example.babydiarycompose.R
import com.example.babydiarycompose.activity.EventDialogActivity
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.Icon

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordingScreen(events: List<Event>) {
    ConstraintLayout(
        modifier = Modifier
            .background(Color(0xFF3c3c3c))
            .fillMaxSize()
    ) {
        val (timeSchedule, verticalScroll, horizontalDivider, event) = createRefs()
        val times = (0..23).toList()
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
                items(events) {
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
            val icons =
                arrayListOf(
                    Icon("母乳", R.drawable.breast_milk),
                    Icon("ミルク", R.drawable.milk_icon),
                    Icon("寝る", R.drawable.sleep_icon),
                    Icon("起きる", R.drawable.wake_up_icon),
                    Icon("おしっこ", R.drawable.pee_icon),
                    Icon("うんち", R.drawable.poop_icon),
                    Icon("母乳", R.drawable.breast_milk),
                    Icon("ミルク", R.drawable.milk_icon),
                    Icon("寝る", R.drawable.sleep_icon),
                    Icon("起きる", R.drawable.wake_up_icon),
                    Icon("おしっこ", R.drawable.pee_icon),
                    Icon("うんち", R.drawable.poop_icon),
                )
            items(icons) {
                Column {
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
    val context = LocalContext.current
    Row(modifier = Modifier.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        context.startActivity(Intent(context, EventDialogActivity::class.java))
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
