package com.example.babydiarycompose.activity

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
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.babydiarycompose.ui.theme.BabyDiaryComposeTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.babydiarycompose.compose.MenuScreen
import com.example.babydiarycompose.compose.RecordingScreen
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
                val navController = rememberNavController()
                val items = arrayListOf("記録", "まとめ", "成長曲線", "メニュー")
                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            items.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = selectedItemIndex == index,
                                    onClick = {
                                        selectedItemIndex = index
                                        when (selectedItemIndex) {
                                            0 -> {
                                                navController.navigate(
                                                    MenuOptions.HOME.route
                                                )
                                            }

                                            1 -> {
                                                navController.navigate(
                                                    MenuOptions.COMPONENTS.route
                                                )
                                            }

                                            2 -> {
                                                navController.navigate(
                                                    MenuOptions.ARTICLES.route
                                                )
                                            }

                                            else -> {
                                                navController.navigate(
                                                    MenuOptions.SETTINGS.route
                                                )
                                            }
                                        }
                                    },
                                    label = { item },
                                    icon = {
                                        BadgedBox(
                                            badge = {
                                                Badge()
                                            }
                                        ) {
                                            Icon(
                                                imageVector = when (index) {
                                                    0 -> {
                                                        Icons.Default.Edit
                                                    }

                                                    1 -> {
                                                        Icons.Default.Build
                                                    }

                                                    2 -> {
                                                        Icons.Default.Info
                                                    }

                                                    else -> {
                                                        Icons.Default.Menu
                                                    }
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
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = MenuOptions.HOME.route,
                    ) {
                        composable("home") {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                val eventList = stateViewModel.getHomeEvents()
                                RecordingScreen(eventList)
                            }
                        }
                        composable("components") {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                val eventList = stateViewModel.getFriendslistEvents()
                                RecordingScreen(eventList)
                            }
                        }
                        composable("articles") {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                val eventList = stateViewModel.getFriendslistEvents()
                                RecordingScreen(eventList)
                            }
                        }
                        composable("settings") {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                MenuScreen()
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

//@Preview(showBackground = true)
//@Composable
//fun PreviewMessageCard() {
//    RecordingScreen(
//
//        arrayListOf(
//            Event("14:00", R.drawable.profile_icon, "ミルク", ""),
//            Event("14:05", R.drawable.profile_icon, "母乳", "50ml")
//        )
//    )
//}
