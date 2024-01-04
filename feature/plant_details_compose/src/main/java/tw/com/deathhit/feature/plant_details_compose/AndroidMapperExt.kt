package tw.com.deathhit.feature.plant_details_compose

import android.content.Context
import tw.com.deathhit.feature.plant_details_compose.sealed.ToastType

internal fun ToastType.getMessage(context: Context) = when (this) {
    ToastType.AddedPlantToGarden -> context.getString(R.string.plant_details_added_plant_to_garden)
}