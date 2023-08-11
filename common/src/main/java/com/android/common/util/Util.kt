package com.android.common.util

fun generateImageUrl(id: String, height: Int, width: Int): String {
    return "https://picsum.photos/id/${id}/${width}/${height}"
}