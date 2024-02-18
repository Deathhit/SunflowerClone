package tw.com.deathhit.feature.compose.garden_planting_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun GardenPlantingListScreen(
    onGoToPlantDetailsScreen: (plantId: String) -> Unit,
    viewModel: GardenPlantingListViewModel = hiltViewModel()
) {
    val actions = viewModel.stateFlow.collectAsState().value.actions

    LaunchedEffect(actions) {
        actions.forEach { action ->
            when (action) {
                is GardenPlantingListViewModel.State.Action.GoToPlantDetailsScreen -> onGoToPlantDetailsScreen(
                    action.plantId
                )
            }

            viewModel.onAction(action)
        }
    }

    GardenPlantingListView(
        plants = viewModel.gardenPlantingPagingDataFlow.collectAsLazyPagingItems(),
        onPlantClick = { viewModel.goToPlantDetailsScreen(plantId = it.plantId) }
    )
}