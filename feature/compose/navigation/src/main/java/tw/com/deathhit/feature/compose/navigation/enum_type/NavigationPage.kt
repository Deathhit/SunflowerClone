package tw.com.deathhit.feature.compose.navigation.enum_type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import tw.com.deathhit.feature.compose.navigation.R

enum class NavigationPage(
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