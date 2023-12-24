package tw.com.deathhit.feature.plant_list

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
import tw.com.deathhit.feature.plant_list.databinding.FragmentPlantListBinding

@AndroidEntryPoint
class PlantListFragment : Fragment() {
    var callback: Callback? = null

    private val binding get() = _binding!!
    private var _binding: FragmentPlantListBinding? = null

    private val viewModel: PlantListViewModel by viewModels()

    private val plantAdapter get() = _plantAdapter!!
    private var _plantAdapter: PlantAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentPlantListBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.recyclerView) {
            adapter = createPlantAdapter().also { _plantAdapter = it }
            setHasFixedSize(true)
        }

        bindViewModelState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null

        _binding = null
        _plantAdapter = null
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

                launch {
                    viewModel.plantPagingDataFlow.collectLatest {
                        plantAdapter.submitData(it)
                    }
                }
            }
        }
    }

    private fun createPlantAdapter() = PlantAdapter(glideRequestManager = Glide.with(this)) {
        viewModel.goToPlantDetailsScreen(it.plantId)
    }

    companion object {
        fun create() = PlantListFragment()
    }

    interface Callback {
        fun onGoToPlantDetailsScreen(plantId: String)
    }
}