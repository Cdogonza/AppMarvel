package com.example.marvel_app_final.model.comics

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comic(
    val characters: Characters, //este
    val creators: Creators,
    val dates: List<Date>,
    val description: String, //este
    val diamondCode: String,
    val digitalId: Int, //este?
    val format: String,
    val id: Int,
    val images: List<Image>,
    val issueNumber: Int,
    val modified: String,
    val pageCount: Int,
    val resourceURI: String,
    val thumbnail: Thumbnail,
    val title: String, //este
    val urls: List<Url>
) : Parcelable