package com.example.babydiarycompose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Modifier
import com.example.babydiarycompose.ui.theme.BabyDiaryComposeTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.babydiarycompose.compose.screen.MenuScreen
import com.example.babydiarycompose.compose.screen.RecordingScreen
import com.example.babydiarycompose.data.MenuOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BabyDiaryComposeTheme {
                val navController = rememberNavController()
                val items = arrayListOf("記録", "まとめ", "成長曲線", "メニュー")
                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }
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
                        NavigationBar(containerColor = Color(0xFF1c1c1c)) {
                            items.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = Color(0xFFEC7786),
//                                        unselectedIconColor = Color.Gray,
//                                        selectedTextColor = Color.White,
//                                        indicatorColor = Color.White
                                    ),
                                    selected = selectedItemIndex == index,
                                    onClick = {
                                        selectedItemIndex = index
                                        when (selectedItemIndex) {
                                            0 -> {
                                                navController.navigate(MenuOptions.HOME.route)
                                            }

                                            1 -> {
                                                navController.navigate(MenuOptions.COMPONENTS.route)
                                            }

                                            2 -> {
                                                navController.navigate(MenuOptions.ARTICLES.route)
                                            }

                                            else -> {
                                                navController.navigate(MenuOptions.SETTINGS.route)
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
                                            IconButton(onClick = {}) {
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
                                RecordingScreen()
                            }
                        }
                        composable("components") {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize(),
                            ) {
                            }
                        }
                        composable("articles") {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize(),
                            ) {
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