package com.depromeet.android.childcare.di

import com.depromeet.android.childcare.bookdetail.BookDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookDetailModule = module {

    viewModel { BookDetailViewModel(get(), get()) }
}