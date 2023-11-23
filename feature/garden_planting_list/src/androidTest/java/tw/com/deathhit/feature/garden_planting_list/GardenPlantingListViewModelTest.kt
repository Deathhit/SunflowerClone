package tw.com.deathhit.feature.garden_planting_list

import androidx.paging.testing.asSnapshot
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tw.com.deathhit.domain.GardenPlantingRepository
import tw.com.deathhit.feature.garden_planting_list.config.generatePlantId
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class GardenPlantingListViewModelTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var gardenPlantingRepository: GardenPlantingRepository

    private lateinit var viewModel: GardenPlantingListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        hiltRule.inject()

        viewModel = GardenPlantingListViewModel(
            gardenPlantingRepository = gardenPlantingRepository
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