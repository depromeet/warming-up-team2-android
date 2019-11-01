package com.depromeet.android.childcare.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.Event
import com.depromeet.android.childcare.data.AuthDataSource

class SplashViewModel(
    authRepository: AuthDataSource
): ViewModel() {

    private val _openAppEvent = MutableLiveData<Event<String>>()
    val openAppEvent: LiveData<Event<String>>
        get() = _openAppEvent

    // 최소 한번 애니메이션이 끝나야 다음으로 실행
    private var isAnimationEnded = false
    private var openType = SplashActivity.NOT_OPEN

    init {
        authRepository.getAccessToken()?.let {
            openType = SplashActivity.OPEN_MAIN
        } ?: run {
            openType = SplashActivity.OPEN_LOGIN
        }

        onSplashFinished()
    }

    fun onAnimationEnded() {
        isAnimationEnded = true
        onSplashFinished()
    }

    // Todo: synchronized 문제 해결 필요
    private fun onSplashFinished() {
        if (openType != SplashActivity.NOT_OPEN
            && isAnimationEnded
            && _openAppEvent.value == null) {
            _openAppEvent.value = Event(openType)
        }
    }
}