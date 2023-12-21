package tw.com.deathhit.feature.plant_list

import androidx.lifecycle.SavedStateHandle
import androidx.paging.testing.asSnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.feature.plant_list.config.TestPlantRepository
import tw.com.deathhit.feature.plant_list.config.generatePlantId

@OptIn(ExperimentalCoroutinesApi::class)
class PlantListViewModelTest {
    private val plantRepository = TestPlantRepository()

    private lateinit var viewModel: PlantListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        viewModel = PlantListViewModel(
            plantRepository = plantRepository,
            savedStateHandle = SavedStateHandle.createHandle(null, null)
        )
    }

    @Test
    fun plantPagingDataFlow() = runTest {
        //Given
        val pageSize = 25

        val repositoryItems =
            plantRepository.getPlantPagingDataFlow().asSnapshot {
                scrollTo(pageSize)
            }

        //When
        val viewModelItems = viewModel.plantPagingDataFlow.asSnapshot {
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
                actions = initialState.actions + PlantListViewModel.State.Action.GoToPlantDetailsScreen(
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