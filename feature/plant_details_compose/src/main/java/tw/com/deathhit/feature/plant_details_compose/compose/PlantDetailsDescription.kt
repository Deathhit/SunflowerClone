package tw.com.deathhit.feature.plant_details_compose.compose

import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import tw.com.deathhit.core.app_ui_compose.SunflowerCloneTheme

@Composable
fun PlantDetailsDescription(description: String) {
    AndroidView(
        modifier = Modifier.padding(bottom = 48f.dp),
        factory = {
            TextView(it).apply {
                linksClickable = true
                movementMethod = LinkMovementMethod.getInstance()
            }
        },
        update = {
            it.text = description
        }
    )
}

@Preview
@Composable
private fun Preview() {
    SunflowerCloneTheme {
        PlantDetailsDescription(description = "HTML<br>description")
    }
}