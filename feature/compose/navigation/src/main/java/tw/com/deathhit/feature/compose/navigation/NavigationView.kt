package tw.com.deathhit.feature.compose.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import tw.com.deathhit.core.app_ui_compose.style.SunflowerCloneTheme

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun NavigationView(
    myGardenPageView: @Composable () -> Unit,
    plantListPageView: @Composable () -> Unit
) {
    SunflowerCloneTheme {
        val pagerState = rememberPagerState(pageCount = { NavigationPage.entries.size })
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                NavigationTopAppBar(scrollBehavior = scrollBehavior)
            }
        ) { contentPadding ->
            NavigationPager(
                pagerState = pagerState,
                myGardenPageView = myGardenPageView,
                plantListPageView = plantListPageView,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NavigationPager(
    pagerState: PagerState,
    myGardenPageView: @Composable () -> Unit,
    plantListPageView: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val pageList = listOf(NavigationPage.PLANT_LIST, NavigationPage.MY_GARDEN)

    Column(modifier = modifier) {
        NavigationTabRow(pagerState = pagerState)

        HorizontalPager(state = pagerState) { page ->
            when (pageList[page]) {
                NavigationPage.MY_GARDEN -> myGardenPageView()
                NavigationPage.PLANT_LIST -> plantListPageView()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NavigationTab(navigationPage: NavigationPage, pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()
    val index = NavigationPage.entries.indexOf(navigationPage)
    val text = stringResource(id = navigationPage.titleResId)

    val isSelected = pagerState.currentPage == index

    val painter =
        painterResource(id = if (isSelected) navigationPage.activeIconRes else navigationPage.inactiveIconRes)

    Tab(
        selected = isSelected,
        onClick = { coroutineScope.launch { pagerState.scrollToPage(index) } },
        text = { Text(text = text) },
        icon = { Icon(painter = painter, contentDescription = text) })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NavigationTabRow(pagerState: PagerState) {
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        divider = {
            Spacer(modifier = Modifier.height(5.dp))
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        NavigationPage.entries.forEach {
            NavigationTab(navigationPage = it, pagerState = pagerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NavigationTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior
) {
    val context = LocalContext.current

    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = context.applicationInfo.labelRes),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    NavigationView(
        myGardenPageView = {},
        plantListPageView = {})
}

private enum class NavigationPage(
    @DrawableRes val activeIconRes: Int,
    @DrawableRes val inactiveIconRes: Int,
    @StringRes val titleResId: Int,
) {
    MY_GARDEN(
        tw.com.deathhit.core.app_ui.R.drawable.ic_my_garden_active,
        tw.com.deathhit.core.app_ui.R.drawable.ic_my_garden_inactive,
        R.string.navigation_tab_my_garden
    ),
    PLANT_LIST(
        tw.com.deathhit.core.app_ui.R.drawable.ic_plant_list_active,
        tw.com.deathhit.core.app_ui.R.drawable.ic_plant_list_inactive,
        R.string.navigation_tab_plant_list
    )
}