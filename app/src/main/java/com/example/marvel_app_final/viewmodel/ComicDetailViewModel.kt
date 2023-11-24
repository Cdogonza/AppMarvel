package com.example.marvel_app_final.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_app_final.model.character.Character
import com.example.marvel_app_final.repository.GetCharacterByComicIdUseCase
import kotlinx.coroutines.launch

class ComicDetailViewModel(
    private val getCharacterByComicIdUseCase: GetCharacterByComicIdUseCase
): ViewModel() {

    private val _charactersLiveData = MutableLiveData<List<Character>>()
    val characters = _charactersLiveData as LiveData<List<Character>>

    fun getCharacterByComicId(comicId: Int) {
        viewModelScope.launch {
            val character = getCharacterByComicIdUseCase(comicId)
            _charactersLiveData.value = character
        }
    }
}