package tw.com.deathhit.feature.compose.plant_details

import android.text.method.LinkMovementMethod
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.text.HtmlCompat
import tw.com.deathhit.core.sunflower_clone_ui_compose.CroppedPlantImage
import tw.com.deathhit.core.sunflower_clone_ui_compose.style.Dimens
import tw.com.deathhit.core.sunflower_clone_ui_compose.style.SunflowerCloneTheme

@ExperimentalMaterial3Api
@Composable
fun PlantDetailsLayout(
    description: String,
    isPlanted: Boolean,
    plantImageUrl: String,
    plantName: String,
    wateringInterval: Int,
    onBackClick: () -> Unit,
    onFabClick: () -> Unit,
    onGalleryClick: () -> Unit,
    onShareClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            if (!isPlanted)
                FloatingActionButton(
                    shape = MaterialTheme.shapes.small,
                    onClick = onFabClick
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = stringResource(R.string.plant_details_add_plant_to_garden)
                    )
                }
        },
        topBar = {
            CenterAlignedTopAppBar(
                actions = {
                    IconButton(
                        onShareClick
                    ) {
                        Icon(
                            Icons.Filled.Share,
                            contentDescription = stringResource(R.string.plant_details_share)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onBackClick
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.plant_details_back)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                title = {
                    Text(
                        text = plantName,
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                modifier = Modifier
                    .statusBarsPadding()
                    .background(color = MaterialTheme.colorScheme.surface)
            )
        }
    ) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
        ) {
            val (image, info) = createRefs()

            CroppedPlantImage(
                model = plantImageUrl,
                modifier = Modifier
                    .constrainAs(image) { top.linkTo(parent.top) }
                    .height(Dimens.PlantDetailsImageHeight)
                    .fillMaxWidth()
            )

            Column(modifier = Modifier
                .constrainAs(info) { top.linkTo(image.bottom) }
                .padding(Dimens.PaddingLarge)) {
                Box(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(
                            start = Dimens.PaddingSmall,
                            end = Dimens.PaddingSmall,
                            bottom = Dimens.PaddingNormal
                        )
                ) {
                    Column(Modifier.fillMaxWidth()) {
                        Text(
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold,
                            text = stringResource(id = R.string.plant_details_watering_needs),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(horizontal = Dimens.PaddingSmall)
                        )

                        Text(
                            text = stringResource(
                                R.string.plant_details_every_x_days,
                                wateringInterval
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                    }

                    Image(
                        painter = painterResource(id = tw.com.deathhit.core.app_ui.R.drawable.ic_photo_library),
                        contentDescription = "Gallery Icon",
                        Modifier
                            .clickable { onGalleryClick() }
                            .align(Alignment.CenterEnd)
                    )
                }

                AndroidView(
                    modifier = Modifier.padding(bottom = Dimens.PaddingScreenBottom),
                    factory = {
                        TextView(it).apply {
                            linksClickable = true
                            movementMethod = LinkMovementMethod.getInstance()
                        }
                    },
                    update = {
                        it.text = HtmlCompat.fromHtml(
                            description,
                            HtmlCompat.FROM_HTML_MODE_COMPACT
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun PlantDetailsPreview() {
    val context = LocalContext.current
    val toast = Toast.makeText(context, "", Toast.LENGTH_LONG)

    SunflowerCloneTheme {
        PlantDetailsLayout(
            description = "The tomato is the edible, often red, berry of the nightshade Solanum lycopersicum, commonly known as a tomato plant. The species originated in western South America. The Nahuatl (Aztec language) word tomatl gave rise to the Spanish word tomate, from which the English word tomato derived. Its use as a cultivated food may have originated with the indigenous peoples of Mexico. The Spanish encountered the tomato from their contact with the Aztec during the Spanish colonization of the Americas and brought it to Europe. From there, the tomato was introduced to other parts of the European-colonized world during the 16th century.<br><br>The tomato is consumed in diverse ways, raw or cooked, in many dishes, sauces, salads, and drinks. While tomatoes are fruits – botanically classified as berries – they are commonly used as a vegetable ingredient or side dish.<br><br>Numerous varieties of the tomato plant are widely grown in temperate climates across the world, with greenhouses allowing for the production of tomatoes throughout all seasons of the year. Tomato plants typically grow to 1–3 meters (3–10 ft) in height. They are vines that have a weak stem that sprawls and typically needs support. Indeterminate tomato plants are perennials in their native habitat, but are cultivated as annuals. Determinate, or bush, plants are annuals that stop growing at a certain height and produce a crop all at once. The size of the tomato varies according to the cultivar, with a range of 0.5–4 inches (1.3–10.2 cm) in width.<br><br>(From <a href=\"https://en.wikipedia.org/wiki/Tomato\">Wikipedia</a>)",
            isPlanted = false,
            plantImageUrl = "",
            plantName = "Tomato",
            wateringInterval = 4,
            onBackClick = {
                toast.apply { setText("Clicked Back!") }.show()
            },
            onFabClick = {
                toast.apply { setText("Clicked Fab!") }.show()
            },
            onGalleryClick = {
                toast.apply { setText("Clicked Gallery!") }.show()
            },
            onShareClick = {
                toast.apply { setText("Clicked Share!") }.show()
            }
        )
    }
}