package tw.com.deathhit.feature.compose.plant_details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.navArgument

data class PlantDetailsDestination(val name: String) {
    val route = "$name/{$KEY_PLANT_ID}"

    fun createLink(plantId: String) = "$name/$plantId"

    companion object {
        private const val TAG = "PlantDetailsDestination"
        private const val KEY_PLANT_ID = "$TAG.KEY_PLANT_ID"

        val args = listOf(navArgument(KEY_PLANT_ID) {
            defaultValue = "malus-pumila"   //For testing
            type = NavType.StringType
        })

        internal val SavedStateHandle.plantId: String get() = get<String>(KEY_PLANT_ID)!!
    }
}