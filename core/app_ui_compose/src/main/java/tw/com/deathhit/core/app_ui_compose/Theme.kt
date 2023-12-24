package tw.com.deathhit.core.app_ui_compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource

private lateinit var darkColor: ColorScheme
private lateinit var lightColor: ColorScheme

@Composable
fun SunflowerCloneTheme(
    content: @Composable () -> Unit
) {
    if (!::darkColor.isInitialized)
        darkColor = darkColorScheme(
            primary = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_primary),
            onPrimary = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_onPrimary),
            primaryContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_primaryContainer),
            onPrimaryContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_onPrimaryContainer),
            secondary = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_secondary),
            onSecondary = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_onSecondary),
            secondaryContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_secondaryContainer),
            onSecondaryContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_onSecondaryContainer),
            tertiary = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_tertiary),
            onTertiary = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_onTertiary),
            tertiaryContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_tertiaryContainer),
            onTertiaryContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_onTertiaryContainer),
            error = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_error),
            errorContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_errorContainer),
            onError = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_onError),
            onErrorContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_onErrorContainer),
            background = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_background),
            onBackground = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_onBackground),
            surface = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_surface),
            onSurface = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_onSurface),
            surfaceVariant = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_surfaceVariant),
            onSurfaceVariant = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_onSurfaceVariant),
            outline = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_outline),
            inverseOnSurface = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_inverseOnSurface),
            inverseSurface = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_inverseSurface),
            inversePrimary = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_inversePrimary),
            surfaceTint = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_surfaceTint),
            outlineVariant = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_outlineVariant),
            scrim = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_dark_scrim),
        )

    if (!::lightColor.isInitialized)
        lightColor = lightColorScheme(
            primary = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_primary),
            onPrimary = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_onPrimary),
            primaryContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_primaryContainer),
            onPrimaryContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_onPrimaryContainer),
            secondary = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_secondary),
            onSecondary = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_onSecondary),
            secondaryContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_secondaryContainer),
            onSecondaryContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_onSecondaryContainer),
            tertiary = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_tertiary),
            onTertiary = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_onTertiary),
            tertiaryContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_tertiaryContainer),
            onTertiaryContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_onTertiaryContainer),
            error = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_error),
            errorContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_errorContainer),
            onError = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_onError),
            onErrorContainer = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_onErrorContainer),
            background = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_background),
            onBackground = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_onBackground),
            surface = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_surface),
            onSurface = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_onSurface),
            surfaceVariant = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_surfaceVariant),
            onSurfaceVariant = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_onSurfaceVariant),
            outline = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_outline),
            inverseOnSurface = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_inverseOnSurface),
            inverseSurface = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_inverseSurface),
            inversePrimary = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_inversePrimary),
            surfaceTint = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_surfaceTint),
            outlineVariant = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_outlineVariant),
            scrim = colorResource(id = tw.com.deathhit.core.app_ui.R.color.md_theme_light_scrim),
        )

    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme())
            darkColor
        else
            lightColor,
        shapes = Shapes,
        typography = Typography,
        content = content
    )
}