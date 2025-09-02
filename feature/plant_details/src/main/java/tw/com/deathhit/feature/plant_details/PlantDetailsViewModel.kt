package tw.com.deathhit.feature.plant_details

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import tw.com.deathhit.domain.sunflower_clone.GardenPlantingRepository
import tw.com.deathhit.domain.sunflower_clone.PlantRepository
import tw.com.deathhit.feature.plant_details.sealed.ToastType
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PlantDetailsViewModel @Inject constructor(
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantRepository: PlantRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _stateFlow = MutableStateFlow<State>(savedStateHandle[KEY_STATE]!!)
    val stateFlow = _stateFlow.asStateFlow()

    private var state: State
        get() = stateFlow.value
        set(value) {
            _stateFlow.update { value }

            savedStateHandle[KEY_STATE] = value
        }

    val plantFlow =
        createPlantFlow().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun addPlantToGarden() {
        viewModelScope.launch {
            gardenPlantingRepository.addGardenPlanting(
                plantDate = System.currentTimeMillis(),
                plantId = stateFlow.value.plantId
            )

            state =
                state.copy(actions = state.actions + State.Action.Toast(ToastType.AddedPlantToGarden))
        }
    }

    fun goBack() {
        state = state.copy(actions = state.actions + State.Action.GoBack)
    }

    fun goToGalleryScreen() {
        viewModelScope.launch {
            val plant = createPlantFlow().first() ?: return@launch

            state = state.copy(
                actions = state.actions + State.Action.GoToGalleryScreen(
                    plantName = plant.plantName
                )
            )
        }
    }

    fun onAction(action: State.Action) {
        state = state.copy(actions = state.actions - action)
    }

    fun sharePlant() {
        viewModelScope.launch {
            val plant = createPlantFlow().first() ?: return@launch

            state =
                state.copy(actions = state.actions + State.Action.SharePlant(plantName = plant.plantName))
        }
    }

    private fun createPlantFlow() = stateFlow.map { it.plantId }.flatMapLatest {
        plantRepository.getPlantFlow(it)
    }

    companion object {
        private const val TAG = "PlantDetailsViewModel"
        private const val KEY_STATE = "$TAG.KEY_STATE"

        internal fun createArgs(plantId: String) = Bundle().apply {
            putParcelable(
                KEY_STATE, State(
                    plantId = plantId
                )
            )
        }
    }

    @Parcelize
    data class State(
        val actions: List<Action> = emptyList(),
        val plantId: String
    ) : Parcelable {
        sealed interface Action : Parcelable {
            @Parcelize
            data object GoBack : Action

            @Parcelize
            data class GoToGalleryScreen(val plantName: String) : Action

            @Parcelize
            data class SharePlant(val plantName: String) : Action

            @Parcelize
            data class Toast(val type: ToastType) : Action
        }
    }
}