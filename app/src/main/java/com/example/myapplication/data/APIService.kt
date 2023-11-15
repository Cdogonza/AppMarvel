package com.example.myapplication.data

import com.example.myapplication.util.Const
import com.example.myapplication.data.dto.CharactersDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface APIService {

//    @GET
//    suspend fun getSuperHero(@Url url:String): Response<MarvelResponse>

    @GET("/v1/public/characters")
    suspend fun getSuperHero(
        @Query("Apikey") apiKey: String = Const.API_KEY,
        @Query("ts") timeStamp: String = Const.timeStamp,
        @Query("hash") hash: String = Const.hash(),
        @Query("offset") offset:String

    ):CharactersDTO

    @GET("/v1/public/characters")
    suspend fun getAllSearchedCharacters(
        @Query("Apikey") apiKey: String = Const.API_KEY,
        @Query("ts") timeStamp: String = Const.timeStamp,
        @Query("hash") hash: String = Const.hash(),
        @Query("nameStartsWith") search:String
    ): CharactersDTO
}