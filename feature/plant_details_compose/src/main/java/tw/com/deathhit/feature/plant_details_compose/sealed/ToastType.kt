package tw.com.deathhit.feature.plant_details_compose.sealed

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface ToastType: Parcelable {
    @Parcelize
    data object AddedPlantToGarden: ToastType
}