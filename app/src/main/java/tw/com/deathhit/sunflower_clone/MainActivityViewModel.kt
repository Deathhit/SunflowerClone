package tw.com.deathhit.sunflower_clone

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import tw.com.deathhit.sunflower_clone.model.MainScreen
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {
    private val _stateFlow = MutableStateFlow(State(actions = emptyList()))
    val stateFlow = _stateFlow.asStateFlow()

    fun goToGallery(plantId: String) {
        _stateFlow.update { state ->
            state.copy(
                actions = state.actions + State.Action.GoToScreen(
                    screen = MainScreen.Gallery(
                        plantId = plantId
                    )
                )
            )
        }
    }

    fun goToInitialScreen() {
        _stateFlow.update { state ->
            state.copy(
                actions = state.actions + State.Action.GoToInitialScreen(screen = MainScreen.Navigation)
            )
        }
    }

    fun goToPlantDetailsScreen(plantId: String) {
        _stateFlow.update { state ->
            state.copy(
                actions = state.actions + State.Action.GoToScreen(
                    screen = MainScreen.PlantDetails(
                        plantId = plantId
                    )
                )
            )
        }
    }

    fun onAction(action: State.Action) {
        _stateFlow.update { state ->
            state.copy(actions = state.actions - action)
        }
    }

    data class State(val actions: List<Action>) {
        sealed interface Action {
            data class GoToInitialScreen(val screen: MainScreen) : Action
            data class GoToScreen(val screen: MainScreen) : Action
        }
    }
}