package com.depromeet.android.childcare.home

import android.util.Log
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

    val feeds = MutableLiveData<List<Record>>()

    init {
        feedRepository.getAllFeeds({
            feeds.value = it
        }, {
            toastProvider.makeToast("값 가져오는 중에 에러남")
        })
    }
}