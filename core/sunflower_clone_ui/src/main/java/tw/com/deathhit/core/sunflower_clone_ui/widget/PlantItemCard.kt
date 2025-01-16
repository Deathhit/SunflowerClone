package tw.com.deathhit.core.sunflower_clone_ui.widget

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import tw.com.deathhit.core.sunflower_clone_ui.SunflowerCloneTheme

@Composable
fun PlantItemCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val cornerRadius = SunflowerCloneTheme.Dimens.plantItemCornerRadius
    val containerColor = SunflowerCloneTheme.Colors.surfaceContainer

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = RoundedCornerShape(bottomEnd = cornerRadius, topStart = cornerRadius),
        content = content
    )
}

@Suppress("UnusedReceiverParameter")
@Composable
@Preview
private fun SunflowerCloneTheme.PlantItemCardPreview() {
    val padding = SunflowerCloneTheme.Dimens.marginNormal

    SunflowerCloneTheme {
        PlantItemCard(
            modifier = Modifier
                .padding(padding)
        ) {
            Text(
                text = "SunflowerCloneTheme.PlantItemCardPreview",
                modifier = Modifier
                    .padding(padding)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}