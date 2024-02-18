package tw.com.deathhit.feature.compose.garden_planting_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import tw.com.deathhit.core.app_ui_compose.CroppedPlantImage
import tw.com.deathhit.core.app_ui_compose.style.SunflowerCloneTheme
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GardenPlantingListItem(
    imageUrl: String?,
    name: String?,
    plantDate: Long,
    waterIntervalDays: Int?,
    onClick: () -> Unit
) {
    SunflowerCloneTheme {
        Card(
            onClick = onClick,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = tw.com.deathhit.core.app_ui.R.dimen.card_side_margin))
                .padding(bottom = dimensionResource(id = tw.com.deathhit.core.app_ui.R.dimen.card_bottom_margin))
        ) {
            Column(Modifier.fillMaxWidth()) {
                CroppedPlantImage(
                    model = imageUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = tw.com.deathhit.core.app_ui.R.dimen.plant_item_image_height)),
                    contentDescription = stringResource(R.string.garden_planting_list_image_description)
                )

                name?.let { name ->
                    Text(
                        text = name,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(id = tw.com.deathhit.core.app_ui.R.dimen.margin_normal))
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
                            .padding(bottom = dimensionResource(id = tw.com.deathhit.core.app_ui.R.dimen.margin_normal)),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    GardenPlantingListItem(
        imageUrl = "",
        name = "Tomato",
        plantDate = 0,
        waterIntervalDays = 4,
        onClick = {}
    )
}