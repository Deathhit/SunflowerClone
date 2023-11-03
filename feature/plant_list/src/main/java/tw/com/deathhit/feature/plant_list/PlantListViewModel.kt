package tw.com.deathhit.feature.plant_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import tw.com.deathhit.domain.PlantRepository
import javax.inject.Inject

@HiltViewModel
class PlantListViewModel @Inject constructor(private val plantRepository: PlantRepository) :
    ViewModel() {
    private val _stateFlow = MutableStateFlow(State(actions = emptyList()))
    val stateFlow = _stateFlow.asStateFlow()

    val plantPagingDataFlow = createPlantPagingDataFlow().cachedIn(viewModelScope)

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

    private fun createPlantPagingDataFlow() = plantRepository.getPlantPagingDataFlow()

    data class State(val actions: List<Action>) {
        sealed interface Action {
            data class GoToPlantDetailsScreen(val plantId: String) : Action
        }
    }
}