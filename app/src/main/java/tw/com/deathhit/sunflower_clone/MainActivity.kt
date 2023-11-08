package tw.com.deathhit.sunflower_clone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
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
import tw.com.deathhit.feature.garden_planting_list.GardenPlantingListFragment
import tw.com.deathhit.feature.plant_list.PlantListFragment
import tw.com.deathhit.sunflower_clone.databinding.ActivityMainBinding
import tw.com.deathhit.sunflower_clone.model.MainScreen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        savedInstanceState ?: run {
            viewModel.goToInitialScreen()
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

        private fun MainScreen.toFragment(): Fragment = GardenPlantingListFragment.create()   //todo assign real fragment
    }
}