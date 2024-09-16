package tw.com.deathhit.feature.navigation

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) :
    ViewModel() {
    private val _stateFlow = MutableStateFlow(savedStateHandle[KEY_STATE] ?: State())
    val stateFlow = _stateFlow.asStateFlow()

    private var state: State
        get() = stateFlow.value
        set(value) {
            _stateFlow.update { value }

            savedStateHandle[KEY_STATE] = value
        }

    fun goToPlantDetailsScreen(plantId: String) {
        state =
            state.copy(actions = state.actions + State.Action.GoToPlantDetailsScreen(plantId = plantId))
    }

    fun onAction(action: State.Action) {
        state = state.copy(actions = state.actions - action)
    }

    companion object {
        private const val TAG = "NavigationViewModel"
        private const val KEY_STATE = "$TAG.KEY_STATE"
    }

    @Parcelize
    data class State(val actions: List<Action> = emptyList()) : Parcelable {
        sealed interface Action : Parcelable {
            @Parcelize
            data class GoToPlantDetailsScreen(val plantId: String) : Action
        }
    }
}