package com.depromeet.android.childcare.editbook

import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.data.BookDataSource
import com.depromeet.android.childcare.util.ToastProvider

class EditBookViewModel(
    private val bookRepository: BookDataSource,
    private val toastProvider: ToastProvider
): ViewModel() {
}