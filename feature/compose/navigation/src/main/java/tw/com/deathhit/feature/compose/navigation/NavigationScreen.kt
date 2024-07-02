package tw.com.deathhit.feature.compose.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import tw.com.deathhit.feature.compose.garden_planting_list.GardenPlantingListScreen
import tw.com.deathhit.feature.compose.navigation.enum_type.NavigationPage
import tw.com.deathhit.feature.compose.plant_list.PlantListScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavigationScreen(
    onGoToPlantDetailsScreen: (plantId: String) -> Unit,
    viewModel: NavigationViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val pageList = listOf(NavigationPage.PLANT_LIST, NavigationPage.MY_GARDEN)

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

    NavigationLayout(
        myGardenPageView = {
            GardenPlantingListScreen(
                onGoToPlantDetailsScreen = { viewModel.goToPlantDetailsScreen(plantId = it) },
                viewModel = hiltViewModel()
            )
        },
        pageList = pageList,
        pagerState = rememberPagerState(pageCount = { pageList.size }),
        plantListPageView = {
            PlantListScreen(
                onGoToPlantDetailsScreen = { viewModel.goToPlantDetailsScreen(plantId = it) },
                viewModel = hiltViewModel()
            )
        },
        title = stringResource(id = context.applicationInfo.labelRes)
    )
}