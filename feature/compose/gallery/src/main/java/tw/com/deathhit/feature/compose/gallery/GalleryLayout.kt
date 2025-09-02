package tw.com.deathhit.feature.compose.gallery

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import tw.com.deathhit.core.sunflower_clone_ui.SunflowerCloneTheme
import tw.com.deathhit.domain.sunflower_clone.model.PhotoDO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryLayout(
    photos: LazyPagingItems<PhotoDO>,
    onBackClick: () -> Unit,
    onPhotoClick: (PhotoDO) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(
                        onBackClick
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.gallery_back)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                title = {
                    Text(
                        text = stringResource(id = R.string.gallery_title),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                modifier = Modifier
                    .statusBarsPadding()
                    .background(color = MaterialTheme.colorScheme.surface)
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .padding(padding),
            contentPadding = PaddingValues(
                horizontal = 8.dp,
                vertical = 16.dp
            )
        ) {
            items(
                photos.itemCount
            ) { index ->
                val photo = photos[index]

                if (photo != null)
                    GalleryItem(
                        imageUrl = photo.imageUrl,
                        photographer = photo.authorName,
                        onClick = {
                            onPhotoClick(photo)
                        })
            }
        }
    }
}

@Preview
@Composable
private fun GalleryPreview(
    @PreviewParameter(GalleryPreviewParamProvider::class) photos: List<PhotoDO>
) {
    val context = LocalContext.current
    val toast = Toast.makeText(context, "", Toast.LENGTH_LONG)

    SunflowerCloneTheme {
        GalleryLayout(
            photos = flowOf(PagingData.from(photos)).collectAsLazyPagingItems(),
            onBackClick = {
                toast.apply { setText("Clicked Back!") }.show()
            },
            onPhotoClick = {
                toast.apply { setText("Clicked Photo ${it.photoId}!") }.show()
            }
        )
    }
}

private class GalleryPreviewParamProvider :
    PreviewParameterProvider<List<PhotoDO>> {
    override val values: Sequence<List<PhotoDO>> =
        sequenceOf(
            emptyList(),
            listOf(
                PhotoDO(
                    attributionUrl = "",
                    authorId = "1",
                    authorName = "Apple",
                    imageUrl = "",
                    photoId = "1",
                    plantName = "Apple"
                ),
                PhotoDO(
                    attributionUrl = "",
                    authorId = "2",
                    authorName = "Banana",
                    imageUrl = "",
                    photoId = "2",
                    plantName = "Banana"
                ),
                PhotoDO(
                    attributionUrl = "",
                    authorId = "3",
                    authorName = "Carrot",
                    imageUrl = "",
                    photoId = "3",
                    plantName = "Carrot"
                ),
                PhotoDO(
                    attributionUrl = "",
                    authorId = "4",
                    authorName = "Dill",
                    imageUrl = "",
                    photoId = "4",
                    plantName = "Dill"
                )
            )
        )
}