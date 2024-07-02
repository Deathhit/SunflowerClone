package tw.com.deathhit.feature.compose.garden_planting_list

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import tw.com.deathhit.core.app_ui.R
import tw.com.deathhit.core.app_ui_compose.style.SunflowerCloneTheme
import tw.com.deathhit.domain.model.GardenPlantingDO

@Composable
fun GardenPlantingListLayout(
    plants: LazyPagingItems<GardenPlantingDO>,
    onPlantClick: (GardenPlantingDO) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        contentPadding = PaddingValues(
            horizontal = dimensionResource(id = R.dimen.card_side_margin),
            vertical = dimensionResource(id = R.dimen.header_margin)
        )
    ) {
        items(
            plants.itemCount
        ) { index ->
            val plant = plants[index]

            if (plant != null)
                GardenPlantingListItemView(
                    imageUrl = plant.imageUrl,
                    name = plant.plantName,
                    plantDate = plant.plantDate,
                    waterIntervalDays = plant.wateringIntervalDays,
                    onClick = { onPlantClick(plant) }
                )
        }
    }
}

@Preview
@Composable
private fun GardenPlantingListPreview(
    @PreviewParameter(GardenPlantingListPreviewParamProvider::class) plants: List<GardenPlantingDO>
) {
    val context = LocalContext.current
    val toast = Toast.makeText(context, "", Toast.LENGTH_LONG)

    SunflowerCloneTheme {
        GardenPlantingListLayout(
            plants = flowOf(PagingData.from(plants)).collectAsLazyPagingItems(),
            onPlantClick = {
                toast.apply { setText("Clicked Garden Plant ${it.plantName}!") }.show()
            }
        )
    }
}

object GardenPlantingListLayout {
    val previewList = listOf(
        GardenPlantingDO(
            description = "Apple",
            gardenPlantingId = 1,
            growZoneNumber = 1,
            imageUrl = "",
            plantDate = 0,
            plantId = "1",
            plantName = "Apple",
            wateringIntervalDays = 1
        ),
        GardenPlantingDO(
            description = "Banana",
            gardenPlantingId = 2,
            growZoneNumber = 2,
            imageUrl = "",
            plantDate = 20000,
            plantId = "2",
            plantName = "Banana",
            wateringIntervalDays = 2
        ),
        GardenPlantingDO(
            description = "Carrot",
            gardenPlantingId = 3,
            growZoneNumber = 3,
            imageUrl = "",
            plantDate = 30000,
            plantId = "3",
            plantName = "Carrot",
            wateringIntervalDays = 3
        ),
        GardenPlantingDO(
            description = "Dill",
            gardenPlantingId = 4,
            growZoneNumber = 4,
            imageUrl = "",
            plantDate = 40000,
            plantId = "4",
            plantName = "Dill",
            wateringIntervalDays = 4
        ),
    )
}

private class GardenPlantingListPreviewParamProvider :
    PreviewParameterProvider<List<GardenPlantingDO>> {
    override val values: Sequence<List<GardenPlantingDO>> =
        sequenceOf(
            emptyList(),
            GardenPlantingListLayout.previewList
        )
}