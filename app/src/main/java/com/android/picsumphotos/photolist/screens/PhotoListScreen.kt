package com.android.picsumphotos.photolist.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.domain.model.PhotoModel
import com.android.picsumphotos.R
import com.android.picsumphotos.common.AppTopBar
import com.android.common.util.generateImageUrl
import com.android.picsumphotos.ui.theme.fontSize
import com.android.picsumphotos.ui.theme.spacing

@Composable
fun PhotoListScreen(lazyPagingItems: LazyPagingItems<PhotoModel>,
                    onItemClicked: (PhotoModel) -> Unit,
                    onToggleClicked: () -> Unit) {
    Scaffold(
        topBar = {
            AppTopBar(title = stringResource(R.string.list_title),
                elevation = MaterialTheme.spacing.small,
                onToggleClicked = {
                    onToggleClicked.invoke()
            })
        }
    ) { padding ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = if (lazyPagingItems.itemCount < 1)
                    Arrangement.Center
                else
                    Arrangement.Top
            ){

                items(count = lazyPagingItems.itemCount) { index ->
                    lazyPagingItems[index]?.let {photoModel->
                        PhotoItem(photoModel = photoModel) {
                            onItemClicked(photoModel)
                        }
                    }
                }

                when (val state = lazyPagingItems.loadState.refresh) {
                    LoadState.Loading -> {
                        item {
                            LoadingScreen(circularProgressSize = MaterialTheme.spacing.extraLarge)
                        }
                    }
                    is LoadState.Error -> {
                        item {
                            PaginationErrorItem(errorMessage = state.error.localizedMessage) {
                                lazyPagingItems.refresh()
                            }
                        }
                    }
                    is LoadState.NotLoading -> {
                        if (lazyPagingItems.itemCount < 1) {
                            item {
                                EmptyItem()
                            }
                        }
                    }
                }

                when (val state = lazyPagingItems.loadState.append) {
                    LoadState.Loading -> {
                        item {
                            LoadingScreen()
                        }
                    }
                    is LoadState.Error -> {
                        item {
                            PaginationErrorItem(errorMessage = state.error.localizedMessage) {
                                lazyPagingItems.refresh()
                            }
                        }
                    }
                    is LoadState.NotLoading -> Unit
                }
            }
        }
    }

}

@Composable
fun PhotoItem(photoModel: PhotoModel, onItemClicked: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onItemClicked()
        }) {
        Card(elevation = MaterialTheme.spacing.small) {
            Column(modifier = Modifier.fillMaxSize()) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(generateImageUrl(photoModel.id, photoModel.height, photoModel.width))
                        .crossfade(true)
                        .build(),
                    contentDescription = photoModel.author,
                )

                Text("${stringResource(R.string.author)} ${photoModel.author}", fontSize = MaterialTheme.fontSize._20sp,
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = MaterialTheme.spacing.small,
                            vertical = MaterialTheme.spacing.medium
                        ))

            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
    }

}