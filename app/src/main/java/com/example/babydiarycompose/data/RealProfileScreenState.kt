package com.example.babydiarycompose.data

import android.content.Context
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.example.babydiarycompose.viewmodel.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.babydiarycompose.state.EventDialogState

class RealProfileScreenState(
    private val viewModel: ProfileViewModel,
    override val scaffoldState: ScaffoldState,
    private val coroutineScope: CoroutineScope,
    private val navController: NavHostController,
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner
) : EventDialogState {

    override val uiState: ProfileViewModel.UiState
        @Composable get() = viewModel.uiState.collectAsState().value

    init {
//        viewModel.uiEvent.collectOnLifecycle(coroutineScope, lifecycleOwner) {
//            when (it) {
//                is UserEntryViewModel.UiEvent.InvalidEntry -> {
//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = context.getString(R.string.message_invalid_entry)
//                    )
//                }
//                is UserEntryViewModel.UiEvent.Error -> {
//                    scaffoldState.snackbarHostState.showSnackbar(message = it.e.message)
//                }
//                is UserEntryViewModel.UiEvent.SaveComplete -> {
//                    navController.navigate("next_screen")
//                }
//            }
//        }
        navController.navigate("next_screen")
        viewModel.fetch()
    }

//    override fun onBackPressed {
//        navController.navigateUp()
//    }

    override fun enterVolume(volume: String) = viewModel.enterVolume(volume)
    override fun enterLastName(lastName: String) = viewModel.enterLastName(lastName)
    override fun onSaveClick() = viewModel.save()
}

@Composable
fun rememberProfileScreenState(
    viewModel: ProfileViewModel =  viewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = LocalNavController.current,
    context: Context = LocalContext.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
): EventDialogState = remember {
    RealProfileScreenState(viewModel, scaffoldState, coroutineScope, navController, context, lifecycleOwner)
}

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("CompositionLocal LocalNavController not present")
}