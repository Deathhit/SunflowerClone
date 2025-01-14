package tw.com.deathhit.feature.compose.navigation

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import tw.com.deathhit.core.sunflower_clone_ui_compose.SunflowerCloneTheme
import tw.com.deathhit.feature.compose.garden_planting_list.GardenPlantingListLayout
import tw.com.deathhit.feature.compose.navigation.enum_type.NavigationPage
import tw.com.deathhit.feature.compose.plant_list.PlantListLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationLayout(
    myGardenPage: @Composable () -> Unit,
    pageList: List<NavigationPage>,
    pagerState: PagerState,
    plantListPage: @Composable () -> Unit,
    title: String
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scrollToPageJob = remember {
        mutableStateOf<Job?>(null)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                divider = {
                    Spacer(modifier = Modifier.height(5.dp))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                pageList.forEachIndexed { index, page ->
                    val text = stringResource(id = page.titleResId)

                    val isSelected = pagerState.currentPage == index

                    val painter =
                        painterResource(id = if (isSelected) page.activeIconRes else page.inactiveIconRes)

                    Tab(
                        selected = isSelected,
                        onClick = {
                            scrollToPageJob.value?.cancel()

                            scrollToPageJob.value =
                                coroutineScope.launch { pagerState.scrollToPage(index) }
                        },
                        text = { Text(text = text) },
                        icon = { Icon(painter = painter, contentDescription = text) })
                }
            }

            HorizontalPager(modifier = Modifier.fillMaxSize(), state = pagerState) { page ->
                when (pageList[page]) {
                    NavigationPage.MY_GARDEN -> myGardenPage()
                    NavigationPage.PLANT_LIST -> plantListPage()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun NavigationPreview() {
    val context = LocalContext.current
    val pageList = listOf(NavigationPage.PLANT_LIST, NavigationPage.MY_GARDEN)
    val toast = Toast.makeText(context, "", Toast.LENGTH_LONG)

    SunflowerCloneTheme {
        NavigationLayout(
            myGardenPage = {
                GardenPlantingListLayout(
                    plants = flowOf(PagingData.from(GardenPlantingListLayout.previewList)).collectAsLazyPagingItems(),
                    onPlantClick = {
                        toast.apply { setText("Clicked Garden Plant ${it.plantName}!") }.show()
                    }
                )
            },
            pageList = pageList,
            pagerState = rememberPagerState(pageCount = { NavigationPage.entries.size }),
            plantListPage = {
                PlantListLayout(
                    plants = flowOf(PagingData.from(PlantListLayout.previewList)).collectAsLazyPagingItems(),
                    onPlantClick = {
                        toast.apply { setText("Clicked Plant ${it.plantName}!") }.show()
                    }
                )
            },
            title = "Sunflower Clone"
        )
    }
}