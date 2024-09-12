package com.example.babydiarycompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.babydiarycompose.compose.NavigationBottomBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BabyDiaryApp() {
    BabyDiaryComposeTheme {
        var isDisplayedNavigationBar by rememberSaveable { mutableStateOf(true) }
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                if (isDisplayedNavigationBar) {
                    NavigationBottomBar(navController)
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
                            override fun transitionListener(name: String) {
                                navController.navigate(name)
                            }
                        }
                        MenuScreen(screenTransitionListener = settingsTransitionListener)
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