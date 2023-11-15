package com.example.myapplication.domain.repository

import com.example.myapplication.data.dto.CharactersDTO
import retrofit2.Response
import javax.inject.Singleton

@Singleton
interface MarvelRepository {

    suspend fun getSuperHero(offset:Int): CharactersDTO
    suspend fun getAllSearchedCharacters(search:String): CharactersDTO
}