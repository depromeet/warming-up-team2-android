package com.depromeet.android.childcare.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.data.FeedDataSource
import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.util.ToastProvider

class HomeViewModel(
    feedRepository: FeedDataSource,
    val feedNavigator: FeedNavigator,
    private val toastProvider: ToastProvider
) : ViewModel() {

    private val _feeds = MutableLiveData<List<Record>>()

    val feeds : LiveData<List<Record>>
        get() = _feeds // _feeds를 feed를 통해 발행합니다

    init {
        feedRepository.getAllFeeds({
            _feeds.value = it
        }, {
            toastProvider.makeToast("값 가져오는 중에 에러남")
        })
    }
}