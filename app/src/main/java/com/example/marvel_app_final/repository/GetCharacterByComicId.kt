package com.example.marvel_app_final.repository

import com.example.marvel_app_final.model.character.Character
import com.example.marvel_app_final.model.comics.Comic
import java.lang.Exception

class GetCharacterByComicId(
    private val repository: CharactersRepositoryByComic): GetCharacterByComicIdUseCase {
    override suspend fun invoke(comicId: Int): List<Character> = try {
        repository.getCharacterByComicId(comicId)
    } catch (ex: Exception) {
        listOf()
    }
}
interface GetCharacterByComicIdUseCase {
    suspend operator fun invoke(comicId: Int): List<Character>
}

