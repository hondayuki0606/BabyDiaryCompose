package com.example.babydiarycompose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.babydiarycompose.R
import com.example.babydiarycompose.ui.theme.BabyDiaryComposeTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.babydiarycompose.compose.BottomBar
import com.example.babydiarycompose.compose.EventList
import com.example.babydiarycompose.data.Event
import com.example.babydiarycompose.data.MenuOptions
import com.example.babydiarycompose.viewmodel.StateViewModel

class MainActivity : ComponentActivity() {

    private val stateViewModel: StateViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BabyDiaryComposeTheme {
                // A surface container using the 'background' color from the theme
                val menus = remember { MenuOptions.values() }
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
//                    bottomBar = {
//                        BottomBar(navController = navController, menus = menus)
//                    },
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = MenuOptions.SETTINGS.route,
                    ) {
                        composable("home") {
                            val eventList = stateViewModel.getHomeEvents()
                            EventList(eventList)
                        }
                        composable("components") {
                            val eventList = stateViewModel.getProfileEvents()
                            EventList(eventList)
                        }
                        composable("articles") {
                            val eventList = stateViewModel.getFriendslistEvents()
                            EventList(eventList)
                        }
                        composable("settings") {
                            val eventList = stateViewModel.getFriendslistEvents()
                            EventList(eventList)
                        }
                    }
                }
            }
        }
        application
    }
}

@Composable
fun EventCard(event: Event) {
    Row {
        Text(text = event.time)
        Image(
            painter = painterResource(event.imageUrl),
            contentDescription = "Contact profile picture",
        )
        Text(text = event.eventName)
        Text(text = event.ml)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMessageCard() {
    EventList(
        arrayListOf(
            Event("14:00", R.drawable.profile_icon, "ミルク", ""),
            Event("14:05", R.drawable.profile_icon, "母乳", "50ml")
        )
    )
}
