package com.android.picsumphotos.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun PicSumPhotosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val extendedColors = SemanticTokenThemeColors(isDarkMode = darkTheme)

    CompositionLocalProvider(values = arrayOf(
        LocalSpacing provides Spacing(),
        LocalFontSize provides FontSize(),
        LocalSemanticTokenThemeColors provides extendedColors
    )) {
        MaterialTheme(
            colors = SemanticTokenTheme.colors.getMaterialColors(!darkTheme),
            typography = Typography,
            content = content
        )
    }
}
