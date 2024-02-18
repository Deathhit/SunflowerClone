package tw.com.deathhit.feature.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

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

    NavigationView(onGoToPlantDetailsScreen = { viewModel.goToPlantDetailsScreen(plantId = it) })
}