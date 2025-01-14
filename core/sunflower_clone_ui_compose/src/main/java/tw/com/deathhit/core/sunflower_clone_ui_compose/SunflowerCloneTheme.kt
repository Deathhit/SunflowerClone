package tw.com.deathhit.core.sunflower_clone_ui_compose

import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import tw.com.deathhit.core.sunflower_clone_ui_compose.style.Shapes
import tw.com.deathhit.core.sunflower_clone_ui_compose.style.Typography

@Composable
fun SunflowerCloneTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme())
            darkColorScheme(
                primary = SunflowerCloneTheme.Colors.primary,
                onPrimary = SunflowerCloneTheme.Colors.onPrimary,
                primaryContainer = SunflowerCloneTheme.Colors.primaryContainer,
                onPrimaryContainer = SunflowerCloneTheme.Colors.onPrimaryContainer,
                inversePrimary = SunflowerCloneTheme.Colors.inversePrimary,
                secondary = SunflowerCloneTheme.Colors.secondary,
                onSecondary = SunflowerCloneTheme.Colors.onSecondary,
                secondaryContainer = SunflowerCloneTheme.Colors.secondaryContainer,
                onSecondaryContainer = SunflowerCloneTheme.Colors.onSecondaryContainer,
                tertiary = SunflowerCloneTheme.Colors.tertiary,
                onTertiary = SunflowerCloneTheme.Colors.onTertiary,
                tertiaryContainer = SunflowerCloneTheme.Colors.tertiaryContainer,
                onTertiaryContainer = SunflowerCloneTheme.Colors.onTertiaryContainer,
                background = SunflowerCloneTheme.Colors.background,
                onBackground = SunflowerCloneTheme.Colors.onBackground,
                surface = SunflowerCloneTheme.Colors.surface,
                onSurface = SunflowerCloneTheme.Colors.onSurface,
                surfaceVariant = SunflowerCloneTheme.Colors.surfaceVariant,
                onSurfaceVariant = SunflowerCloneTheme.Colors.onSurfaceVariant,
                inverseSurface = SunflowerCloneTheme.Colors.inverseSurface,
                inverseOnSurface = SunflowerCloneTheme.Colors.inverseOnSurface,
                error = SunflowerCloneTheme.Colors.error,
                onError = SunflowerCloneTheme.Colors.onError,
                errorContainer = SunflowerCloneTheme.Colors.errorContainer,
                onErrorContainer = SunflowerCloneTheme.Colors.onErrorContainer,
                outline = SunflowerCloneTheme.Colors.outline,
                outlineVariant = SunflowerCloneTheme.Colors.outlineVariant,
                surfaceBright = SunflowerCloneTheme.Colors.surfaceBright,
                surfaceContainer = SunflowerCloneTheme.Colors.surfaceContainer,
                surfaceContainerHigh = SunflowerCloneTheme.Colors.surfaceContainerHigh,
                surfaceContainerHighest = SunflowerCloneTheme.Colors.surfaceContainerHighest,
                surfaceContainerLow = SunflowerCloneTheme.Colors.surfaceContainerLow,
                surfaceContainerLowest = SunflowerCloneTheme.Colors.surfaceContainerLowest,
                surfaceDim = SunflowerCloneTheme.Colors.surfaceDim
            )
        else
            lightColorScheme(
                primary = SunflowerCloneTheme.Colors.primary,
                onPrimary = SunflowerCloneTheme.Colors.onPrimary,
                primaryContainer = SunflowerCloneTheme.Colors.primaryContainer,
                onPrimaryContainer = SunflowerCloneTheme.Colors.onPrimaryContainer,
                inversePrimary = SunflowerCloneTheme.Colors.inversePrimary,
                secondary = SunflowerCloneTheme.Colors.secondary,
                onSecondary = SunflowerCloneTheme.Colors.onSecondary,
                secondaryContainer = SunflowerCloneTheme.Colors.secondaryContainer,
                onSecondaryContainer = SunflowerCloneTheme.Colors.onSecondaryContainer,
                tertiary = SunflowerCloneTheme.Colors.tertiary,
                onTertiary = SunflowerCloneTheme.Colors.onTertiary,
                tertiaryContainer = SunflowerCloneTheme.Colors.tertiaryContainer,
                onTertiaryContainer = SunflowerCloneTheme.Colors.onTertiaryContainer,
                background = SunflowerCloneTheme.Colors.background,
                onBackground = SunflowerCloneTheme.Colors.onBackground,
                surface = SunflowerCloneTheme.Colors.surface,
                onSurface = SunflowerCloneTheme.Colors.onSurface,
                surfaceVariant = SunflowerCloneTheme.Colors.surfaceVariant,
                onSurfaceVariant = SunflowerCloneTheme.Colors.onSurfaceVariant,
                inverseSurface = SunflowerCloneTheme.Colors.inverseSurface,
                inverseOnSurface = SunflowerCloneTheme.Colors.inverseOnSurface,
                error = SunflowerCloneTheme.Colors.error,
                onError = SunflowerCloneTheme.Colors.onError,
                errorContainer = SunflowerCloneTheme.Colors.errorContainer,
                onErrorContainer = SunflowerCloneTheme.Colors.onErrorContainer,
                outline = SunflowerCloneTheme.Colors.outline,
                outlineVariant = SunflowerCloneTheme.Colors.outlineVariant,
                surfaceBright = SunflowerCloneTheme.Colors.surfaceBright,
                surfaceContainer = SunflowerCloneTheme.Colors.surfaceContainer,
                surfaceContainerHigh = SunflowerCloneTheme.Colors.surfaceContainerHigh,
                surfaceContainerHighest = SunflowerCloneTheme.Colors.surfaceContainerHighest,
                surfaceContainerLow = SunflowerCloneTheme.Colors.surfaceContainerLow,
                surfaceContainerLowest = SunflowerCloneTheme.Colors.surfaceContainerLowest,
                surfaceDim = SunflowerCloneTheme.Colors.surfaceDim
            ),
        shapes = Shapes,
        typography = Typography,
        content = content
    )
}

