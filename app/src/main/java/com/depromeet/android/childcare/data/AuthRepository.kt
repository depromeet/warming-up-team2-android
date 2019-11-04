package com.depromeet.android.childcare.data

import android.content.Context
import androidx.preference.PreferenceManager
import com.depromeet.android.childcare.network.ServiceApi

class AuthRepository(
    private val context: Context,
    private val service: ServiceApi
): AuthDataSource {

    companion object {
        private const val KEY_AUTH_TOKEN = "auth_token"
    }

    override fun getAccessToken(): String? =
        PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_AUTH_TOKEN, null)

    override fun updateAccessToken(token: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(KEY_AUTH_TOKEN, token)
            .apply()
    }

    override fun loginWithKakao(
        kakaoToken: String,
        success: (String) -> Unit,
        failed: (String, String?) -> Unit
    ) {
        // Todo 서버에 로그인 요청 (이 때 코드가 나올 것임)

        // FixMe: tokken 을 update 해야 함
//        updateAccessToken("sampleToken")

        // 성공 작업 실행
        success("A123456")
    }
}