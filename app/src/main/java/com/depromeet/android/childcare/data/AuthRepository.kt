package com.depromeet.android.childcare.data

import android.content.Context
import androidx.preference.PreferenceManager

class AuthRepository(val context: Context): AuthDataSource {

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

    override fun loginWithKakao() {

    }
}