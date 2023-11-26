package tw.com.deathhit.sunflower_clone.model

sealed interface MainScreen {
    data class Gallery(val plantName: String) : MainScreen
    data object Navigation : MainScreen
    data class PlantDetails(val plantId: String) : MainScreen
}