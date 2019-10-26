package com.depromeet.android.childcare.di

import com.depromeet.android.childcare.book.BookViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookModule = module {

    viewModel { BookViewModel(get(), get()) }
}