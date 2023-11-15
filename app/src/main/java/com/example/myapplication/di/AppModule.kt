package com.example.myapplication.di


import com.example.myapplication.data.APIService
import com.example.myapplication.data.repository.MarvelRepositoryImp
import com.example.myapplication.domain.repository.MarvelRepository
import com.example.myapplication.domain.user_cases.CharactersUseCase
import com.example.myapplication.domain.user_cases.SearchCharacterUseCase
import org.koin.dsl.module
import retrofit2.Retrofit
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import com.example.myapplication.ui.characterList.CharactersViewModel
import com.example.myapplication.util.Const.Companion.BASE_URL
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.converter.gson.GsonConverterFactory


val repositoriesModule = module{

    singleOf(::MarvelRepositoryImp) {
        bind<MarvelRepository>()
    }

    }
    val viewModelsModule = module {
        viewModelOf(::CharactersViewModel)
    }


val networkModule = module{
        singleOf(::OkHttpClient){
            provideOkHttpClient()
        }
        single<Retrofit> {
            provideRetrofit(get())
        }

        single<APIService> {
            provideApi(get())
        }
    }
fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}
fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}
fun provideApi(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)

//
//
//@Module
//object AppModule {
//    @Provides
//    @Singleton
//    fun getRetrofit(): APIService {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(APIService::class.java)
//
//
//    }
//    @Provides
//    @Singleton
//    fun provideMarvelRepository(api: APIService): MarvelRepositoryImp {
//        return MarvelRepositoryImp(api)
//    }
//
//    @Provides
//    @Singleton
//    fun charactersUseCase(repository: MarvelRepositoryImp): CharactersUseCase {
//        return CharactersUseCase(repository)
//    }
//
//    @Provides
//    @Singleton
//    fun searchCharacterUseCase(repository: MarvelRepositoryImp): CharactersUseCase {
//        return CharactersUseCase(repository)
//    }
//
//}