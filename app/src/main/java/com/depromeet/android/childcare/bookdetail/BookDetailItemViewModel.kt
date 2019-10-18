package com.depromeet.android.childcare.bookdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.depromeet.android.childcare.model.Record

class BookDetailItemViewModel(
    bookDetail: Record,
    private val navigator: BookDetailNavigator
) {
    private val _bookDetail = MutableLiveData<Record>()

    val bookDetail : LiveData<Record>
        get() = _bookDetail

    init {
        _bookDetail.value = bookDetail
    }

    fun onOptionClick() = _bookDetail.value?.let { navigator.showOptionDialog(it.id) }
}