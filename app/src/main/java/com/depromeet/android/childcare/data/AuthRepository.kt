package com.depromeet.android.childcare.data

import android.content.Context
import androidx.preference.PreferenceManager
import com.depromeet.android.childcare.KEY_AUTH_TOKEN
import com.depromeet.android.childcare.model.request.LoginRequest
import com.depromeet.android.childcare.network.ServiceApi
import com.depromeet.android.childcare.network.retrofitCallback

class AuthRepository(
    private val context: Context,
    private val service: ServiceApi
): AuthDataSource {

    override fun getAccessToken(): String? =
        PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_AUTH_TOKEN, null)

    override fun updateAccessToken(token: String): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(KEY_AUTH_TOKEN, token)
            .commit()
    }

    override fun loginWithKakao(
        kakaoToken: String,
        success: () -> Unit,
        failed: (String?) -> Unit
    ) {

        service.login(LoginRequest(kakaoToken)).enqueue(retrofitCallback { response, throwable ->

            // Todo: 나중에 헤더보고 에러처리는 필수
            throwable?.let {
                failed(throwable.message)
            } ?: run {
                response?.let {

                    if(response.code() != 200) {
                        failed(it.message())
                        return@retrofitCallback
                    }

                    it.body()?.let { loginResponse ->
                        // 성공시에 preference 에 accesstoken 추가
                        updateAccessToken(loginResponse.data.accessToken).apply {
                            if (this) {
                                success()
                            } else {
                                failed("다시 로그인해 주세요")
                            }
                        }
                    }
                }
            }
        })
    }
}