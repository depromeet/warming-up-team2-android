package com.depromeet.android.childcare.di

import com.depromeet.android.childcare.mypage.MyPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myPageModule = module {
    viewModel { MyPageViewModel(get(), get()) }
}