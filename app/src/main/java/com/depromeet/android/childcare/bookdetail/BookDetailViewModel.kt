package com.depromeet.android.childcare.bookdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.data.BookDataSource
import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.util.ToastProvider

class BookDetailViewModel(
    bookRepository: BookDataSource,
    private val toastProvider: ToastProvider
) : ViewModel() {

    private val _bookDetails = MutableLiveData<List<Record>>()

    val bookDetails: LiveData<List<Record>>
        get() = _bookDetails

    init {
        bookRepository.getAllRecords({
            _bookDetails.value = it
        }, { msg, reason ->
            Log.e(msg, reason)
            toastProvider.makeToast(reason.toString())
        })
    }
}