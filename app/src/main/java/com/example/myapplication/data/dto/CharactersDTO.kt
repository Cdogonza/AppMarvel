package com.example.myapplication.data.dto

import com.example.myapplication.data.dto.Data
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharactersDTO (
    @SerializedName("code")
    @Expose
    val code: Int,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("copyright")
    @Expose
    val copyright: String,
    @SerializedName("attributionText")
    @Expose
    val attributionText: String,
    @SerializedName("attributionHTML")
    @Expose
    val attributionHTML: String,
    @SerializedName("etag")
    @Expose
    val etag: String,
    @SerializedName("data")
    @Expose
    val `data`: Data
)