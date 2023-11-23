package com.example.marvel_app_final.model.character




data class Data(
    var offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<com.example.marvel_app_final.model.character.Character>
)
