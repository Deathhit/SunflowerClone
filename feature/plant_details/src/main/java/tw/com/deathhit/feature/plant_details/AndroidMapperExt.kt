package tw.com.deathhit.feature.plant_details

import android.content.Context
import tw.com.deathhit.feature.plant_details.sealed.ToastType

internal fun ToastType.getMessage(context: Context) = when (this) {
    ToastType.AddedPlantToGarden -> context.getString(R.string.plant_details_added_plant_to_garden)
}