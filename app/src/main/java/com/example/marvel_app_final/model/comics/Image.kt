package com.example.marvel_app_final.model.comics

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val extension: String,
    val path: String
) : Parcelable