package com.depromeet.android.childcare.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.Event
import com.depromeet.android.childcare.data.AuthDataSource
import com.depromeet.android.childcare.data.BookDataSource
import com.depromeet.android.childcare.util.ToastProvider
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException

class LoginViewModel(
    private val authRepository: AuthDataSource,
    private val bookRepository: BookDataSource,
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

    val spouseCode = MutableLiveData<String>()

    private val _openMainEvent = MutableLiveData<Event<Boolean>>()
    val openMainEvent: LiveData<Event<Boolean>>
        get() = _openMainEvent

    private val _isProgressed = MutableLiveData<Boolean>(false)
    val isProgressed: LiveData<Boolean>
        get() = _isProgressed

    fun onConnectClick() {
        _isProgressed.value = true

        spouseCode.value?.let {
            bookRepository.connectSpouse(it, {
                _openMainEvent.value = Event(true)
                _isProgressed.value = false

            }, {reason ->
                Log.e("LoginError", "error: $reason" )
                toastProvider.makeToast("연동에 실패했습니다. 다시 시도해주세요")
                _isProgressed.value = false
            })
        } ?: run {
            toastProvider.makeToast("배우자 코드를 입력해주세요")
            _isProgressed.value = false
        }
    }

    fun onConnectLaterClick() {
        _isProgressed.value = true
        _openMainEvent.value = Event(true)
        _isProgressed.value = false
    }

    override fun onSessionOpenFailed(exception: KakaoException?) {
        toastProvider.makeToast("로그인에 실패했습니다. 다시 시도해주세요")
    }

    override fun onSessionOpened() {
        val token = Session.getCurrentSession().tokenInfo.accessToken
        _isProgressed.value = true

        authRepository.loginWithKakao(token, {
            bookRepository.getMyInfo({myInfo, spouseInfo ->

                if (spouseInfo != null) {
                    // 연결되어 있다면 바로 Main 으로 이동시킴
                    _openMainEvent.value = Event(true)
                } else {
                    // 연결되어 있지 않다면 물어보는 곳으로 이동
                    _myCode.value = myInfo.connectCode
                    _visibleType.value = VisibleType.CONNECT
                }
                _isProgressed.value = false

            }, { reason ->
                Log.e("LoginError", "error: $reason")
                toastProvider.makeToast("로그인에 실패했습니다. 다시 시도해주세요")
                _isProgressed.value = false
            })

        }, { reason ->
            Log.e("LoginError", "error: $reason" )
            toastProvider.makeToast("로그인에 실패했습니다. 다시 시도해주세요")
            _isProgressed.value = false
        })
    }
}