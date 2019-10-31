package com.depromeet.android.childcare.di

import com.depromeet.android.childcare.login.KakaoSessionCallback
import com.depromeet.android.childcare.login.LoginActivity
import com.depromeet.android.childcare.login.LoginViewModel
import com.kakao.auth.ISessionCallback
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val loginModule = module {
    viewModel { LoginViewModel() }

    scope(named<LoginActivity>()) {

        scoped<ISessionCallback> { KakaoSessionCallback() }
    }
}