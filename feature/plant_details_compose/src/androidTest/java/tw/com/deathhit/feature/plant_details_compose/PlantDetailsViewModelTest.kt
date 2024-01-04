package tw.com.deathhit.feature.plant_details_compose

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.feature.plant_details_compose.config.TestGardenPlantingRepository
import tw.com.deathhit.feature.plant_details_compose.config.TestPlantRepository
import tw.com.deathhit.feature.plant_details_compose.config.generatePlantId

@OptIn(ExperimentalCoroutinesApi::class)
class PlantDetailsViewModelTest {
    private val gardenPlantingRepository = TestGardenPlantingRepository()
    private val plantRepository = TestPlantRepository()

    private lateinit var plantId: String
    private lateinit var viewModel: PlantDetailsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        plantId = generatePlantId()

        viewModel = PlantDetailsViewModel(
            gardenPlantingRepository = gardenPlantingRepository,
            plantRepository = plantRepository,
            savedStateHandle = SavedStateHandle.createHandle(
                null,
                PlantDetailsViewModel.createArgs(plantId = plantId)
            )
        )
    }

    @Test
    fun addPlantToGarden() = runTest {
        //Given

        //When
        viewModel.addPlantToGarden()
        advanceUntilIdle()

        //Then
        assert(gardenPlantingRepository.plantIds.last() == plantId)
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
            finalState == initialState.copy(
                actions = initialState.actions + PlantDetailsViewModel.State.Action.GoBack
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

    @Test
    fun sharePlant() = runTest {
        //Given
        val initialState = viewModel.stateFlow.value
        val plant = plantRepository.getPlantFlow(plantId = initialState.plantId).first()!!

        //When
        viewModel.sharePlant()
        advanceUntilIdle()

        //Then
        val finalState = viewModel.stateFlow.value

        assert(
            finalState == initialState.copy(
                actions = initialState.actions + PlantDetailsViewModel.State.Action.SharePlant(
                    plantName = plant.plantName
                )
            )
        )
    }
}