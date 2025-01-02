package tw.com.deathhit.sunflower_clone.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tw.com.deathhit.core.sunflower_clone_ui_compose.style.SunflowerCloneTheme
import tw.com.deathhit.feature.compose.gallery.GalleryDestination
import tw.com.deathhit.feature.compose.gallery.GalleryScreen
import tw.com.deathhit.feature.compose.navigation.NavigationDestination
import tw.com.deathhit.feature.compose.navigation.NavigationScreen
import tw.com.deathhit.feature.compose.plant_details.PlantDetailsDestination
import tw.com.deathhit.feature.compose.plant_details.PlantDetailsScreen
import tw.com.deathhit.sunflower_clone.compose.model.MainScreen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MainNavHost(navHostController = navController)

            val actions = viewModel.stateFlow.collectAsState().value.actions

            LaunchedEffect(actions) {
                actions.forEach { action ->
                    when (action) {
                        MainActivityViewModel.State.Action.GoBack -> onBackPressedDispatcher.onBackPressed()
                        is MainActivityViewModel.State.Action.GoToScreen -> when (action.screen) {
                            is MainScreen.Gallery -> navController.navigate(
                                galleryDestination.createLink(
                                    action.screen.plantName
                                )
                            )

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
    private fun MainNavHost(navHostController: NavHostController) {
        val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
            { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(300)) }
        val exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
            { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(300)) }

        SunflowerCloneTheme {
            NavHost(
                navController = navHostController,
                startDestination = navigationDestination.route,
                enterTransition = enterTransition,
                exitTransition = exitTransition,
                popEnterTransition = enterTransition,
                popExitTransition = exitTransition
            ) {
                composable(
                    galleryDestination.route,
                    arguments = GalleryDestination.args
                ) {
                    GalleryScreen(onGoBack = { viewModel.goBack() })
                }
                composable(navigationDestination.route) {
                    NavigationScreen(
                        onGoToPlantDetailsScreen = { viewModel.goToPlantDetailsScreen(plantId = it) }
                    )
                }
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
    }

    companion object {
        private val galleryDestination = GalleryDestination("Gallery")
        private val navigationDestination = NavigationDestination("Navigation")
        private val plantDetailsDestination = PlantDetailsDestination("PlantDetails")
    }
}