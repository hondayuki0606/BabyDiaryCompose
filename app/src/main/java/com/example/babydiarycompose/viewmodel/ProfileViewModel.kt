package com.example.babydiarycompose.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.babydiarycompose.data.UnsplashPhoto
import com.example.babydiarycompose.data.UnsplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject internal constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: UnsplashRepository
) : ViewModel() {
    private var queryString: String? = savedStateHandle["plantName"]

    data class UiState(
        var volume: String = "",
        var lastName: String = "",
//        val loadingState: LoadingState = LoadingState.LOADED
    ) {
        companion object {
            val default = UiState()
        }
    }

    sealed interface UiEvent {
        data class Error(val e: Throwable) : UiEvent

        //        data class InvalidEntry(val e: InvalidCause) : UiEvent
        object SaveComplete : UiEvent
    }

    private val _plantPictures = MutableStateFlow<PagingData<UnsplashPhoto>?>(null)

    val plantPictures: Flow<PagingData<UnsplashPhoto>> get() = _plantPictures.filterNotNull()

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.default)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEvent: Channel<UiEvent> = Channel()
    val uiEvent: Flow<UiEvent> = _uiEvent.receiveAsFlow()

    fun fetch() {
        viewModelScope.launch {
            try {
                _plantPictures.value =
                    repository.getSearchResultStream(queryString ?: "").cachedIn(viewModelScope)
                        .first()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
//        useCase.fetch()
//            .onSuccess { user ->
//                _uiState.update { state ->
//                    state.copy(
//                        firstName = user.firstName,
//                        lastName = user.lastName
//                    )
//                }
//            }
//            .onFailure { e ->
//                _uiEvent.send(UiEvent.Error(e))
//            }
    }

    fun enterVolume(volume: String) {
        _uiState.value.volume = volume
//        _uiState.upddate { it.copy(firstName = volume) }
    }

    fun enterLastName(lastName: String) {
        _uiState.value.lastName = lastName
//        _uiState.upddate { it.copy(lastName = lastName) }
    }

    fun save() {
//        _uiState.update { it.copy(loadingState = LoadingState.LOADING) }
//
//        val uiState = _uiState.value
//        useCase.save(
//            firstName = uiState.firstName,
//            lastName = uiState.lastName
//        )
//            .onSuccess {
//                _uiState.update { it.copy(loadingState = LoadingState.LOADED) }
//                _uiEvent.send(UiEvent.SaveComplete)
//            }
//            .onFailure { e ->
//                _uiState.update { it.copy(loadingState = LoadingState.LOADED) }
//                _uiEvent.send(
//                    when (e) {
//                        is ProfileUseCaseError.InvalidEntry -> UiEvent.InvalidEntry(e.cause)
//                        else -> UiEvent.Error(e)
//                    }
//                )
//            }
    }
}