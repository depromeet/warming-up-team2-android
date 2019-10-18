package com.depromeet.android.childcare.bookdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.data.FeedDataSource
import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.util.ToastProvider

class BookDetailViewModel(
    feedRepository: FeedDataSource,
    private val toastProvider: ToastProvider
) : ViewModel() {

    private val _bookDetails = MutableLiveData<List<Record>>()

    val bookDetails : LiveData<List<Record>>
        get() = _bookDetails

    init {
        feedRepository.getAllFeeds({
            _bookDetails.value = it
        }, {
            toastProvider.makeToast("값 가져오는 중에 에러남")
        })
    }
}