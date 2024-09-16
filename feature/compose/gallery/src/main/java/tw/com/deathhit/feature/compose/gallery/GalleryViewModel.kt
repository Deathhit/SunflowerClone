package tw.com.deathhit.feature.compose.gallery

import android.os.Bundle
import android.os.Parcelable
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
import kotlinx.parcelize.Parcelize
import tw.com.deathhit.domain.PhotoRepository
import tw.com.deathhit.feature.compose.gallery.GalleryDestination.Companion.plantName
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(State(plantName = savedStateHandle.plantName))
    val stateFlow = _stateFlow.asStateFlow()

    private var state: State
        get() = stateFlow.value
        set(value) {
            _stateFlow.update { value }

            savedStateHandle[KEY_STATE] = value
        }

    val photoPagingDataFlow = createPhotoPagingDataFlow().cachedIn(viewModelScope)

    fun goBack() {
        state = state.copy(actions = state.actions + State.Action.GoBack)
    }

    fun onAction(action: State.Action) {
        state = state.copy(actions = state.actions - action)
    }

    fun openWebLink(url: String) {
        state = state.copy(actions = state.actions + State.Action.OpenWebLink(url = url))
    }

    private fun createPhotoPagingDataFlow() =
        stateFlow.map { it.plantName }.flatMapLatest { plantId ->
            photoRepository.getPhotoPagingDataFlow(plantId)
        }

    companion object {
        private const val TAG = "GalleryViewModel"
        private const val KEY_STATE = "$TAG.KEY_STATE"

        internal fun createArgs(plantName: String) = Bundle().apply {
            putParcelable(KEY_STATE, State(plantName = plantName))
        }
    }

    @Parcelize
    data class State(val actions: List<Action> = emptyList(), val plantName: String) : Parcelable {
        sealed interface Action : Parcelable {
            @Parcelize
            data object GoBack : Action

            @Parcelize
            data class OpenWebLink(val url: String) : Action
        }
    }
}