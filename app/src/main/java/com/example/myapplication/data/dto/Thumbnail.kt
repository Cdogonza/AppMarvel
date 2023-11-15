package com.example.myapplication.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("path")
    @Expose
    val path: String,
    @SerializedName("extension")
    @Expose
    val extension: String
)
