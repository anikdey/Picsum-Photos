package com.android.picsumphotos.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.android.picsumphotos.R
import com.android.picsumphotos.ui.theme.SemanticTokenTheme

@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color = SemanticTokenTheme.colors.surfacePrimary,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    backgroundColor: Color = SemanticTokenTheme.colors.surfaceBackground,
    backArrowColor: Color = SemanticTokenTheme.colors.surfacePrimary,
    titleAlignment: TextAlign = TextAlign.Start,
    onBackClicked: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = backgroundColor,
        elevation = elevation,
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Medium,
                color = titleColor,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.fillMaxWidth(),
                textAlign = titleAlignment
            )
        },
        navigationIcon = {
            onBackClicked?.let {
                IconButton(
                    modifier = Modifier.testTag("toolbar.btn.back"),
                    onClick = it
                ) {
                    Icon(Icons.Outlined.ArrowBack, "", tint = backArrowColor)
                }
            }
        },
        actions = {
            // Todo additional thins to handle if any
        }
    )
}

@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color = SemanticTokenTheme.colors.surfacePrimary,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    backgroundColor: Color = SemanticTokenTheme.colors.surfaceBackground,
    titleAlignment: TextAlign = TextAlign.Start,
    onToggleClicked: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = backgroundColor,
        elevation = elevation,
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Medium,
                color = titleColor,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.fillMaxWidth(),
                textAlign = titleAlignment
            )
        },
        actions = {
            IconButton(
                modifier = Modifier.testTag("toolbar.btn.back"),
                onClick = {
                    onToggleClicked.invoke()
                }
            ) {
                Row {
                    Text(text = "Toggle Theme", color = titleColor)
                    //Icon(Icons.Outlined.Refresh, "", tint = titleColor)
                    Icon(painterResource(id = R.drawable.ic_toggle), "", tint = titleColor)
                }
            }
        }
    )
}