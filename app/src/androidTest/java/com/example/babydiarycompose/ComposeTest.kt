package com.example.babydiarycompose

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ComposeTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun test(){

    }
}