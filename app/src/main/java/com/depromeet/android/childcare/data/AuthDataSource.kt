package com.depromeet.android.childcare.data

interface AuthDataSource {
    fun getAccessToken(): String?
    fun updateAccessToken(token: String)
    fun loginWithKakao(
        kakaoToken: String,
        success: (String) -> Unit,
        failed: (String, String?) -> Unit
    )
}