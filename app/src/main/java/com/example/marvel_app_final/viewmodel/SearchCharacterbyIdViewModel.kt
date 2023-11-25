package com.example.marvel_app_final.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_app_final.model.character.Character

import com.example.marvel_app_final.repository.SearchCharacterByIdUseCase
import kotlinx.coroutines.launch

class SearchCharacterbyIdViewModel(
    private val repository: SearchCharacterByIdUseCase
): ViewModel() {
    private val _searchCharacterLiveData = MutableLiveData<List<Character>>()
    val character = _searchCharacterLiveData as LiveData<List<Character>>
    fun searchCharacterById(characterId: Int){
        viewModelScope.launch {
            val character = repository(characterId)
            _searchCharacterLiveData.value = character
        }
    }
}