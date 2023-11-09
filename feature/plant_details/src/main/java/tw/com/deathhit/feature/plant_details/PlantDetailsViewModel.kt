package tw.com.deathhit.feature.plant_details

import android.os.Bundle
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
import tw.com.deathhit.domain.GardenPlantingRepository
import tw.com.deathhit.domain.PlantRepository
import tw.com.deathhit.feature.plant_details.sealed.ToastType
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PlantDetailsViewModel @Inject constructor(
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantRepository: PlantRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(
        State(
            actions = emptyList(),
            plantId = savedStateHandle[KEY_PLANT_ID]!!
        )
    )
    val stateFlow = _stateFlow.asStateFlow()

    val plantFlow =
        createPlantFlow().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun addPlantToGarden() {
        viewModelScope.launch {
            gardenPlantingRepository.addGardenPlanting(plantId = stateFlow.value.plantId)

            _stateFlow.update { state ->
                state.copy(actions = state.actions + State.Action.Toast(ToastType.AddedPlantToGarden))
            }
        }
    }

    fun goBack() {
        _stateFlow.update { state ->
            state.copy(actions = state.actions + State.Action.GoBack)
        }
    }

    fun goToGalleryScreen() {
        _stateFlow.update { state ->
            state.copy(actions = state.actions + State.Action.GoToGalleryScreen(plantId = state.plantId))
        }
    }

    fun onAction(action: State.Action) {
        _stateFlow.update { state ->
            state.copy(actions = state.actions - action)
        }
    }

    fun saveState() {
        with(stateFlow.value) {
            savedStateHandle[KEY_PLANT_ID] = plantId
        }
    }

    fun sharePlant() {
        viewModelScope.launch {
            val plant = createPlantFlow().first() ?: return@launch

            _stateFlow.update { state ->
                state.copy(actions = state.actions + State.Action.SharePlant(plantName = plant.plantName))
            }
        }
    }

    private fun createPlantFlow() = stateFlow.map { it.plantId }.flatMapLatest {
        plantRepository.getPlantFlow(it)
    }

    companion object {
        private const val TAG = "PlantDetailsViewModel"
        private const val KEY_PLANT_ID = "$TAG.KEY_PLANT_ID"

        internal fun createArgs(plantId: String) = Bundle().apply {
            putString(KEY_PLANT_ID, plantId)
        }
    }

    data class State(val actions: List<Action>, val plantId: String) {
        sealed interface Action {
            data object GoBack : Action
            data class GoToGalleryScreen(val plantId: String) : Action
            data class SharePlant(val plantName: String) : Action
            data class Toast(val type: ToastType) : Action
        }
    }
}