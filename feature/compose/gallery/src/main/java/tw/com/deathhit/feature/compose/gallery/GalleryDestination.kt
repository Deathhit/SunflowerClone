package tw.com.deathhit.feature.compose.gallery

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.navArgument

data class GalleryDestination(val name: String) {
    val route = "$name/{$KEY_PLANT_NAME}"

    fun createLink(plantName: String) = "$name/$plantName"

    companion object {
        private const val TAG = "GalleryDestination"
        private const val KEY_PLANT_NAME = "$TAG.KEY_PLANT_NAME"

        val args = listOf(navArgument(KEY_PLANT_NAME) {
            type = NavType.StringType
        })

        internal val SavedStateHandle.plantName: String get() = get<String>(KEY_PLANT_NAME)!!
    }
}
