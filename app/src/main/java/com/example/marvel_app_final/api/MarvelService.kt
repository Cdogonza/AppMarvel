package com.example.marvel_app_final.api

import com.example.marvel_app_final.model.character.MarvelResponse
import com.example.marvel_app_final.model.comics.ComicsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    suspend fun getCharacters(@Query("offset") offset: Int): MarvelResponse


    @GET("characters/{characterId}/comics")
    suspend fun getComicsByCharacterId(
        @Path("characterId") characterId: Int): Response<ComicsResponse>

    @GET("comics/{comicId}/characters")
    suspend fun getCharacterByComicId(
        @Path("comicId") comicId: Int): Response<MarvelResponse>

}