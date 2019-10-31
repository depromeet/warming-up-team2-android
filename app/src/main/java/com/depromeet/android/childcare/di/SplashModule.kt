package com.depromeet.android.childcare.di

import com.depromeet.android.childcare.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {

    viewModel { SplashViewModel() }
}