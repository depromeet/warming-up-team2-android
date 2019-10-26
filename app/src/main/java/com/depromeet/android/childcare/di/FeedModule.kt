package com.depromeet.android.childcare.di

import com.depromeet.android.childcare.feed.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val feedModule = module {

    viewModel { FeedViewModel(get(), get()) }
}