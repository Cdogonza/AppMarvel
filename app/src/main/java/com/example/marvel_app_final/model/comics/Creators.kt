package com.example.marvel_app_final.model.comics

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Creators(
    val available: Int,
    val collectionURI: String,
    val returned: Int
) : Parcelable