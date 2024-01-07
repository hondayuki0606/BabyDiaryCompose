package com.example.babydiarycompose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import com.example.babydiarycompose.ui.theme.BabyDiaryComposeTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

class EventDialogActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val time = intent.getStringExtra("TIME")
        val right = intent.getStringExtra("RIGHT")
        val left = intent.getStringExtra("LEFT")
        val sort = intent.getStringExtra("SORT")
        setContent {
            BabyDiaryComposeTheme {
                Column(Modifier.background(Color.Black)) {
                    Text(
                        color = Color.White,
                        text = "母乳",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                    )
                    Text(
                        color = Color.White,
                        text = time.toString(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                    )
                    Text(
                        color = Color.White,
                        text = right.toString(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                    )
                    Text(
                        color = Color.White,
                        text = left.toString(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                    )
                    Text(
                        color = Color.White,
                        text = sort.toString(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                    )
                }

            }
            application
        }
    }
}

