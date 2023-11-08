package tw.com.deathhit.feature.garden_planting_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import tw.com.deathhit.domain.GardenPlantingRepository
import javax.inject.Inject

@HiltViewModel
class GardenPlantingListViewModel @Inject constructor(
    private val gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(State(actions = emptyList()))
    val stateFlow = _stateFlow.asStateFlow()

    val gardenPlantingPagingDataFlow = createGardenPlantingPagingDataFlow().cachedIn(viewModelScope)

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

    private fun createGardenPlantingPagingDataFlow() =
        gardenPlantingRepository.getGardenPlantingPagingDataFlow()

    data class State(val actions: List<Action>) {
        sealed interface Action {
            data class GoToPlantDetailsScreen(val plantId: String) : Action
        }
    }
}