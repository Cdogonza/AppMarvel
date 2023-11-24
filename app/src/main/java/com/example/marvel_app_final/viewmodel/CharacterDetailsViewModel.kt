package com.example.marvel_app_final.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_app_final.model.comics.Comic
import com.example.marvel_app_final.repository.GetComicsByCharacterIdUseCase
import kotlinx.coroutines.launch


class CharacterDetailsViewModel(
    private val getComicsByCharacterIdUseCase: GetComicsByCharacterIdUseCase
): ViewModel() {

    private val _comicsLiveData = MutableLiveData<List<Comic>>()
    val character = _comicsLiveData as LiveData<List<Comic>>

    fun getComicsByCharacterId(characterId: Int) {
        viewModelScope.launch {
            val comics = getComicsByCharacterIdUseCase(characterId)
            _comicsLiveData.value = comics
        }
    }
}