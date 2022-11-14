package com.sema.automauto.ui.view.image

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
) : ViewModel() {

    val state = mutableStateOf<ImagesUiState>(Loading)

    init {
        val item = stateHandle.get<String>("item")
        item?.let {
            getImages(listOf(it))
        }
    }

    private fun getImages(images: List<String>) {
        viewModelScope.launch {
            state.value = ImagesUiStateReady(images = images)
        }
    }
}

sealed class ImagesUiState
data class ImagesUiStateReady(val images: List<String>) : ImagesUiState()
object Loading : ImagesUiState()
class ImagesUiStateError(val error: String? = null) : ImagesUiState()