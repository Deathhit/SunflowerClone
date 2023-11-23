package tw.com.deathhit.feature.navigation

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
import tw.com.deathhit.feature.navigation.config.generatePlantId

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class NavigationViewModelTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var viewModel: NavigationViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        hiltRule.inject()

        viewModel = NavigationViewModel()
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
                actions = initialState.actions + NavigationViewModel.State.Action.GoToPlantDetailsScreen(
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