package tw.com.deathhit.feature.navigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import tw.com.deathhit.feature.garden_planting_list.GardenPlantingListFragment
import tw.com.deathhit.feature.navigation.databinding.FragmentNavigationBinding
import tw.com.deathhit.feature.plant_list.PlantListFragment

@AndroidEntryPoint
class NavigationFragment : Fragment() {
    var callback: Callback? = null

    private val binding get() = _binding!!
    private var _binding: FragmentNavigationBinding? = null

    private val viewModel: NavigationViewModel by viewModels()

    private val tabLayoutMediator get() = _tabLayoutMediator!!
    private var _tabLayoutMediator: TabLayoutMediator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        configureFragmentCallbacks()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentNavigationBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.toolbar) {
            title = getString(context.applicationInfo.labelRes)
        }

        with(binding.viewPager) {
            adapter = createNavigationTabAdapter()
        }

        configureTabLayoutAndViewPager()

        bindViewModelState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabLayoutMediator.detach()

        binding.viewPager.adapter = null

        _binding = null
        _tabLayoutMediator = null
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

    private fun configureTabLayoutAndViewPager() {
        with(binding) {
            _tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    POS_MY_GARDEN -> {
                        tab.setIcon(tw.com.deathhit.core.sunflower_clone_ui.R.drawable.garden_tab_selector)
                        tab.text = getString(R.string.navigation_tab_my_garden)
                    }

                    POS_PLANT_LIST -> {
                        tab.setIcon(tw.com.deathhit.core.sunflower_clone_ui.R.drawable.plant_list_tab_selector)
                        tab.text = getString(R.string.navigation_tab_plant_list)
                    }
                }
            }.apply {
                attach()
            }
        }
    }

    private fun createNavigationTabAdapter() = NavigationTabAdapter(childFragmentManager, lifecycle)

    companion object {
        fun create() = NavigationFragment()
    }

    interface Callback {
        fun onGoToPlantDetailsScreen(plantId: String)
    }
}