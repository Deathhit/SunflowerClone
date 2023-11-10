package tw.com.deathhit.feature.gallery

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import tw.com.deathhit.feature.gallery.databinding.FragmentGalleryBinding

@AndroidEntryPoint
class GalleryFragment : Fragment() {
    var callback: Callback? = null

    private val binding get() = _binding!!
    private var _binding: FragmentGalleryBinding? = null

    private val viewModel: GalleryViewModel by viewModels()

    private val photoAdapter get() = _photoAdapter!!
    private var _photoAdapter: PhotoAdapter? = null

    private val onGoBackListener = View.OnClickListener {
        viewModel.goBack()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentGalleryBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.recyclerView) {
            adapter = createPhotoAdapter().also { _photoAdapter = it }
            setHasFixedSize(true)
        }

        bindViewModelState()
    }

    override fun onResume() {
        super.onResume()
        with(binding) {
            toolbar.setNavigationOnClickListener(onGoBackListener)
        }
    }

    override fun onPause() {
        super.onPause()
        with(binding) {
            toolbar.setNavigationOnClickListener(null)
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
                                when (action) {
                                    GalleryViewModel.State.Action.GoBack -> callback?.onGoBack()
                                    is GalleryViewModel.State.Action.OpenWebLink -> openWebLink(url = action.url)

                                }

                                viewModel.onAction(action)
                            }
                        }
                }

                launch {
                    viewModel.photoPagingDataFlow.collectLatest {
                        photoAdapter.submitData(it)
                    }
                }
            }
        }
    }

    private fun createPhotoAdapter() = PhotoAdapter(glideRequestManager = Glide.with(this)) {
        viewModel.openWebLink(url = it.attributionUrl)
    }

    private fun openWebLink(url: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                R.string.gallery_toast_unable_to_open_link,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        fun create(plantId: String) = GalleryFragment().apply {
            arguments = GalleryViewModel.createArgs(plantId = plantId)
        }
    }

    interface Callback {
        fun onGoBack()
    }
}