package com.gl4.project.data.utilities

data class Token(
    var access_token: String? = null,
    var expires_in: Long? = null,
    val token_type: String = "Bearer"
){
    fun isAccessTokenExpired(): Boolean {
        val currentTimeMillis = System.currentTimeMillis()
        return expires_in != null && currentTimeMillis >= expires_in!!
    }

    fun updateAccessToken(token: String, expiresIn: Long) {
        access_token = token
        expires_in = System.currentTimeMillis() + expiresIn * 1000 // Convert expiresIn to milliseconds
    }
}