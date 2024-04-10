package com.example.babydiarycompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.babydiarycompose.ui.screen.GrowthCurveScreen
import com.example.babydiarycompose.ui.screen.MenuScreen
import com.example.babydiarycompose.ui.screen.RecordingScreen
import com.example.babydiarycompose.ui.screen.SummaryScreen
import com.example.babydiarycompose.data.Screen
import com.example.babydiarycompose.listener.ScreenTransitionListener
import com.example.babydiarycompose.ui.screen.AccountScreen
import com.example.babydiarycompose.ui.screen.OutputOfRecordScreen
import com.example.babydiarycompose.ui.screen.SearchOfRecordAndDiaryScreen
import com.example.babydiarycompose.ui.screen.SettingsScreen
import com.example.babydiarycompose.ui.theme.BabyDiaryComposeTheme
import com.example.babydiarycompose.ui.theme.Pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BabyDiaryApp() {
    BabyDiaryComposeTheme {
        val navController = rememberNavController()
        var isDisplayedNavigationBar by rememberSaveable { mutableStateOf(true) }
        val items = arrayListOf("記録", "まとめ", "成長曲線", "メニュー")
        var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color(0xFF1c1c1c),
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Column {
                            Text(
                                color = Color.White,
                                text = "めいたん👶0歳2か月13日",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                maxLines = 1, fontSize = 12.sp
                            )
                            Text(
                                color = Color(0xFFEC7786),
                                text = "2023/12/27(水)",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                painter = rememberVectorPainter(image = Icons.Default.ArrowBack),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                painter = rememberVectorPainter(image = Icons.Default.ArrowForward),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                )
            },
            bottomBar = {
                if (isDisplayedNavigationBar) {
                    NavigationBar(containerColor = Color(0xFF1c1c1c)) {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Pink,
                                    unselectedIconColor = Color.Gray,
                                    selectedTextColor = Color.White,
                                    indicatorColor = Color.White
                                ),
                                selected = selectedItemIndex == index,
                                onClick = {
                                    selectedItemIndex = index
                                    when (selectedItemIndex) {
                                        Screen.RECORD.index -> {
                                            navController.navigate(Screen.RECORD.route)
                                        }

                                        Screen.SUMMARY.index -> {
                                            navController.navigate(Screen.SUMMARY.route)
                                        }

                                        Screen.GROW.index -> {
                                            navController.navigate(Screen.GROW.route)
                                        }

                                        Screen.MENU.index -> {
                                            navController.navigate(Screen.MENU.route)
                                        }
                                    }
                                },
                                label = {
                                    Text(
                                        color = Color.White,
                                        text = item
                                    )
                                },
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            Badge()
                                        }
                                    ) {
                                        IconButton(onClick = {
                                            selectedItemIndex = index
                                            when (selectedItemIndex) {
                                                Screen.RECORD.index -> {
                                                    navController.navigate(Screen.RECORD.route)
                                                }

                                                Screen.SUMMARY.index -> {
                                                    navController.navigate(Screen.SUMMARY.route)
                                                }

                                                Screen.GROW.index -> {
                                                    navController.navigate(Screen.GROW.route)
                                                }

                                                Screen.MENU.index -> {
                                                    navController.navigate(Screen.MENU.route)
                                                }
                                            }
                                        }) {
                                            Icon(
                                                imageVector = when (index) {
                                                    0 ->
                                                        Icons.Default.Edit

                                                    1 ->
                                                        Icons.Default.Build

                                                    2 ->
                                                        Icons.Default.Info

                                                    else ->
                                                        Icons.Default.Menu
                                                },
                                                contentDescription = items[selectedItemIndex]
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.RECORD.route,
                modifier = Modifier.padding(innerPadding),
            ) {
                composable(Screen.RECORD.route) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        RecordingScreen()
                        isDisplayedNavigationBar = true
                    }
                }
                composable(Screen.SUMMARY.route) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        SummaryScreen()
                        isDisplayedNavigationBar = true
                    }
                }
                composable(Screen.GROW.route) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        GrowthCurveScreen()
                        isDisplayedNavigationBar = true
                    }
                }
                composable(Screen.MENU.route) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        val settingsTransitionListener = object : ScreenTransitionListener {
                            override fun transitionListener(screenName: String) {
                                navController.navigate(screenName)
                            }
                        }
                        MenuScreen(settingsTransitionListener)
                        isDisplayedNavigationBar = true
                    }
                }
                composable(Screen.SETTINGS.route) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        SettingsScreen()
                        isDisplayedNavigationBar = false
                    }
                }
                composable(Screen.SEARCH_OF_RECORD_AND_DIARY.route) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        SearchOfRecordAndDiaryScreen()
                        isDisplayedNavigationBar = false
                    }
                }
                composable(Screen.OUTPUT_OF_RECORD.route) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        OutputOfRecordScreen()
                        isDisplayedNavigationBar = false
                    }
                }
                composable(Screen.ACCOUNT.route) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        AccountScreen()
                        isDisplayedNavigationBar = false
                    }
                }
            }
        }
    }
}