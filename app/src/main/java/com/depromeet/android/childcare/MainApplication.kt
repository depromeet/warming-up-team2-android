package com.depromeet.android.childcare

import android.app.Application
import com.depromeet.android.childcare.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(
                appModule,
                apiModule,
                splashModule,
                feedModule,
                bookModule,
                bookDetailModule)
            )
        }
    }
}