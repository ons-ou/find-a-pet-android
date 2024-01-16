package com.gl4.project.data

import com.gl4.project.data.api.AnimalsApi
import com.gl4.project.data.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val BASE_URL = "https://api.petfinder.com/v2/"

    private val retrofitWithoutInterceptor: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    var apiServiceNoI: AnimalsApi = retrofitWithoutInterceptor.create(AnimalsApi::class.java)
    var apiService: AnimalsApi = apiServiceNoI


    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()
    }

    fun setAuthInterceptor() {

        val retrofitWithInterceptor = retrofitWithoutInterceptor.newBuilder()
            .client(createOkHttpClient())
            .build()

        apiService = retrofitWithInterceptor.create(AnimalsApi::class.java)
    }
}
