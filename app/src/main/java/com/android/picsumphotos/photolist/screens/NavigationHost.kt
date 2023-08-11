package com.android.picsumphotos.photolist.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import com.android.domain.model.PhotoModel
import com.android.picsumphotos.common.NavScreens
import com.android.picsumphotos.common.Constants
import com.android.picsumphotos.photolist.navtype.PhotoModelNavType
import com.android.picsumphotos.photolist.navtype.parcelable
import com.android.picsumphotos.photolist.navtype.toJsonString

@Composable
fun NavigationHost(lazyPagingItems: LazyPagingItems<PhotoModel>, toggleDarkMode: () -> Unit) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavScreens.ListScreen.route
    ) {

        composable(route = NavScreens.ListScreen.route) {
            PhotoListScreen(lazyPagingItems, onToggleClicked = {
                toggleDarkMode.invoke()
            }, onItemClicked = { photoModel ->
                navController.navigate("${NavScreens.DetailsScreen.route}/${photoModel.toJsonString()}")
            })
        }

        composable(
            route = "${NavScreens.DetailsScreen.route}/{${Constants.keyPhotoModel}}",
            arguments = listOf(
            navArgument(Constants.keyPhotoModel) {
                type = PhotoModelNavType()
            })
        ) { navBackStackEntry ->
            val photoModel = navBackStackEntry.arguments?.parcelable<PhotoModel>(Constants.keyPhotoModel)
            photoModel?.let {
                PhotoDetailsScreen(it) {
                    navController.navigateUp()
                }
            }
        }
    }
}