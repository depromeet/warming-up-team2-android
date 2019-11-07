package com.depromeet.android.childcare.di

import com.depromeet.android.childcare.data.AuthDataSource
import com.depromeet.android.childcare.data.AuthRepository
import com.depromeet.android.childcare.data.BookDataSource
import com.depromeet.android.childcare.data.BookRepository
import com.depromeet.android.childcare.util.ResourcesProvider
import com.depromeet.android.childcare.util.ResourcesProviderImpl
import com.depromeet.android.childcare.util.ToastProvider
import com.depromeet.android.childcare.util.ToastProviderImpl
import org.koin.dsl.module

val appModule = module {

    single<ToastProvider> { ToastProviderImpl(get()) }

    single<ResourcesProvider> { ResourcesProviderImpl(get()) }

    single<BookDataSource> { BookRepository(get())}

    single<AuthDataSource> { AuthRepository(get(), get()) }
}