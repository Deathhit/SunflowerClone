package tw.com.deathhit.feature.compose.plant_list

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tw.com.deathhit.core.sunflower_clone_ui.SunflowerCloneTheme
import tw.com.deathhit.core.sunflower_clone_ui.widget.CroppedPlantImage

@Composable
fun PlantItem(name: String, imageUrl: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        modifier = Modifier
            .padding(8.dp)
    ) {
        Column(Modifier.fillMaxWidth()) {
            CroppedPlantImage(
                model = imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp),
                contentDescription = stringResource(R.string.plant_list_item_image_description)
            )
            Text(
                text = name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    )
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview
@Composable
private fun PlantItemPreview() {
    val context = LocalContext.current
    val toast = Toast.makeText(context, "", Toast.LENGTH_LONG)

    SunflowerCloneTheme {
        PlantItem(
            imageUrl = "",
            name = "Tomato",
            onClick = {
                toast.apply { setText("Clicked Plant!") }.show()
            }
        )
    }
}