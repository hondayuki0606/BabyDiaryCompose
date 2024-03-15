package com.example.babydiarycompose

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.babydiarycompose.ui.screen.RecordingScreen
import com.example.babydiarycompose.viewmodel.RecordingViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ComposeTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: RecordingViewModel

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testMyComposable() {
        composeTestRule.setContent {
            viewModel = hiltViewModel()
            RecordingScreen(viewModel)
        }
    }
}