package com.example.marvel_app_final.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvel_app_final.api.MarvelService
import com.example.marvel_app_final.constant.Constant
import com.example.marvel_app_final.model.character.Character

class CharacterPagingSource(private val apiService: MarvelService): PagingSource<Int, Character>() {



    override fun getRefreshKey(state: PagingState<Int,Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val LIMIT = Constant.LIMIT
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val offset = params.key ?: 0

            val response = apiService.getCharacters(offset)
            val responseOffset = response.data.offset
            val totalCharacters = response.data.total

            LoadResult.Page(
                data = response.data.results,
                prevKey = null,
                nextKey = if (responseOffset < totalCharacters) {
                    responseOffset + LIMIT
                } else null
            )

        }catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }



}