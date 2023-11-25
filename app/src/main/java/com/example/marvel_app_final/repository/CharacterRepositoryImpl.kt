package com.example.marvel_app_final.repository

import com.example.marvel_app_final.api.MarvelService
import com.example.marvel_app_final.model.character.Character
import com.example.marvel_app_final.resource.Output
import com.example.marvel_app_final.resource.parseResponse

class CharacterRepositoryImpl(
    private val service: MarvelService

): CharactersRepositoryByComic{
    override suspend fun getCharacterByComicId(comicId: Int): List<Character> {
        return when (val result = service.getCharacterByComicId(comicId).parseResponse()) {
            is Output.Success -> {
                result.value.data.results
            }
            is Output.Failure -> throw GetCharacterException()
            else -> {
                throw GetCharacterException()}
        }
    }
}

interface CharactersRepositoryByComic{
    suspend fun getCharacterByComicId(comicId: Int): List<Character>
}
class GetCharacterException: Exception()