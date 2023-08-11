package com.android.picsumphotos.photolist.navtype

import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.android.domain.model.PhotoModel
import com.google.gson.Gson

class PhotoModelNavType : NavType<PhotoModel>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): PhotoModel? {
        return bundle.parcelable(key)
    }

    override fun parseValue(value: String): PhotoModel {
        return Gson().fromJson(value, PhotoModel::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: PhotoModel) {
        bundle.putParcelable(key, value)
    }
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

fun PhotoModel.toJsonString(): String {
   return Uri.encode(Gson().toJson(this))
}