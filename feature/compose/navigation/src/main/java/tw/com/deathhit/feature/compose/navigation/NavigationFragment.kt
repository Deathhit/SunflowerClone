package tw.com.deathhit.feature.compose.navigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import tw.com.deathhit.core.app_ui_compose.style.SunflowerCloneTheme
import tw.com.deathhit.feature.compose.garden_planting_list.GardenPlantingListFragment
import tw.com.deathhit.feature.compose.plant_list.PlantListFragment

@AndroidEntryPoint
class NavigationFragment : Fragment() {
    var callback: Callback? = null

    private val viewModel: NavigationViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        configureFragmentCallbacks()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                SunflowerCloneTheme {
                    NavigationScreen()
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModelState()
    }

    private fun bindViewModelState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.stateFlow.map { it.actions }.distinctUntilChanged()
                        .collectLatest { actions ->
                            actions.forEach { action ->
                                when (action) {
                                    is NavigationViewModel.State.Action.GoToPlantDetailsScreen -> callback?.onGoToPlantDetailsScreen(
                                        action.plantId
                                    )
                                }

                                viewModel.onAction(action)
                            }
                        }
                }
            }
        }
    }

    private fun configureFragmentCallbacks() {
        childFragmentManager.addFragmentOnAttachListener { _, fragment ->
            when (fragment) {
                is GardenPlantingListFragment -> fragment.callback =
                    object : GardenPlantingListFragment.Callback {
                        override fun onGoToPlantDetailsScreen(plantId: String) {
                            viewModel.goToPlantDetailsScreen(plantId)
                        }
                    }

                is PlantListFragment -> fragment.callback = object : PlantListFragment.Callback {
                    override fun onGoToPlantDetailsScreen(plantId: String) {
                        viewModel.goToPlantDetailsScreen(plantId)
                    }
                }
            }
        }
    }

    companion object {
        fun create() = NavigationFragment()
    }

    interface Callback {
        fun onGoToPlantDetailsScreen(plantId: String)
    }
}