package com.android.picsumphotos.photolist.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.domain.model.PhotoModel
import com.android.picsumphotos.R
import com.android.picsumphotos.common.AppTopBar
import com.android.picsumphotos.common.Constants
import com.android.common.util.generateImageUrl
import com.android.picsumphotos.ui.theme.SemanticTokenTheme
import com.android.picsumphotos.ui.theme.fontSize
import com.android.picsumphotos.ui.theme.spacing

@Composable
fun PhotoDetailsScreen(
    photoModel: PhotoModel,
    onBackPressed: (() -> Unit)? = null) {

    Scaffold(
        topBar = {
            AppTopBar(title = photoModel.author,
            backArrowColor = SemanticTokenTheme.colors.surfacePrimary,
            elevation = MaterialTheme.spacing.small) {
                onBackPressed?.invoke()
            }
        }
    ) { padding->
        Column(modifier = Modifier.fillMaxSize().padding(padding)
            .testTag(if(photoModel.isPortrait) Constants.portraitTag else Constants.landscapeTag),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = if(photoModel.isPortrait) Arrangement.Top else Arrangement.Center
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(generateImageUrl(photoModel.id, photoModel.height, photoModel.width))
                    .crossfade(true)
                    .build(),
                contentDescription = photoModel.author
            )

            Text("${stringResource(R.string.author)} ${photoModel.author}",
                fontSize = MaterialTheme.fontSize._20sp,
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.spacing.small,
                        vertical = MaterialTheme.spacing.medium
                    ))

        }
    }
}