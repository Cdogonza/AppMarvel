package com.example.marvel_app_final.model.character

import com.example.marvel_app_final.model.character.Character


data class Data(
    var offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
)
