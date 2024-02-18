package tw.com.deathhit.feature.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import tw.com.deathhit.feature.compose.garden_planting_list.GardenPlantingListScreen
import tw.com.deathhit.feature.compose.plant_list.PlantListScreen

@Composable
fun NavigationScreen(
    onGoToPlantDetailsScreen: (plantId: String) -> Unit,
    viewModel: NavigationViewModel = hiltViewModel()
) {
    val actions = viewModel.stateFlow.collectAsState().value.actions

    LaunchedEffect(actions) {
        actions.forEach { action ->
            when (action) {
                is NavigationViewModel.State.Action.GoToPlantDetailsScreen -> onGoToPlantDetailsScreen(
                    action.plantId
                )
            }

            viewModel.onAction(action)
        }
    }

    NavigationView(
        myGardenPageView = {
            GardenPlantingListScreen(
                onGoToPlantDetailsScreen = { viewModel.goToPlantDetailsScreen(plantId = it) },
                viewModel = hiltViewModel(key = "MyGardenPageView")
            )
        },
        plantListPageView = {
            PlantListScreen(
                onGoToPlantDetailsScreen = { viewModel.goToPlantDetailsScreen(plantId = it) },
                viewModel = hiltViewModel(key = "PlantListPageView")
            )
        }
    )
}