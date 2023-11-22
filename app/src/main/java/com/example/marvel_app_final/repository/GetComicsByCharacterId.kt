package com.example.marvel_app_final.repository

import com.example.marvel_app_final.model.comics.Comic
import java.lang.Exception

class GetComicsByCharacterId(
    private val repository: ComicsRepository): GetComicsByCharacterIdUseCase {
    override suspend fun invoke(characterId: Int): List<Comic> = try {
        repository.getComicsByCharacterId(characterId)
    } catch (ex: Exception) {
        listOf()
    }
}

interface GetComicsByCharacterIdUseCase {
    suspend operator fun invoke(characterId: Int): List<Comic>
}