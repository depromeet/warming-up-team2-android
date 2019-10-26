package com.depromeet.android.childcare.di

import com.depromeet.android.childcare.data.FeedDataSource
import com.depromeet.android.childcare.data.FeedRepository
import com.depromeet.android.childcare.util.ResourcesProvider
import com.depromeet.android.childcare.util.ResourcesProviderImpl
import com.depromeet.android.childcare.util.ToastProvider
import com.depromeet.android.childcare.util.ToastProviderImpl
import org.koin.dsl.module

val appModule = module {

    single<ToastProvider> { ToastProviderImpl(get()) }

    single<FeedDataSource> { FeedRepository() }

    single<ResourcesProvider> { ResourcesProviderImpl(get()) }
}