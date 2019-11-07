package com.depromeet.android.childcare.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.data.BookDataSource
import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.util.ToastProvider

class FeedViewModel(
    bookRepository: BookDataSource,
    private val toastProvider: ToastProvider
) : ViewModel() {

    private val _feeds = MutableLiveData<List<Record>>(listOf())
    val feeds: LiveData<List<Record>>
        get() = _feeds

    private val _feedType = MutableLiveData<FeedType>(FeedType.DETAIL)
    val feedType: LiveData<FeedType>
        get() = _feedType

    init {
        bookRepository.getAllRecords({
            _feeds.value = it
        }, { msg, reason ->
            toastProvider.makeToast(reason.toString())
        })
    }

    fun changeFeedType() {
        if (_feedType.value == FeedType.DETAIL) {
            _feedType.value = FeedType.SUMMARY
        } else {
            _feedType.value = FeedType.DETAIL
        }
    }
}