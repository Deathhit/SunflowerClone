package tw.com.deathhit.sunflower_clone

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.parcelize.Parcelize
import tw.com.deathhit.sunflower_clone.model.MainScreen
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) :
    ViewModel() {
    private val _stateFlow = MutableStateFlow(savedStateHandle[KEY_STATE] ?: State())
    val stateFlow = _stateFlow.asStateFlow()

    fun goBack() {
        _stateFlow.update { state ->
            state.copy(actions = state.actions + State.Action.GoBack)
        }
    }

    fun goToGalleryScreen(plantName: String) {
        _stateFlow.update { state ->
            state.copy(
                actions = state.actions + State.Action.GoToScreen(
                    screen = MainScreen.Gallery(
                        plantName = plantName
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

    fun saveState() {
        savedStateHandle[KEY_STATE] = stateFlow.value
    }

    companion object {
        private const val TAG = "MainActivityViewModel"
        private const val KEY_STATE = "$TAG.KEY_STATE"
    }

    @Parcelize
    data class State(val actions: List<Action> = emptyList()) : Parcelable {
        sealed interface Action : Parcelable {
            @Parcelize
            data object GoBack : Action

            @Parcelize
            data class GoToInitialScreen(val screen: MainScreen) : Action

            @Parcelize
            data class GoToScreen(val screen: MainScreen) : Action
        }
    }
}