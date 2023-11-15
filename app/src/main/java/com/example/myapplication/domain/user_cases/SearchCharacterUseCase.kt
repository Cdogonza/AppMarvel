package com.example.myapplication.domain.user_cases

import com.example.myapplication.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import com.example.myapplication.util.Response
import java.io.IOException
import com.example.myapplication.domain.model.Character
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SearchCharacterUseCase @Inject constructor(
    private val repository: MarvelRepository
) {
    operator fun invoke(search: String) : Flow<Response<List<Character>>> = flow {
        try {
            emit(Response.Loading())
            val list = repository.getAllSearchedCharacters(search).data.results.map {

                it.to(Character::class.java) as Character
            }
            emit(Response.Success<List<Character>>(list))
        } catch (e: HttpException) {
            emit(Response.Error<List<Character>>(e.printStackTrace().toString()))
        }
        catch (e: IOException) {
            emit(Response.Error<List<Character>>(e.printStackTrace().toString()))
        }
    }
}