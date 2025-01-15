package tw.com.deathhit.core.sunflower_clone_ui_compose

import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import tw.com.deathhit.core.sunflower_clone_ui_compose.style.Shapes
import tw.com.deathhit.core.sunflower_clone_ui_compose.style.Typography

@Composable
fun SunflowerCloneTheme(
    content: @Composable () -> Unit
) {
    var colorScheme: ColorScheme? by remember { mutableStateOf(null) }

    MaterialTheme(
        colorScheme = colorScheme ?: SunflowerCloneTheme.getColorScheme().also { colorScheme = it },
        shapes = Shapes,
        typography = Typography,
        content = content
    )
}

object SunflowerCloneTheme {
    private val typedValue = TypedValue()

    @Composable
    @ReadOnlyComposable
    internal fun getColorScheme() = if (isSystemInDarkTheme())
        darkColorScheme(
            primary = Colors.primary,
            onPrimary = Colors.onPrimary,
            primaryContainer = Colors.primaryContainer,
            onPrimaryContainer = Colors.onPrimaryContainer,
            inversePrimary = Colors.inversePrimary,
            secondary = Colors.secondary,
            onSecondary = Colors.onSecondary,
            secondaryContainer = Colors.secondaryContainer,
            onSecondaryContainer = Colors.onSecondaryContainer,
            tertiary = Colors.tertiary,
            onTertiary = Colors.onTertiary,
            tertiaryContainer = Colors.tertiaryContainer,
            onTertiaryContainer = Colors.onTertiaryContainer,
            background = Colors.background,
            onBackground = Colors.onBackground,
            surface = Colors.surface,
            onSurface = Colors.onSurface,
            surfaceVariant = Colors.surfaceVariant,
            onSurfaceVariant = Colors.onSurfaceVariant,
            inverseSurface = Colors.inverseSurface,
            inverseOnSurface = Colors.inverseOnSurface,
            error = Colors.error,
            onError = Colors.onError,
            errorContainer = Colors.errorContainer,
            onErrorContainer = Colors.onErrorContainer,
            outline = Colors.outline,
            outlineVariant = Colors.outlineVariant,
            surfaceBright = Colors.surfaceBright,
            surfaceContainer = Colors.surfaceContainer,
            surfaceContainerHigh = Colors.surfaceContainerHigh,
            surfaceContainerHighest = Colors.surfaceContainerHighest,
            surfaceContainerLow = Colors.surfaceContainerLow,
            surfaceContainerLowest = Colors.surfaceContainerLowest,
            surfaceDim = Colors.surfaceDim
        )
    else
        lightColorScheme(
            primary = Colors.primary,
            onPrimary = Colors.onPrimary,
            primaryContainer = Colors.primaryContainer,
            onPrimaryContainer = Colors.onPrimaryContainer,
            inversePrimary = Colors.inversePrimary,
            secondary = Colors.secondary,
            onSecondary = Colors.onSecondary,
            secondaryContainer = Colors.secondaryContainer,
            onSecondaryContainer = Colors.onSecondaryContainer,
            tertiary = Colors.tertiary,
            onTertiary = Colors.onTertiary,
            tertiaryContainer = Colors.tertiaryContainer,
            onTertiaryContainer = Colors.onTertiaryContainer,
            background = Colors.background,
            onBackground = Colors.onBackground,
            surface = Colors.surface,
            onSurface = Colors.onSurface,
            surfaceVariant = Colors.surfaceVariant,
            onSurfaceVariant = Colors.onSurfaceVariant,
            inverseSurface = Colors.inverseSurface,
            inverseOnSurface = Colors.inverseOnSurface,
            error = Colors.error,
            onError = Colors.onError,
            errorContainer = Colors.errorContainer,
            onErrorContainer = Colors.onErrorContainer,
            outline = Colors.outline,
            outlineVariant = Colors.outlineVariant,
            surfaceBright = Colors.surfaceBright,
            surfaceContainer = Colors.surfaceContainer,
            surfaceContainerHigh = Colors.surfaceContainerHigh,
            surfaceContainerHighest = Colors.surfaceContainerHighest,
            surfaceContainerLow = Colors.surfaceContainerLow,
            surfaceContainerLowest = Colors.surfaceContainerLowest,
            surfaceDim = Colors.surfaceDim
        )

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
        val surfaceContainerHighest: Color
            @Composable @ReadOnlyComposable get() = colorAttribute(com.google.android.material.R.attr.colorSurfaceContainerHighest)
    }
}