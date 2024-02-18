package tw.com.deathhit.feature.compose.gallery

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun GalleryScreen(
    onGoBack: () -> Unit,
    viewModel: GalleryViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val actions = viewModel.stateFlow.collectAsState().value.actions

    LaunchedEffect(actions) {
        actions.forEach { action ->
            when (action) {
                GalleryViewModel.State.Action.GoBack -> onGoBack()
                is GalleryViewModel.State.Action.OpenWebLink -> openWebLink(context, action.url)
            }

            viewModel.onAction(action)
        }
    }

    GalleryView(
        photos = viewModel.photoPagingDataFlow.collectAsLazyPagingItems(),
        onBackClick = { viewModel.goBack() },
        onPhotoClick = { viewModel.openWebLink(url = it.attributionUrl) })
}

private fun openWebLink(context: Context, url: String) {
    try {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(
            context,
            R.string.gallery_toast_unable_to_open_link,
            Toast.LENGTH_LONG
        ).show()
    }
}