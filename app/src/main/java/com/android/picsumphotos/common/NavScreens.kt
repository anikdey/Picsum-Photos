package com.android.picsumphotos.common

sealed class NavScreens(val route: String) {
    object ListScreen: NavScreens("picture_list")
    object DetailsScreen: NavScreens("picture_details")
}
