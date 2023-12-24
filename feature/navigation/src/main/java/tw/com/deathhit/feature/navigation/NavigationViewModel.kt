package tw.com.deathhit.feature.navigation

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) :
    ViewModel() {
    private var state: State
        get() = savedStateHandle[KEY_STATE] ?: State()
        set(value) {
            savedStateHandle[KEY_STATE] = value
        }
    val stateFlow = savedStateHandle.getStateFlow(KEY_STATE, state)

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