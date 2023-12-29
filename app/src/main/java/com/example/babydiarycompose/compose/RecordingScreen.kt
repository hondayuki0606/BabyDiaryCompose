package com.example.babydiarycompose.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.babydiarycompose.R
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.Icon

@Composable
fun RecordingScreen(events: List<Event>) {
    ConstraintLayout(
        modifier = Modifier
            .background(Color(0xFF3c3c3c))
            .fillMaxSize()
    ) {
        val (time, verticalScroll, event) = createRefs()
        Column(
            modifier = Modifier
                .constrainAs(time) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
                .width(24.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            repeat(24) {
                Text(
                    text = it.toString(),
                    color = Color.White
                )
            }
        }
        Column(
            modifier = Modifier
                .constrainAs(verticalScroll) {
                    top.linkTo(parent.top)
                    start.linkTo(time.end)
                    bottom.linkTo(event.top)
                }
                .background(Color(0xFF272727))
                .verticalScroll(rememberScrollState())
        ) {
            events.forEach {
                EventCard(event = it)
            }
        }
        Row(
            modifier = Modifier
                .constrainAs(event) {
                    top.linkTo(verticalScroll.bottom)
                    start.linkTo(time.end)
                    bottom.linkTo(parent.bottom)
                }
                .horizontalScroll(rememberScrollState())
                .background(Color(0xFF272727))
                .height(150.dp)
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
            icons.forEach {
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
fun EventCard(event: Event) {
    Row {
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
