package com.gl4.project.data.interceptors

import android.util.Log
import com.gl4.project.data.RetrofitHelper
import com.gl4.project.data.api.AnimalsApi
import com.gl4.project.data.utilities.AuthBody
import com.gl4.project.data.utilities.Token
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.awaitResponse

class AuthInterceptor : Interceptor {

    private val apiService = RetrofitHelper.apiServiceNoI
    private val sessionManager = Token()
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        Log.d("Api", originalRequest.url.toString())

        val accessToken = sessionManager.access_token

        if (accessToken == null || sessionManager.isAccessTokenExpired()) {

            val refreshedToken = runBlocking {
                try {
                    val response = apiService.authenticate()


                    if (response.isSuccessful) {
                        sessionManager.updateAccessToken(response.body()!!.access_token!!, response.body()!!.expires_in!!)
                        response.body()!!.access_token!!
                    } else {
                        Log.e("Api", "Error: ${response.code()} - ${response.errorBody()?.string()}")
                    }
                } catch (e: Exception) {
                    Log.e("Api", "Error: ${e.message}")
                }
            }

            if (refreshedToken != null) {
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $refreshedToken")
                    .build()

                return chain.proceed(newRequest)
            }
        }

        val authorizedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()

        return chain.proceed(authorizedRequest)
    }
}
