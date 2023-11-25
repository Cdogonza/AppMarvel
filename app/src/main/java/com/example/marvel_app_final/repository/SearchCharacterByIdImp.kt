package com.example.marvel_app_final.repository
import com.example.marvel_app_final.model.character.Character
class SearchCharacterByIdImp (
    private val repository: SearchCharacterByIdRepository): SearchCharacterByIdUseCase {
    override suspend fun invoke(characterId: Int): List<Character> =
        try{
            repository.searchCharacterById(characterId)
        }catch (ex: Exception){
            listOf()
        }
    }




interface SearchCharacterByIdUseCase{
    suspend operator  fun invoke(characterId:Int) : List<Character>
}