package tw.com.deathhit.feature.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {
    private val _stateFlow = MutableStateFlow(State(actions = emptyList()))
    val stateFlow = _stateFlow.asStateFlow()

    fun goToPlantDetailsScreen(plantId: String) {
        _stateFlow.update { state ->
            state.copy(actions = state.actions + State.Action.GoToPlantDetailsScreen(plantId = plantId))
        }
    }

    fun onAction(action: State.Action) {
        _stateFlow.update { state ->
            state.copy(actions = state.actions - action)
        }
    }

    data class State(val actions: List<Action>) {
        sealed interface Action {
            data class GoToPlantDetailsScreen(val plantId: String) : Action
        }
    }
}