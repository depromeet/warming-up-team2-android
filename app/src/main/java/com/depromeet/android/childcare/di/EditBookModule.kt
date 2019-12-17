package com.depromeet.android.childcare.di

import com.depromeet.android.childcare.editbook.EditBookViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val editBookModule = module {

    viewModel { EditBookViewModel(get(), get()) }
}