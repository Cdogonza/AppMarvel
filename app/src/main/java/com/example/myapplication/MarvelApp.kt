package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.networkModule
import com.example.myapplication.di.repositoriesModule
import com.example.myapplication.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MarvelApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MarvelApp)
            modules(
                listOf(
                    repositoriesModule,
                    viewModelsModule,
                    networkModule
                )
            )
        }

    }
    }


