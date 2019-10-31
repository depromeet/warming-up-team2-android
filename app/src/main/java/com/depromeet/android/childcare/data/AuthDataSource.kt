package com.depromeet.android.childcare.data

interface AuthDataSource {
    fun getAccessToken(): String?
    fun updateAccessToken(token: String)
    fun loginWithKakao()
}