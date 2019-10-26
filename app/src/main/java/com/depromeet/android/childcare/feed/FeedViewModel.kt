package com.depromeet.android.childcare.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.data.FeedDataSource
import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.util.ToastProvider

class FeedViewModel(
    feedRepository: FeedDataSource,
    private val toastProvider: ToastProvider
) : ViewModel() {

    private val _feeds = MutableLiveData<List<Record>>()
    private val _feedType = MutableLiveData<FeedType>(FeedType.DETAIL)

    val feeds : LiveData<List<Record>>
        get() = _feeds

    val feedType : LiveData<FeedType>
        get() = _feedType

    init {
        feedRepository.getAllFeeds({
            _feeds.value = it
        }, {
            toastProvider.makeToast("값 가져오는 중에 에러남")
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