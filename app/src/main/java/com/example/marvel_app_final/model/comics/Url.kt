package com.example.marvel_app_final.model.comics
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Url(
    val type: String,
    val url: String
) : Parcelable