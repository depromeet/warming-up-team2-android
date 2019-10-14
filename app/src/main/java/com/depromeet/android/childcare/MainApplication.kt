package com.depromeet.android.childcare

import android.app.Application
import com.depromeet.android.childcare.di.apiModule
import com.depromeet.android.childcare.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(apiModule, homeModule))
        }
    }
}