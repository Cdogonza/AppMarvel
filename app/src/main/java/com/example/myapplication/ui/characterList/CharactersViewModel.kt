package com.example.myapplication.ui.characterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.MainActivity
import com.example.myapplication.data.repository.MarvelRepositoryImp
import com.example.myapplication.domain.repository.MarvelRepository
import com.example.myapplication.util.Response
import com.example.myapplication.domain.user_cases.CharactersUseCase
import com.example.myapplication.domain.user_cases.SearchCharacterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import javax.inject.Inject
import kotlin.reflect.KProperty


class CharactersViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase,
    private val searchCharacterUseCase: SearchCharacterUseCase
) : ViewModel(){

    private val marvelValue = MutableStateFlow(MarvelListState())
    var _marvelValue : StateFlow<MarvelListState> = marvelValue


    fun getAllCharactersData(offset: Int) = viewModelScope.launch(Dispatchers.IO){
        charactersUseCase(offset = offset).collect() {
            when(it){
                is Response.Success -> {
                    marvelValue.value = MarvelListState(characterList = it.data?: emptyList())

                }
                is Response.Loading -> {
                    marvelValue.value = MarvelListState(isLoading = true)
                }
                is Response.Error -> {
                    marvelValue.value = MarvelListState(error = it.message?:"Error inesperado")
                }
            }
        }
    }
    fun getSearchedCharacters(search: String) = viewModelScope.launch(Dispatchers.IO){
        searchCharacterUseCase.invoke(search = search).collect() {
            when(it){
                is Response.Success -> {
                    marvelValue.value = MarvelListState(characterList = it.data?: emptyList())

                }
                is Response.Loading -> {
                    marvelValue.value = MarvelListState(isLoading = true)
                }
                is Response.Error -> {
                    marvelValue.value = MarvelListState(error = it.message?:"Error inesperado")
                }
            }
        }
    }



}