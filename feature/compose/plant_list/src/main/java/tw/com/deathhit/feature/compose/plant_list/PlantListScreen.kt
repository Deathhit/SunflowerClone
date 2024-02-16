package tw.com.deathhit.feature.compose.plant_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import tw.com.deathhit.domain.model.PlantDO

@Composable
internal fun PlantListScreen(
    plants: LazyPagingItems<PlantDO>,
    onPlantClick: (PlantDO) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .imePadding(),
        contentPadding = PaddingValues(
            horizontal = dimensionResource(id = tw.com.deathhit.core.app_ui.R.dimen.card_side_margin),
            vertical = dimensionResource(id = tw.com.deathhit.core.app_ui.R.dimen.header_margin)
        )
    ) {
        items(
            plants.itemCount
        ) { index ->
            val plant = plants[index]

            if (plant != null)
                PlantListItem(imageUrl = plant.imageUrl, name = plant.plantName, onClick = {
                    onPlantClick(plant)
                })
        }
    }
}

@Preview
@Composable
private fun PlantListScreenPreview(
    @PreviewParameter(PlantListPreviewParamProvider::class) plants: List<PlantDO>
) {
    PlantListScreen(plants = flowOf(PagingData.from(plants)).collectAsLazyPagingItems())
}

private class PlantListPreviewParamProvider : PreviewParameterProvider<List<PlantDO>> {
    override val values: Sequence<List<PlantDO>> =
        sequenceOf(
            emptyList(),
            listOf(
                PlantDO(
                    description = "Apple",
                    growZoneNumber = 1,
                    imageUrl = "",
                    plantDate = null,
                    plantId = "1",
                    plantName = "Apple",
                    wateringIntervalDays = 1
                ),
                PlantDO(
                    description = "Banana",
                    growZoneNumber = 2,
                    imageUrl = "",
                    plantDate = null,
                    plantId = "2",
                    plantName = "Banana",
                    wateringIntervalDays = 2
                ),
                PlantDO(
                    description = "Carrot",
                    growZoneNumber = 3,
                    imageUrl = "",
                    plantDate = null,
                    plantId = "3",
                    plantName = "Carrot",
                    wateringIntervalDays = 3
                ),
                PlantDO(
                    description = "Dill",
                    growZoneNumber = 4,
                    imageUrl = "",
                    plantDate = null,
                    plantId = "4",
                    plantName = "Dill",
                    wateringIntervalDays = 4
                ),
            )
        )
}