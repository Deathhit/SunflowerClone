package tw.com.deathhit.feature.plant_list_compose

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PlantListItem(name: String, imageUrl: String, onClick: () -> Unit) {
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
                contentDescription = stringResource(R.string.plant_list_item_image_description)
            )
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
    }
}

@Preview
@Composable
private fun Preview() {
    SunflowerCloneTheme {
        PlantListItem(
            imageUrl = "",
            name = "Tomato",
            onClick = {}
        )
    }
}