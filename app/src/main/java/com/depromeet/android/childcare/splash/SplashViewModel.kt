package com.depromeet.android.childcare.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.Event

class SplashViewModel: ViewModel() {

    private val _openAppEvent = MutableLiveData<Event<String>>()
    val openAppEvent: LiveData<Event<String>>
        get() = _openAppEvent

    // 최소 한번 애니메이션이 끝나야 다음으로 실행
    private var isAnimationEnded = false
    private var openType = SplashActivity.NOT_OPEN

    init {
        // Todo 로그인 되어 있는지 체크
        openType = SplashActivity.OPEN_MAIN
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