package com.example.babydiarycompose

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.babydiarycompose.ui.screen.RecordingScreen
import com.example.babydiarycompose.viewmodel.RecordingViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
class ComposeTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

//    private lateinit var viewModel: RecordingViewModel

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testMyComposable() {
//        composeTestRule.setContent {
////            viewModel = hiltViewModel()
////            RecordingScreen(viewModel)
//        }
    }
}