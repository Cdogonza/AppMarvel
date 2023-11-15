package com.example.myapplication.domain.model

import com.example.myapplication.data.dto.Comics
import com.example.myapplication.data.dto.Events
import com.example.myapplication.data.dto.Series
import com.example.myapplication.data.dto.Stories
import com.example.myapplication.data.dto.Thumbnail
import com.example.myapplication.data.dto.Url
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
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
    @SerializedName("comics")
    @Expose
    val comics: Comics,
    @SerializedName("series")
    @Expose
    val series: Series,
    @SerializedName("stories")
    @Expose
    val stories: Stories,
    @SerializedName("events")
    @Expose
    val events: Events,
    @SerializedName("urls")
    @Expose
    val urls: List<Url>
)