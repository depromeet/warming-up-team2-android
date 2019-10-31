package com.depromeet.android.childcare.login

import android.util.Log
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException

class KakaoSessionCallback: ISessionCallback {
    override fun onSessionOpenFailed(exception: KakaoException?) {

    }

    override fun onSessionOpened() {
        Log.e("login!!", "abcdefg")
        Session.getCurrentSession().tokenInfo.accessToken
    }
}