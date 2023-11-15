package com.example.myapplication.ui.characterList


import com.example.myapplication.domain.model.Character

class MarvelListState(
    val isLoading: Boolean = false,
    val characterList: List<Character> = emptyList(),
    val error: String = ""
)