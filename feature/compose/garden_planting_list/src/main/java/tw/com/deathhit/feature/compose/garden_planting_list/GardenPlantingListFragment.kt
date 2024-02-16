package tw.com.deathhit.feature.compose.garden_planting_list

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
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import tw.com.deathhit.core.app_ui_compose.style.SunflowerCloneTheme

@AndroidEntryPoint
class GardenPlantingListFragment : Fragment() {
    var callback: Callback? = null

    private val viewModel: GardenPlantingListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            SunflowerCloneTheme {
                val plants = viewModel.gardenPlantingPagingDataFlow.collectAsLazyPagingItems()

                GardenPlantingListScreen(plants = plants, onPlantClick = {
                    viewModel.goToPlantDetailsScreen(it.plantId)
                })
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
                                    is GardenPlantingListViewModel.State.Action.GoToPlantDetailsScreen -> callback?.onGoToPlantDetailsScreen(
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

    companion object {
        fun create() = GardenPlantingListFragment()
    }

    interface Callback {
        fun onGoToPlantDetailsScreen(plantId: String)
    }
}