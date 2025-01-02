package tw.com.deathhit.sunflower_clone.navigation

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.sunflower_clone.navigation.config.generatePlantId
import tw.com.deathhit.sunflower_clone.navigation.config.generatePlantName
import tw.com.deathhit.sunflower_clone.navigation.model.MainScreen

class MainActivityViewModelTest {
    private lateinit var viewModel: MainActivityViewModel

    @Before
    fun setup() {
        viewModel =
            MainActivityViewModel(savedStateHandle = SavedStateHandle.createHandle(null, null))
    }

    @Test
    fun goBack() = runTest {
        //Given
        val initialState = viewModel.stateFlow.value

        //When
        viewModel.goBack()

        //Then
        val finalState = viewModel.stateFlow.value

        assert(
            finalState == initialState.copy(actions = initialState.actions + MainActivityViewModel.State.Action.GoBack)
        )
    }

    @Test
    fun goToGalleryScreen() = runTest {
        //Given
        val initialState = viewModel.stateFlow.value
        val plantName = generatePlantName()

        //When
        viewModel.goToGalleryScreen(plantName = plantName)

        //Then
        val finalState = viewModel.stateFlow.value

        assert(
            finalState == initialState.copy(
                actions = initialState.actions + MainActivityViewModel.State.Action.GoToScreen(
                    screen = MainScreen.Gallery(plantName = plantName)
                )
            )
        )
    }

    @Test
    fun goToPlantDetailsScreen() = runTest {
        //Given
        val initialState = viewModel.stateFlow.value
        val plantId = generatePlantId()

        //When
        viewModel.goToPlantDetailsScreen(plantId = plantId)

        //Then
        val finalState = viewModel.stateFlow.value

        assert(
            finalState == initialState.copy(
                actions = initialState.actions + MainActivityViewModel.State.Action.GoToScreen(
                    screen = MainScreen.PlantDetails(plantId = plantId)
                )
            )
        )
    }

    @Test
    fun onAction() = runTest {
        //Given
        val initialState = viewModel.stateFlow.value

        viewModel.goBack()

        val updatedState = viewModel.stateFlow.value

        //When
        viewModel.onAction(updatedState.actions.last())

        //Then
        val finalState = viewModel.stateFlow.value

        assert(finalState == initialState)
    }
}