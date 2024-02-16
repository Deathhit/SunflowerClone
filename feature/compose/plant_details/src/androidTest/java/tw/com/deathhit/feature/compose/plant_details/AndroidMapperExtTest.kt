package tw.com.deathhit.feature.compose.plant_details

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.feature.compose.plant_details.sealed.ToastType

class AndroidMapperExtTest {
    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun mapToastTypeToMessage_addedPlantToGarden() {
        //Given
        val toastType = ToastType.AddedPlantToGarden

        //When
        val message = toastType.getMessage(context)

        //Then
        assert(message == context.getString(R.string.plant_details_added_plant_to_garden))
    }
}