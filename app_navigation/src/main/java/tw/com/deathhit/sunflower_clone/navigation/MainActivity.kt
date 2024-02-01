package tw.com.deathhit.sunflower_clone.navigation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import tw.com.deathhit.feature.gallery.GalleryFragment
import tw.com.deathhit.feature.navigation.NavigationFragment
import tw.com.deathhit.feature.plant_details.PlantDetailsFragment
import tw.com.deathhit.sunflower_clone.model.MainScreen
import tw.com.deathhit.sunflower_clone.navigation.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by viewModels()

    private val navController by lazy {
        findNavController(R.id.container_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        configureFragmentCallbacks()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        bindViewModelState()
    }

    private fun bindViewModelState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.map { it.actions }.distinctUntilChanged()
                    .collectLatest { actions ->
                        actions.forEach { action ->
                            when (action) {
                                MainActivityViewModel.State.Action.GoBack -> navController.popBackStack()
                                is MainActivityViewModel.State.Action.GoToScreen -> goToScreen(
                                    screen = action.screen
                                )
                            }

                            viewModel.onAction(action)
                        }
                    }
            }
        }
    }

    private fun configureFragmentCallbacks() {
        supportFragmentManager.addFragmentOnAttachListener { _, navHostFragment ->
            when (navHostFragment) {
                is NavHostFragment -> navHostFragment.childFragmentManager.addFragmentOnAttachListener { _, fragment ->
                    when (fragment) {
                        is GalleryFragment -> fragment.callback =
                            object : GalleryFragment.Callback {
                                override fun onGoBack() {
                                    viewModel.goBack()
                                }
                            }

                        is NavigationFragment -> fragment.callback =
                            object : NavigationFragment.Callback {
                                override fun onGoToPlantDetailsScreen(plantId: String) {
                                    viewModel.goToPlantDetailsScreen(plantId = plantId)
                                }
                            }

                        is PlantDetailsFragment -> fragment.callback =
                            object : PlantDetailsFragment.Callback {
                                override fun onGoBack() {
                                    viewModel.goBack()
                                }

                                override fun onGoToGalleryScreen(plantName: String) {
                                    viewModel.goToGalleryScreen(plantName = plantName)
                                }
                            }
                    }
                }
            }
        }
    }

    private fun goToScreen(screen: MainScreen) {
        when (screen) {
            is MainScreen.Gallery -> navController.navigate(
                R.id.action_gallery,
                GalleryFragment.createArgs(plantName = screen.plantName)
            )

            is MainScreen.PlantDetails -> navController.navigate(
                R.id.action_plantDetails,
                PlantDetailsFragment.createArgs(plantId = screen.plantId)
            )
        }
    }
}