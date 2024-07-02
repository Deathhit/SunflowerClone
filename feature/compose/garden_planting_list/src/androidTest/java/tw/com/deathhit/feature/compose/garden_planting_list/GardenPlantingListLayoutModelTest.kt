package tw.com.deathhit.feature.compose.garden_planting_list

import androidx.lifecycle.SavedStateHandle
import androidx.paging.testing.asSnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.feature.compose.garden_planting_list.config.TestGardenPlantingRepository
import tw.com.deathhit.feature.compose.garden_planting_list.config.generatePlantId

@OptIn(ExperimentalCoroutinesApi::class)
class GardenPlantingListLayoutModelTest {
    private val gardenPlantingRepository = TestGardenPlantingRepository()

    private lateinit var viewModel: GardenPlantingListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        viewModel = GardenPlantingListViewModel(
            gardenPlantingRepository = gardenPlantingRepository,
            savedStateHandle = SavedStateHandle.createHandle(null, null)
        )
    }

    @Test
    fun gardenPlantingPagingDataFlow() = runTest {
        //Given
        val pageSize = 25

        val repositoryItems =
            gardenPlantingRepository.getGardenPlantingPagingDataFlow().asSnapshot {
                scrollTo(pageSize)
            }

        //When
        val viewModelItems = viewModel.gardenPlantingPagingDataFlow.asSnapshot {
            scrollTo(pageSize)
        }

        //Then
        assert(repositoryItems == viewModelItems)
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
                actions = initialState.actions + GardenPlantingListViewModel.State.Action.GoToPlantDetailsScreen(
                    plantId = plantId
                )
            )
        )
    }

    @Test
    fun onAction() = runTest {
        //Given
        val initialState = viewModel.stateFlow.value

        viewModel.goToPlantDetailsScreen(plantId = generatePlantId())

        val updatedState = viewModel.stateFlow.value

        //When
        viewModel.onAction(updatedState.actions.last())

        //Then
        val finalState = viewModel.stateFlow.value

        assert(finalState == initialState)
    }
}