package com.example.marvel_app_final.model.comics

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Date(
    val date: String,
    val type: String
) : Parcelable