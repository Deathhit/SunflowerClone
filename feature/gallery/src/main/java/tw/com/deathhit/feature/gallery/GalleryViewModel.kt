package tw.com.deathhit.feature.gallery

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import tw.com.deathhit.domain.PhotoRepository
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _stateFlow =
        MutableStateFlow(
            State(
                actions = emptyList(),
                plantName = savedStateHandle[KEY_PLANT_NAME]!!
            )
        )
    val stateFlow = _stateFlow.asStateFlow()

    val photoPagingDataFlow = createPhotoPagingDataFlow().cachedIn(viewModelScope)

    fun goBack() {
        _stateFlow.update { state ->
            state.copy(actions = state.actions + State.Action.GoBack)
        }
    }

    fun onAction(action: State.Action) {
        _stateFlow.update { state ->
            state.copy(actions = state.actions - action)
        }
    }

    fun openWebLink(url: String) {
        _stateFlow.update { state ->
            state.copy(actions = state.actions + State.Action.OpenWebLink(url = url))
        }
    }

    fun saveState() {
        with(stateFlow.value) {
            savedStateHandle[KEY_PLANT_NAME] = plantName
        }
    }

    private fun createPhotoPagingDataFlow() =
        stateFlow.map { it.plantName }.flatMapLatest { plantId ->
            photoRepository.getPhotoPagingDataFlow(plantId)
        }

    companion object {
        private const val TAG = "GalleryViewModel"
        private const val KEY_PLANT_NAME = "$TAG.KEY_PLANT_NAME"

        internal fun createArgs(plantName: String) = Bundle().apply {
            putString(KEY_PLANT_NAME, plantName)
        }
    }

    data class State(val actions: List<Action>, val plantName: String) {
        sealed interface Action {
            data object GoBack : Action
            data class OpenWebLink(val url: String) : Action
        }
    }
}