package tw.com.deathhit.core.sunflower_clone_ui_compose.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun SunflowerCloneTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme())
            darkColorScheme
        else
            lightColorScheme,
        shapes = Shapes,
        typography = Typography,
        content = content
    )
}