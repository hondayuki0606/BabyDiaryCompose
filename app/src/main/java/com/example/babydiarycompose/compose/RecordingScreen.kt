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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.babydiarycompose.R
import com.example.babydiarycompose.activity.EventCard
import com.example.babydiarycompose.data.Event

@Composable
fun RecordingScreen(events: List<Event>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .background(Color.LightGray)
                .size(100.dp)
                .verticalScroll(rememberScrollState())
        ) {
            events.forEach {
                EventCard(event = it)
            }
        }
        Row(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .height(50.dp)
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        ) {
            val icons =
                arrayListOf(
                    R.drawable.breast_milk,
                    R.drawable.milk_icon,
                    R.drawable.sleep_icon,
                    R.drawable.wake_up_icon,
                    R.drawable.pee_icon,
                    R.drawable.poop_icon
                )
            icons.forEach {
                Image(
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp)
                        .padding(5.dp),
                    contentScale = ContentScale.Fit,
                    painter = painterResource(it),
                    contentDescription = "i" +
                            "mage"
                )
            }
        }
    }
}
