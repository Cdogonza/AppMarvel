package com.example.myapplication.domain.model

import com.example.myapplication.data.dto.Thumbnail
import com.example.myapplication.data.dto.Url
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// MarvelComic is a data class that represents a comic from the Marvel API.
class MarvelComic(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("modified")
    @Expose
    val modified: String,
    @SerializedName("thumbnail")
    @Expose
    val thumbnail: Thumbnail,
    @SerializedName("thumbnailExt")
    @Expose
    val thumbnailExt:String,
    @SerializedName("resourceURI")
    @Expose
    val resourceURI: String,
    @SerializedName("urls")
    @Expose
    val urls: List<Url>
)