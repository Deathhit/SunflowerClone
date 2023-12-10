package tw.com.deathhit.feature.plant_details

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ShareCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import tw.com.deathhit.feature.plant_details.databinding.FragmentPlantDetailsBinding

@AndroidEntryPoint
class PlantDetailsFragment : Fragment() {
    var callback: Callback? = null

    private val binding get() = _binding!!
    private var _binding: FragmentPlantDetailsBinding? = null

    private val viewModel: PlantDetailsViewModel by viewModels()

    private val onAddPlantToGardenListener = View.OnClickListener {
        viewModel.addPlantToGarden()
    }
    private val onGoBackListener = View.OnClickListener {
        viewModel.goBack()
    }
    private val onGoToGalleryScreenListener = View.OnClickListener {
        viewModel.goToGalleryScreen()
    }
    private val onClickMenuItemListener = Toolbar.OnMenuItemClickListener { item ->
        when (item.itemId) {
            R.id.action_share -> {
                viewModel.sharePlant()

                true
            }

            else -> false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentPlantDetailsBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.textViewDescription) {
            movementMethod = LinkMovementMethod.getInstance()
        }

        bindViewModelState()
    }

    override fun onResume() {
        super.onResume()
        with(binding) {
            fab.setOnClickListener(onAddPlantToGardenListener)
            imageViewGoToGallery.setOnClickListener(onGoToGalleryScreenListener)
            toolbar.setNavigationOnClickListener(onGoBackListener)
            toolbar.setOnMenuItemClickListener(onClickMenuItemListener)
        }
    }

    override fun onPause() {
        super.onPause()
        with(binding) {
            fab.setOnClickListener(null)
            imageViewGoToGallery.setOnClickListener(null)
            toolbar.setNavigationOnClickListener(null)
            toolbar.setOnMenuItemClickListener(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveState()
        super.onSaveInstanceState(outState)
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

                launch {
                    viewModel.plantFlow.filterNotNull().distinctUntilChanged()
                        .collectLatest { plant ->
                            with(binding) {
                                fab.isVisible = !plant.isPlanted
                                imageViewPlant.let {
                                    Glide.with(it).load(plant.imageUrl).into(it)
                                }
                                textViewDescription.text = Html.fromHtml(
                                    plant.description,
                                    Html.FROM_HTML_MODE_LEGACY
                                )
                                textViewTitle.text = plant.plantName
                                textViewWateringValue.text = getString(
                                    R.string.plant_details_every_x_days,
                                    plant.wateringIntervalDays
                                )
                                toolbarLayout.title = plant.plantName
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