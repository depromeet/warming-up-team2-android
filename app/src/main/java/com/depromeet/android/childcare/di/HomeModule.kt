package com.depromeet.android.childcare.di

import com.depromeet.android.childcare.bookdetail.BookDetailNavigator
import com.depromeet.android.childcare.bookdetail.BookDetailViewModel
import com.depromeet.android.childcare.data.FeedDataSource
import com.depromeet.android.childcare.data.FeedRepository
import com.depromeet.android.childcare.home.FeedNavigator
import com.depromeet.android.childcare.home.HomeViewModel
import com.depromeet.android.childcare.util.ToastProvider
import com.depromeet.android.childcare.util.ToastProviderImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel { (navigator: FeedNavigator) -> HomeViewModel(get(), navigator, get()) }
    viewModel { (navigator: BookDetailNavigator) -> BookDetailViewModel(get(), navigator, get()) }

    single<ToastProvider> { ToastProviderImpl(get()) }
    single<FeedDataSource> { FeedRepository() }
}