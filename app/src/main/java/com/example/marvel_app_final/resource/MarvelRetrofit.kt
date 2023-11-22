package com.example.marvel_app_final.resource

import com.example.marvel_app_final.constant.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MarvelRetrofit {

    companion object {
        private lateinit var retrofit: Retrofit
        private const val BASE_URL = Constant.BASE_URL

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val chainRequest = chain.request()
                val urlOriginal = chainRequest.url
                val ts = "1"
                val httpUrl = urlOriginal.newBuilder()
                    .addQueryParameter("ts", ts)
                    .addQueryParameter("apikey", "f71a18992b53dc1c85c37a560b2b4690")

                    .addQueryParameter("hash", "0e20265d85d437f34515d327c28674f4")
                    .build()

                chain.proceed(chainRequest.newBuilder().url(httpUrl).build())
            }
            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}