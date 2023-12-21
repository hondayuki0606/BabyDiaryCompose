package com.example.babydiarycompose.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.babydiarycompose.R
import com.example.babydiarycompose.ui.theme.BabyDiaryComposeTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
                val items = arrayListOf("1", "2", "3")
                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            NavigationBar {
                                items.forEachIndexed { index, s ->
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            selectedItemIndex = index
                                        },
                                        label = { "label" },
                                        icon = {
                                            BadgedBox(
                                                badge = {
                                                    Badge()
                                                }
                                            ) {
                                                Icon(
                                                    imageVector = if (index == selectedItemIndex) {
                                                        Icons.Default.Add
                                                    } else {
                                                        Icons.Default.Add
                                                    },
                                                    contentDescription = items[selectedItemIndex]
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            NavHost(
                                navController = navController,
                                startDestination = MenuOptions.HOME.route,
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
            }
            application
        }
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
