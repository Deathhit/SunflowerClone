package tw.com.deathhit.sunflower_clone

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import tw.com.deathhit.feature.gallery.GalleryFragment
import tw.com.deathhit.feature.navigation.NavigationFragment
import tw.com.deathhit.feature.plant_details.PlantDetailsFragment
import tw.com.deathhit.sunflower_clone.databinding.ActivityMainBinding
import tw.com.deathhit.sunflower_clone.model.MainScreen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        configureFragmentCallbacks()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        savedInstanceState ?: run {
            viewModel.goToInitialScreen()
        }

        bindViewModelState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveState()
        super.onSaveInstanceState(outState)
    }

    private fun bindViewModelState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.map { it.actions }.distinctUntilChanged()
                    .collectLatest { actions ->
                        actions.forEach { action ->
                            when (action) {
                                MainActivityViewModel.State.Action.GoBack -> onBackPressedDispatcher.onBackPressed()

                                is MainActivityViewModel.State.Action.GoToInitialScreen -> goToInitialScreen(
                                    screen = action.screen
                                )

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
        supportFragmentManager.addFragmentOnAttachListener { _, fragment ->
            when (fragment) {
                is GalleryFragment -> fragment.callback = object : GalleryFragment.Callback {
                    override fun onGoBack() {
                        viewModel.goBack()
                    }
                }

                is NavigationFragment -> fragment.callback = object : NavigationFragment.Callback {
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

    private fun goToInitialScreen(screen: MainScreen) {
        supportFragmentManager.commit {
            val containerId = binding.containerMain.id

            setReorderingAllowed(true)

            replace(containerId, screen.toFragment(), TAG_MAIN)
        }
    }

    private fun goToScreen(screen: MainScreen) {
        supportFragmentManager.commit {
            val containerId = binding.containerMain.id

            setReorderingAllowed(true)

            setCustomAnimations(
                tw.com.deathhit.core.app_ui.R.anim.slide_in_left,
                tw.com.deathhit.core.app_ui.R.anim.slide_out_right
            )

            replace(containerId, screen.toFragment(), TAG_MAIN)

            addToBackStack(null)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val TAG_MAIN = "$TAG.TAG_MAIN"

        private fun MainScreen.toFragment(): Fragment = when (this) {
            is MainScreen.Gallery -> GalleryFragment.create(plantName = plantName)
            MainScreen.Navigation -> NavigationFragment.create()
            is MainScreen.PlantDetails -> PlantDetailsFragment.create(plantId = plantId)
        }
    }
}