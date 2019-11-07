package com.depromeet.android.childcare.di

import com.depromeet.android.childcare.login.LoginActivity
import com.depromeet.android.childcare.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val loginModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }

    scope(named<LoginActivity>()) {

    }
}