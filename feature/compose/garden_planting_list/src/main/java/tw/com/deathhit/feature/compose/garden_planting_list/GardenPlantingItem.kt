package tw.com.deathhit.feature.compose.garden_planting_list

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
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun GardenPlantingItem(
    imageUrl: String?,
    name: String?,
    plantDate: Long,
    waterIntervalDays: Int?,
    onClick: () -> Unit
) {
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
                contentDescription = stringResource(R.string.garden_planting_list_image_description)
            )

            name?.let { name ->
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

            Text(
                text = stringResource(id = R.string.garden_planting_list_planted),
                Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleSmall
            )

            val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())

            Text(
                text = dateFormat.format(plantDate),
                Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.labelSmall
            )

            waterIntervalDays?.let { waterIntervalDays ->
                Text(
                    text = stringResource(
                        id = R.string.garden_planting_list_water_in_x_days,
                        waterIntervalDays
                    ),
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Preview
@Composable
private fun GardenPlantingItemPreview() {
    val context = LocalContext.current
    val toast = Toast.makeText(context, "", Toast.LENGTH_LONG)

    SunflowerCloneTheme {
        GardenPlantingItem(
            imageUrl = "",
            name = "Tomato",
            plantDate = 0,
            waterIntervalDays = 4,
            onClick = {
                toast.apply { setText("Clicked Garden Plant!") }.show()
            }
        )
    }
}