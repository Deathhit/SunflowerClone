package tw.com.deathhit.feature.plant_details.sealed

sealed interface ToastType {
    data object AddedPlantToGarden: ToastType
}