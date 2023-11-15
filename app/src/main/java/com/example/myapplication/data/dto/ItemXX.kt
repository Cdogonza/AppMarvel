package com.example.myapplication.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemXX (
    @SerializedName("resourceURI")
    @Expose
    val resourceURI: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("type")
    @Expose
    val type: String
        )