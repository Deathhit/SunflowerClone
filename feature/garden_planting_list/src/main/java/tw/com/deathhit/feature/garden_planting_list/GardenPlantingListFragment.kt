package tw.com.deathhit.feature.garden_planting_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import tw.com.deathhit.feature.garden_planting_list.databinding.FragmentGardenPlantingListBinding

@AndroidEntryPoint
class GardenPlantingListFragment : Fragment() {
    var callback: Callback? = null

    private val binding get() = _binding!!
    private var _binding: FragmentGardenPlantingListBinding? = null

    private val viewModel: GardenPlantingListViewModel by viewModels()

    private val gardenPlantingAdapter get() = _gardenPlantingAdapter!!
    private var _gardenPlantingAdapter: GardenPlantingAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentGardenPlantingListBinding.inflate(inflater, container, false)
        .also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.recyclerView) {
            adapter = createGardenPlantingAdapter().also { _gardenPlantingAdapter = it }
            setHasFixedSize(true)
        }

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

                launch {
                    viewModel.gardenPlantingPagingDataFlow.collectLatest {
                        gardenPlantingAdapter.submitData(it)
                    }
                }
            }
        }
    }

    private fun createGardenPlantingAdapter() =
        GardenPlantingAdapter(glideRequestManager = Glide.with(this)) {
            viewModel.goToPlantDetailsScreen(it.plantId)
        }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null

        _binding = null
        _gardenPlantingAdapter = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveState()
        super.onSaveInstanceState(outState)
    }

    companion object {
        fun create() = GardenPlantingListFragment()
    }

    interface Callback {
        fun onGoToPlantDetailsScreen(plantId: String)
    }
}