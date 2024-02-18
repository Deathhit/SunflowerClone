package tw.com.deathhit.feature.compose.gallery

import androidx.lifecycle.SavedStateHandle
import androidx.paging.testing.asSnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.feature.compose.gallery.config.TestPhotoRepository
import tw.com.deathhit.feature.compose.gallery.config.generatePlantName
import tw.com.deathhit.feature.compose.gallery.config.generateUrl

@OptIn(ExperimentalCoroutinesApi::class)
class GalleryViewModelTest {
    private val photoRepository = TestPhotoRepository()

    private lateinit var plantName: String

    private lateinit var viewModel: GalleryViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        plantName = generatePlantName()

        viewModel = GalleryViewModel(
            photoRepository = photoRepository,
            savedStateHandle = SavedStateHandle.createHandle(
                null,
                GalleryViewModel.createArgs(plantName = plantName)
            )
        )
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
            finalState
                    == initialState.copy(actions = initialState.actions + GalleryViewModel.State.Action.GoBack)
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
    fun openWebUrl() = runTest {
        //Given
        val initialState = viewModel.stateFlow.value
        val url = generateUrl()

        //When
        viewModel.openWebLink(url = url)

        //Then
        val finalState = viewModel.stateFlow.value

        assert(
            finalState == initialState.copy(
                actions = initialState.actions + GalleryViewModel.State.Action.OpenWebLink(
                    url = url
                )
            )
        )
    }

    @Test
    fun photoPagingDataFlow() = runTest {
        //Given
        val pageSize = 25

        val repositoryItems =
            photoRepository.getPhotoPagingDataFlow(plantName = plantName).asSnapshot {
                scrollTo(pageSize)
            }

        //When
        val viewModelItems = viewModel.photoPagingDataFlow.asSnapshot {
            scrollTo(pageSize)
        }

        //Then
        assert(repositoryItems == viewModelItems)
    }
}