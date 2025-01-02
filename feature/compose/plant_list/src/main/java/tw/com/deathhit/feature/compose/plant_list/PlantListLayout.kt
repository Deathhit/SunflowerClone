package tw.com.deathhit.feature.compose.plant_list

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
import tw.com.deathhit.core.sunflower_clone_ui_compose.style.SunflowerCloneTheme
import tw.com.deathhit.domain.model.PlantDO

@Composable
fun PlantListLayout(
    plants: LazyPagingItems<PlantDO>,
    onPlantClick: (PlantDO) -> Unit,
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
                PlantItem(imageUrl = plant.imageUrl, name = plant.plantName, onClick = {
                    onPlantClick(plant)
                })
        }
    }
}

@Preview
@Composable
private fun PlantListPreview(
    @PreviewParameter(PlantListPreviewParamProvider::class) plants: List<PlantDO>
) {
    val context = LocalContext.current
    val toast = Toast.makeText(context, "", Toast.LENGTH_LONG)

    SunflowerCloneTheme {
        PlantListLayout(
            plants = flowOf(PagingData.from(plants)).collectAsLazyPagingItems(),
            onPlantClick = {
                toast.apply { setText("Clicked Plant ${it.plantName}!") }.show()
            }
        )
    }
}

object PlantListLayout {
    val previewList = listOf(
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
}

private class PlantListPreviewParamProvider : PreviewParameterProvider<List<PlantDO>> {
    override val values: Sequence<List<PlantDO>> =
        sequenceOf(
            emptyList(),
            PlantListLayout.previewList
        )
}