package com.example.marvel_app_final.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.marvel_app_final.api.MarvelService
import com.example.marvel_app_final.constant.Constant
import kotlinx.coroutines.flow.Flow
import com.example.marvel_app_final.model.character.Character

class CharactersRepository(private val apiService: MarvelService) {

    fun getResultStream(): Flow<PagingData<Character>> {
        return Pager(config = PagingConfig(pageSize = pageSize, maxSize = pageMax),
            pagingSourceFactory = { CharacterPagingSource(apiService) }
        ).flow
    }

    companion object {
        const val pageSize: Int = Constant.PAGE_SIZE
        const val pageMax: Int = Constant.MAX_PAGE
    }
}