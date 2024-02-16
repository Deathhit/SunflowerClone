package tw.com.deathhit.feature.compose.plant_list

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
class PlantListFragment : Fragment() {
    var callback: Callback? = null

    private val viewModel: PlantListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            SunflowerCloneTheme {
                val plants = viewModel.plantPagingDataFlow.collectAsLazyPagingItems()

                PlantListScreen(plants = plants, onPlantClick = {
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
                                    is PlantListViewModel.State.Action.GoToPlantDetailsScreen -> callback?.onGoToPlantDetailsScreen(
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
        fun create() = PlantListFragment()
    }

    interface Callback {
        fun onGoToPlantDetailsScreen(plantId: String)
    }
}