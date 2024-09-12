package com.example.babydiarycompose.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.babydiarycompose.data.Screen
import com.example.babydiarycompose.ui.theme.Pink
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBottomBar(navController: NavHostController) {

    val bottomItems = arrayListOf("記録", "まとめ", "成長曲線", "メニュー")
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar(containerColor = Color(0xFF1c1c1c)) {
        bottomItems.forEachIndexed { index, item ->
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
                                contentDescription = bottomItems[selectedItemIndex]
                            )
                        }
                    }
                }
            )
        }
    }
}