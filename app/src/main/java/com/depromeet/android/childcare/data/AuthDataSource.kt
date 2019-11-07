package com.depromeet.android.childcare.data

interface AuthDataSource {
    fun getAccessToken(): String?
    fun updateAccessToken(token: String): Boolean
    fun loginWithKakao(
        kakaoToken: String,
        success: () -> Unit, // 연동코드, spouse 와 연동되어있는지 아닌지
        failed: (String?) -> Unit
    )
}