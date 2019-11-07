package com.depromeet.android.childcare.network

import android.content.Context
import androidx.preference.PreferenceManager
import com.depromeet.android.childcare.KEY_AUTH_TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {

        val token = PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_AUTH_TOKEN, null)

        token?.let {
            val newRequest = request().newBuilder().run {
                addHeader("Authorization", "Bearer $token")
                build()
            }
            proceed(newRequest)
        } ?: run {
            proceed(request())
        }
    }
}