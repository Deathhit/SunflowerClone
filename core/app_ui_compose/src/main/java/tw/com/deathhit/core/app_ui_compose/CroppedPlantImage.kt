package tw.com.deathhit.core.app_ui_compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CroppedPlantImage(
    model: Any?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    GlideImage(model = model, contentDescription = contentDescription, modifier = modifier)
}