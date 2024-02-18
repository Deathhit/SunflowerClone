package tw.com.deathhit.feature.compose.plant_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun PlantListScreen(
    onGoToPlantDetailsScreen: (plantId: String) -> Unit,
    viewModel: PlantListViewModel = hiltViewModel()
) {
    val actions = viewModel.stateFlow.collectAsState().value.actions

    LaunchedEffect(actions) {
        actions.forEach { action ->
            when (action) {
                is PlantListViewModel.State.Action.GoToPlantDetailsScreen -> onGoToPlantDetailsScreen(
                    action.plantId
                )
            }

            viewModel.onAction(action)
        }
    }

    PlantListView(
        plants = viewModel.plantPagingDataFlow.collectAsLazyPagingItems(),
        onPlantClick = { viewModel.goToPlantDetailsScreen(plantId = it.plantId) }
    )
}