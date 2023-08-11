package com.android.picsumphotos.photolist.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.android.picsumphotos.R
import com.android.picsumphotos.ui.theme.SemanticTokenTheme
import com.android.picsumphotos.ui.theme.spacing

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    circularProgressSize: Dp = MaterialTheme.spacing.large,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.small),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(size = circularProgressSize),
            color = SemanticTokenTheme.colors.primary,
            strokeWidth = MaterialTheme.spacing.extraSmall
        )
    }
}

@Composable
fun PaginationErrorItem(
    modifier: Modifier = Modifier,
    errorMessage: String?,
    onTryAgainClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        errorMessage?.let {
            Text(text = it)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }

        Button(
            modifier = modifier,
            onClick = onTryAgainClick
        ) {
            Text(text = stringResource(R.string.try_again))
        }
    }
}

@Composable
fun EmptyItem(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(all = MaterialTheme.spacing.large),
            text = stringResource(R.string.nothing_found)
        )
    }
}