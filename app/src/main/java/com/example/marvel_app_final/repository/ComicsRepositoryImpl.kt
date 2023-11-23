package com.example.marvel_app_final.repository

import com.example.marvel_app_final.api.MarvelService
import com.example.marvel_app_final.model.comics.Comic
import com.example.marvel_app_final.resource.Output
import com.example.marvel_app_final.resource.parseResponse
import java.lang.Exception

class ComicsRepositoryImpl(
    private val service: MarvelService
): ComicsRepository {
    override suspend fun getComicsByCharacterId(characterId: Int): List<Comic> {
        return when (val result = service.getComicsByCharacterId(characterId).parseResponse()) {
            is Output.Success -> {
                result.value.data.results
            }
            is Output.Failure -> throw GetComicsException()
            else -> {
                throw GetComicsException()}
        }
    }
}

interface ComicsRepository {
    suspend fun getComicsByCharacterId(characterId: Int): List<Comic>
}

class GetComicsException: Exception()