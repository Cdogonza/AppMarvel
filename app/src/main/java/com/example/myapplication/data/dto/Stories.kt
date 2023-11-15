package com.example.myapplication.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Stories(
    @SerializedName("available")
    @Expose
    val available: Int,
    @SerializedName("collectionURI")
    @Expose
    val collectionURI: String,
    @SerializedName("items")
    @Expose
    val items: List<ItemXX>,
    @SerializedName("returned")
    @Expose
    val returned: Int
)