object SunflowerCloneTheme {
    private val typedValue = TypedValue()

    @Composable
    @ReadOnlyComposable
    private fun colorAttribute(@AttrRes attrId: Int) =
        colorResource(typedValue.apply {
            LocalContext.current.theme.resolveAttribute(
                attrId,
                this,
                true
            )
        }.resourceId)

    object Colors {
        val primary: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorPrimary)
        val onPrimary: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorOnPrimary)
        val primaryContainer: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorPrimaryContainer)
        val onPrimaryContainer: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorOnPrimaryContainer)
        val secondary: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorSecondary)
        val onSecondary: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorOnSecondary)
        val secondaryContainer: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorSecondaryContainer)
        val onSecondaryContainer: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorOnSecondaryContainer)
        val tertiary: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorTertiary)
        val onTertiary: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorOnTertiary)
        val tertiaryContainer: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorTertiaryContainer)
        val onTertiaryContainer: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorOnTertiaryContainer)
        val error: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorError)
        val onError: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorOnError)
        val errorContainer: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorErrorContainer)
        val onErrorContainer: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorOnErrorContainer)
        val background: Color @Composable @ReadOnlyComposable get() = colorAttribute(android.R.attr.colorBackground)
        val onBackground: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorOnBackground)
        val surface: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorSurface)
        val onSurface: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorOnSurface)
        val surfaceVariant: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorSurfaceVariant)
        val onSurfaceVariant: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorOnSurfaceVariant)
        val outline: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorOutline)
        val outlineVariant: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorOutlineVariant)
        val inverseSurface: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorSurfaceInverse)
        val inverseOnSurface: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorOnSurfaceInverse)
        val inversePrimary: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorPrimaryInverse)
        val surfaceDim: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorSurfaceDim)
        val surfaceBright: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorSurfaceBright)
        val surfaceContainerLowest: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorSurfaceContainerLowest)
        val surfaceContainerLow: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorSurfaceContainerLow)
        val surfaceContainer: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorSurfaceContainer)
        val surfaceContainerHigh: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorSurfaceContainerHigh)
        val surfaceContainerHighest: Color @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorSurfaceContainerHighest)
    }
}