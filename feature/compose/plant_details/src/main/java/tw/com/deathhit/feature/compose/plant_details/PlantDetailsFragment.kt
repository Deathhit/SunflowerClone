package tw.com.deathhit.feature.compose.plant_details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.app.ShareCompat
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

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class PlantDetailsFragment : Fragment() {
    var callback: Callback? = null

    private val viewModel: PlantDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            SunflowerCloneTheme {
                val plant = viewModel.plantFlow.collectAsState().value

                if (plant != null)
                    PlantDetailsScreen(
                        description = plant.description,
                        isPlanted = plant.isPlanted,
                        plantImageUrl = plant.imageUrl,
                        plantName = plant.plantName,
                        wateringInterval = plant.wateringIntervalDays,
                        onBackClick = { viewModel.goBack() },
                        onFabClick = { viewModel.addPlantToGarden() },
                        onGalleryClick = { viewModel.goToGalleryScreen() },
                        onShareClick = { viewModel.sharePlant() })
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
                                sequenceOf(
                                    when (action) {
                                        is PlantDetailsViewModel.State.Action.GoBack -> callback?.onGoBack()
                                        is PlantDetailsViewModel.State.Action.GoToGalleryScreen -> callback?.onGoToGalleryScreen(
                                            plantName = action.plantName
                                        )

                                        is PlantDetailsViewModel.State.Action.SharePlant -> sharePlant(
                                            plantName = action.plantName
                                        )

                                        is PlantDetailsViewModel.State.Action.Toast -> Toast.makeText(
                                            requireContext(),
                                            action.type.getMessage(requireContext()),
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                )

                                viewModel.onAction(action)
                            }
                        }
                }
            }
        }
    }

    private fun sharePlant(plantName: String) {
        startActivity(
            ShareCompat.IntentBuilder(requireContext())
                .setText(getString(R.string.plant_details_share_message_x, plantName))
                .setType("text/plain")
                .createChooserIntent()
                .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        )
    }

    companion object {
        fun create(plantId: String) = PlantDetailsFragment().apply {
            arguments = PlantDetailsViewModel.createArgs(plantId = plantId)
        }
    }

    interface Callback {
        fun onGoBack()
        fun onGoToGalleryScreen(plantName: String)
    }
}