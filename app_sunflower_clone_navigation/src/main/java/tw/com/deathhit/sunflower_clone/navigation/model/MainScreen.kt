package tw.com.deathhit.sunflower_clone.navigation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface MainScreen : Parcelable {
    @Parcelize
    data class Gallery(val plantName: String) : MainScreen

    @Parcelize
    data class PlantDetails(val plantId: String) : MainScreen
}