package com.android.domain.model

import android.os.Parcelable
import com.android.data.dto.PhotoResponseDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoModel(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val downloadUrl: String,
    val isPortrait: Boolean = false
): Parcelable

fun PhotoResponseDto.toPhotoModel(): PhotoModel {
    return PhotoModel(id, author, width, height, downloadUrl, width<height)
}

fun List<PhotoResponseDto>.toPhotoModelList(): List<PhotoModel> {
    return this.map {
        it.toPhotoModel()
    }
}