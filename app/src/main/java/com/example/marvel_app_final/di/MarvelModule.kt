package com.example.marvel_app_final.di

import com.example.marvel_app_final.api.MarvelService
import com.example.marvel_app_final.repository.CharactersRepository
import com.example.marvel_app_final.repository.ComicsRepository
import com.example.marvel_app_final.repository.ComicsRepositoryImpl
import com.example.marvel_app_final.repository.GetComicsByCharacterId
import com.example.marvel_app_final.repository.GetComicsByCharacterIdUseCase
import com.example.marvel_app_final.resource.MarvelRetrofit
import com.example.marvel_app_final.viewmodel.AllCharactersViewModel
import com.example.marvel_app_final.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val marvelModule = module {

    single { MarvelRetrofit.createService(MarvelService::class.java) }

    single { CharactersRepository(get()) }

    viewModel { AllCharactersViewModel(get()) }

    single<ComicsRepository> { ComicsRepositoryImpl(get()) }

    single<GetComicsByCharacterIdUseCase> { GetComicsByCharacterId(get()) }

    viewModel { CharacterDetailsViewModel(get()) }
}