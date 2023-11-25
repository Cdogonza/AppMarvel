package com.example.marvel_app_final.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.marvel_app_final.model.character.Character
import com.example.marvel_app_final.repository.CharactersRepository
import com.example.marvel_app_final.repository.SearchCharacterByIdRepository
import kotlinx.coroutines.flow.Flow

class AllCharactersViewModel(
    private val repository: CharactersRepository
) : ViewModel() {

    fun getListData(): Flow<PagingData<Character>> {
        return repository.getResultStream()
            .cachedIn(viewModelScope)
    }



}


