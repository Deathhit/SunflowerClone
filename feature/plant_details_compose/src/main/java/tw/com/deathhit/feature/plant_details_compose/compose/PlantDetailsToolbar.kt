package tw.com.deathhit.feature.plant_details_compose.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import tw.com.deathhit.core.app_ui_compose.SunflowerCloneTheme
import tw.com.deathhit.feature.plant_details_compose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantDetailsToolbar(
    plantName: String,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface {
        TopAppBar(
            modifier = modifier
                .statusBarsPadding()
                .background(color = MaterialTheme.colorScheme.surface),
            title = {
                Row {
                    IconButton(
                        onBackClick,
                        Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.plant_details_back)
                        )
                    }

                    Text(
                        text = plantName,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )

                    IconButton(
                        onShareClick,
                        Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            Icons.Filled.Share,
                            contentDescription = stringResource(R.string.plant_details_share)
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    SunflowerCloneTheme {
        PlantDetailsToolbar(
            plantName = "Apple",
            onBackClick = { },
            onShareClick = { }
        )
    }
}