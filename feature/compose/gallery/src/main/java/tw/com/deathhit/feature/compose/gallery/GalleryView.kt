package tw.com.deathhit.feature.compose.gallery

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
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import tw.com.deathhit.core.app_ui_compose.style.SunflowerCloneTheme
import tw.com.deathhit.domain.model.PhotoDO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GalleryView(
    photos: LazyPagingItems<PhotoDO>,
    onBackClick: () -> Unit,
    onPhotoClick: (PhotoDO) -> Unit
) {
    SunflowerCloneTheme {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            topBar = {
                GalleryTopBar(
                    topAppBarScrollBehavior = scrollBehavior,
                    onBackClick = onBackClick,
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { padding ->
            GalleryContent(
                photos = photos,
                onPhotoClick = onPhotoClick,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
        }
    }
}

@Composable
private fun GalleryContent(
    photos: LazyPagingItems<PhotoDO>,
    onPhotoClick: (PhotoDO) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        contentPadding = PaddingValues(
            horizontal = dimensionResource(id = tw.com.deathhit.core.app_ui.R.dimen.card_side_margin),
            vertical = dimensionResource(id = tw.com.deathhit.core.app_ui.R.dimen.header_margin)
        )
    ) {
        items(
            photos.itemCount
        ) { index ->
            val photo = photos[index]

            if (photo != null)
                GalleryItemView(
                    imageUrl = photo.imageUrl,
                    photographer = photo.authorName,
                    onClick = {
                        onPhotoClick(photo)
                    })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GalleryTopBar(
    topAppBarScrollBehavior: TopAppBarScrollBehavior,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
        scrollBehavior = topAppBarScrollBehavior,
        title = {
            Text(
                text = stringResource(id = R.string.gallery_title),
                style = MaterialTheme.typography.titleLarge,
            )
        },
        modifier = modifier
            .statusBarsPadding()
            .background(color = MaterialTheme.colorScheme.surface)
    )
}

@Preview
@Composable
private fun GalleryPreview(
    @PreviewParameter(GalleryPreviewParamProvider::class) photos: List<PhotoDO>
) {
    GalleryView(
        photos = flowOf(PagingData.from(photos)).collectAsLazyPagingItems(),
        onBackClick = {},
        onPhotoClick = {}
    )
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