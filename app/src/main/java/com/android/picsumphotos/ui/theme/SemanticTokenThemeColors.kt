package com.android.picsumphotos.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

interface SemanticTokenThemeColorsContract {
    val primary: Color
    val secondary: Color
    val background: Color
    val surfaceBackground: Color
    val error: Color
    val surfacePrimary: Color
}

object SemanticTokenColorsDark : SemanticTokenThemeColorsContract {
    override val primary = Color(0xffff7535)
    override val secondary = Color(0xffa3c2f5)
    override val background = Color(0xff0e0e11)
    override val surfaceBackground = Color(0xff1c1d22)
    override val error = Color(0xff4e0408)
    override val surfacePrimary = Color(0xfffafafa)
}

object SemanticTokenColorsLight : SemanticTokenThemeColorsContract {
    override val primary = Color(0xffff7535)
    override val secondary = Color(0xff29327a)
    override val background = Color(0xfffafafa)
    override val surfaceBackground = Color(0xffffffff)
    override val error = Color(0xffffd6d8)
    override val surfacePrimary = Color(0xff0e0e11)
}

class SemanticTokenThemeColors(isDarkMode: Boolean):
    SemanticTokenThemeColorsContract by if (isDarkMode) SemanticTokenColorsDark else SemanticTokenColorsLight

val LocalSemanticTokenThemeColors = staticCompositionLocalOf {
    SemanticTokenThemeColors(false)
}

fun SemanticTokenThemeColors.getMaterialColors(isLight: Boolean) : Colors {
    return Colors(
        primary = primary,
        primaryVariant = primary,
        secondary = secondary,
        secondaryVariant = secondary,
        background = background,
        surface = surfaceBackground,
        error = error,
        onError = error,
        onPrimary = surfacePrimary,
        onSecondary = surfacePrimary,
        onBackground = surfacePrimary,
        onSurface = surfacePrimary,
        isLight = isLight
    )
}

object SemanticTokenTheme {
    val colors: SemanticTokenThemeColors
        @Composable
        get() = LocalSemanticTokenThemeColors.current
}