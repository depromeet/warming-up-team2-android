package com.depromeet.android.childcare.editbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.Event
import com.depromeet.android.childcare.data.BookDataSource
import com.depromeet.android.childcare.model.PaymentType
import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.model.RecordType
import com.depromeet.android.childcare.model.User
import com.depromeet.android.childcare.util.ToastProvider

class EditBookViewModel(
    private val bookRepository: BookDataSource,
    private val toastProvider: ToastProvider
) : ViewModel() {

    private val _record = MutableLiveData<Record>(
        Record(
            -1,
            User(-1, "", "", ""),
            RecordType.PAYMENT,
            "", "", -1, "", PaymentType.CASH, "", "")
    )
    val record: LiveData<Record>
        get() = _record

    private val _closeEditBookViewEvent = MutableLiveData<Event<Boolean>>()
    val closeEditBookViewEvent: LiveData<Event<Boolean>>
        get() = _closeEditBookViewEvent

    init {
        bookRepository.bookModel?.let {
            _record.value = it
        } ?: kotlin.run {
            _closeEditBookViewEvent.value = Event(true)
        }
    }

    fun onBackButtonClick() {
        bookRepository.bookModel = null
        _closeEditBookViewEvent.value = Event(true)
    }
}