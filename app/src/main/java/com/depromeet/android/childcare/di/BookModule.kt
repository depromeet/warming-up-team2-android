package com.depromeet.android.childcare.di

import com.depromeet.android.childcare.book.BookViewModel
import com.depromeet.android.childcare.data.BookDataSource
import com.depromeet.android.childcare.data.BookRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookModule = module {

    single<BookDataSource> { BookRepository(get()) }

    viewModel { BookViewModel(get(), get()) }
}