package com.depromeet.android.childcare.di

import com.depromeet.android.childcare.book.BookViewModel
import com.depromeet.android.childcare.data.BookDataSource
import com.depromeet.android.childcare.data.BookRepository
import com.depromeet.android.childcare.util.ResourcesProvider
import com.depromeet.android.childcare.util.ResourcesProviderImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookModule = module {

    single<BookDataSource> { BookRepository(get()) }

    single<ResourcesProvider> { ResourcesProviderImpl(get()) }

    viewModel { BookViewModel(get(), get()) }
}