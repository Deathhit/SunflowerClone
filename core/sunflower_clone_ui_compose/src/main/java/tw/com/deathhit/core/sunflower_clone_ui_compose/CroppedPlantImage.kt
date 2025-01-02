package tw.com.deathhit.core.sunflower_clone_ui_compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CroppedPlantImage(
    model: Any?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    GlideImage(
        contentScale = ContentScale.Crop,
        model = model,
        contentDescription = contentDescription,
        modifier = modifier
    )
}