package com.example.marvel_app_final.repository

import com.example.marvel_app_final.api.MarvelService
import com.example.marvel_app_final.model.character.Character
import com.example.marvel_app_final.resource.Output
import com.example.marvel_app_final.resource.parseResponse

class SearchCharacterByIdRepository (
    private val service: MarvelService
): SearchRepositoryById{
    override suspend fun searchCharacterById(characterId: Int): List<Character> {
        return when (val result = service.searchCharacterById(characterId).parseResponse()){
            is Output.Success -> {
                result.value.data.results
            }
            is Output.Failure -> throw  SearchCharacterById()
            else-> {
                throw  SearchCharacterById()}
            }
        }
    }




 interface SearchRepositoryById {
    suspend fun searchCharacterById(characterId: Int): List<Character>

}
class SearchCharacterById : Exception()