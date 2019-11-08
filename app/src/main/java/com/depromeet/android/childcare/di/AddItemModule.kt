package com.depromeet.android.childcare.di

import com.depromeet.android.childcare.addbook.AddItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addItemModule = module {

    viewModel { AddItemViewModel(get(), get()) }
}