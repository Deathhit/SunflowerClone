package tw.com.deathhit.sunflower_clone.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tw.com.deathhit.feature.compose.plant_details.PlantDetailsDestination
import tw.com.deathhit.feature.compose.plant_details.PlantDetailsScreen
import tw.com.deathhit.sunflower_clone.compose.model.MainScreen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: MainActivityViewModel = hiltViewModel()

            MainNavHost(navHostController = navController, viewModel = viewModel)

            val actions = viewModel.stateFlow.collectAsState().value.actions

            LaunchedEffect(actions) {
                actions.forEach { action ->
                    when (action) {
                        MainActivityViewModel.State.Action.GoBack -> onBackPressedDispatcher.onBackPressed()
                        is MainActivityViewModel.State.Action.GoToScreen -> when (action.screen) {
                            is MainScreen.Gallery -> TODO()
                            is MainScreen.PlantDetails -> navController.navigate(
                                plantDetailsDestination.createLink(plantId = action.screen.plantId)
                            )
                        }
                    }

                    viewModel.onAction(action)
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun MainNavHost(
        navHostController: NavHostController,
        viewModel: MainActivityViewModel
    ) {
        NavHost(
            navController = navHostController,
            startDestination = plantDetailsDestination.route    //TODO test
        ) {
            NavDestination
            composable(
                plantDetailsDestination.route,
                arguments = PlantDetailsDestination.args
            ) {
                PlantDetailsScreen(
                    onGoBack = { viewModel.goBack() },
                    onGoToGalleryScreen = { viewModel.goToGalleryScreen(plantName = it) })
            }
        }
    }

    companion object {
        private val plantDetailsDestination = PlantDetailsDestination("PlantDetails")
    }
}