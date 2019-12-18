package com.depromeet.android.childcare.di

import com.depromeet.android.childcare.addbook.AddBookActivity
import com.depromeet.android.childcare.addbook.AddBookFirstFragment
import com.depromeet.android.childcare.addbook.AddBookSecondFragment
import com.depromeet.android.childcare.addbook.AddItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val addItemModule = module {

    viewModel { AddItemViewModel(get(), get()) }

    scope(named<AddBookActivity>()) {
        factory { AddBookFirstFragment() }
        factory{ AddBookSecondFragment() }
    }
}