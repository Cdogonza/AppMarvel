package com.example.myapplication.data.repository

import com.example.myapplication.data.dto.CharactersDTO
import com.example.myapplication.data.APIService
import com.example.myapplication.domain.repository.MarvelRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepositoryImp @Inject constructor(
    private val api: APIService
): MarvelRepository {
    override suspend fun getSuperHero(offset: Int): CharactersDTO{
        return api.getSuperHero(offset = offset.toString())
    }

    override suspend fun getAllSearchedCharacters(search: String): CharactersDTO {
        return api.getAllSearchedCharacters(search = search)
    }

}