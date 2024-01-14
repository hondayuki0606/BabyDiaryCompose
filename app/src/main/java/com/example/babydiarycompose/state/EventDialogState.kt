package com.example.babydiarycompose.state

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import com.example.babydiarycompose.viewmodel.ProfileViewModel

interface EventDialogState {
    val uiState: ProfileViewModel.UiState
        @Composable get

    val scaffoldState: ScaffoldState

    fun onBackPressed() {}
    fun enterVolume(volume: String) {}
    fun enterLastName(lastName: String) {}
    fun onSaveClick() {}
}