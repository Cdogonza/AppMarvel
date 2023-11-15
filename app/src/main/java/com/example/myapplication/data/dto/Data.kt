package com.example.myapplication.data.dto

import com.example.myapplication.domain.model.Character
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("offset")
    @Expose
    val offset: Int,
    @SerializedName("limit")
    @Expose
    val limit: Int,
    @SerializedName("total")
    @Expose
    val total: Int,
    @SerializedName("count")
    @Expose
    val count: Int,
    @SerializedName("results")
    @Expose
    val results: List<Character>
)