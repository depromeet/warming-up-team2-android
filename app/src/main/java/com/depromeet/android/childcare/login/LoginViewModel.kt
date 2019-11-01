package com.depromeet.android.childcare.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.Event
import com.depromeet.android.childcare.data.AuthDataSource
import com.depromeet.android.childcare.util.ToastProvider
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException

class LoginViewModel(
    private val authRepository: AuthDataSource,
    private val toastProvider: ToastProvider
): ViewModel(), ISessionCallback {

    enum class VisibleType {
        LOGIN, CONNECT
    }

    private val _visibleType = MutableLiveData<VisibleType>(VisibleType.LOGIN)
    val visibleType: LiveData<VisibleType>
        get() = _visibleType

    private val _myCode = MutableLiveData<String>()
    val myCode: LiveData<String>
        get() = _myCode

    private val _openMainEvent = MutableLiveData<Event<Boolean>>()
    val openMainEvent: LiveData<Event<Boolean>>
        get() = _openMainEvent

    fun onConnectClick() {
        // Todo: connect 요청!! 후 Main 으로 이동
        _openMainEvent.value = Event(true)
    }

    fun onConnectLaterClick() {
        _openMainEvent.value = Event(true)
    }

    override fun onSessionOpenFailed(exception: KakaoException?) {
        // Todo: 에러처리 필요
    }

    override fun onSessionOpened() {
        val token = Session.getCurrentSession().tokenInfo.accessToken

        authRepository.loginWithKakao(token, {code ->
            _myCode.value = code
            _visibleType.value = VisibleType.CONNECT
            Log.e("success!!", "value: ${_visibleType.value}")
        }, { msg, reason ->
            Log.e(msg, "error: $reason" )
            toastProvider.makeToast(reason.toString())
        })
    }
}