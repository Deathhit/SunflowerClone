package tw.com.deathhit.sunflower_clone.compose

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.parcelize.Parcelize
import tw.com.deathhit.sunflower_clone.model.MainScreen
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) :
    ViewModel() {
    private var state: State
        get() = savedStateHandle[KEY_STATE] ?: State()
        set(value) {
            savedStateHandle[KEY_STATE] = value
        }
    val stateFlow = savedStateHandle.getStateFlow(KEY_STATE, state)

    fun goBack() {
        state = state.copy(actions = state.actions + State.Action.GoBack)
    }

    fun goToGalleryScreen(plantName: String) {
        state = state.copy(
            actions = state.actions + State.Action.GoToScreen(
                screen = MainScreen.Gallery(
                    plantName = plantName
                )
            )
        )
    }

    fun goToInitialScreen() {
        state = state.copy(
            actions = state.actions + State.Action.GoToInitialScreen(screen = MainScreen.Navigation)
        )
    }

    fun goToPlantDetailsScreen(plantId: String) {
        state = state.copy(
            actions = state.actions + State.Action.GoToScreen(
                screen = MainScreen.PlantDetails(
                    plantId = plantId
                )
            )
        )
    }

    fun onAction(action: State.Action) {
        state = state.copy(actions = state.actions - action)
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