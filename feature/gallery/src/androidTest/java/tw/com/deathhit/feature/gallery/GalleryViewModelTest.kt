package tw.com.deathhit.feature.gallery

import androidx.lifecycle.SavedStateHandle
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
import tw.com.deathhit.domain.PhotoRepository
import tw.com.deathhit.feature.gallery.config.generatePlantName
import tw.com.deathhit.feature.gallery.config.generateUrl
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class GalleryViewModelTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var photoRepository: PhotoRepository

    private lateinit var plantName: String
    private lateinit var viewModel: GalleryViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        hiltRule.inject()

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