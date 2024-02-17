package tw.com.deathhit.feature.compose.plant_details

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ShareCompat
import androidx.hilt.navigation.compose.hiltViewModel

@ExperimentalMaterial3Api
@Composable
fun PlantDetailsScreen(
    onGoBack: () -> Unit,
    onGoToGalleryScreen: (plantName: String) -> Unit,
    viewModel: PlantDetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val actions = viewModel.stateFlow.collectAsState().value.actions

    LaunchedEffect(actions) {
        actions.forEach { action ->
            when (action) {
                PlantDetailsViewModel.State.Action.GoBack -> onGoBack()
                is PlantDetailsViewModel.State.Action.GoToGalleryScreen -> onGoToGalleryScreen(
                    action.plantName
                )

                is PlantDetailsViewModel.State.Action.SharePlant -> sharePlant(
                    context = context,
                    plantName = action.plantName
                )

                is PlantDetailsViewModel.State.Action.Toast -> showToast(
                    context = context,
                    msg = action.type.getMessage(context)
                )
            }

            viewModel.onAction(action)
        }
    }

    viewModel.plantFlow.collectAsState().value?.run {
        PlantDetailsView(
            description = description,
            isPlanted = isPlanted,
            plantImageUrl = imageUrl,
            plantName = plantName,
            wateringInterval = wateringIntervalDays,
            onBackClick = { viewModel.goBack() },
            onFabClick = { viewModel.addPlantToGarden() },
            onGalleryClick = { viewModel.goToGalleryScreen() },
            onShareClick = { viewModel.sharePlant() }
        )
    }
}

private fun sharePlant(context: Context, plantName: String) {
    with(context) {
        startActivity(
            ShareCompat.IntentBuilder(this)
                .setText(getString(R.string.plant_details_share_message_x, plantName))
                .setType("text/plain")
                .createChooserIntent()
                .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        )
    }
}

private fun showToast(context: Context, msg: String) {
    Toast.makeText(
        context,
        msg,
        Toast.LENGTH_LONG
    ).show()
}