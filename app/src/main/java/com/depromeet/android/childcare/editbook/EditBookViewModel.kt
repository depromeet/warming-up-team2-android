package com.depromeet.android.childcare.editbook

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.Event
import com.depromeet.android.childcare.data.BookDataSource
import com.depromeet.android.childcare.model.PaymentType
import com.depromeet.android.childcare.model.RecordType
import com.depromeet.android.childcare.util.ToastProvider
import com.depromeet.android.childcare.util.convertToString
import java.util.*

class EditBookViewModel(
    private val bookRepository: BookDataSource,
    private val toastProvider: ToastProvider
) : ViewModel() {

    var recordId: Int = -1
    val recordType = MutableLiveData<RecordType>(RecordType.PAYMENT)
    val recordDate = MutableLiveData<String>(Date().convertToString("yyyy-mm-dd"))
    val title = MutableLiveData<String>("")
    val amount = MutableLiveData<String>("")
    val category = MutableLiveData<String>("")
    val paymentType = MutableLiveData<PaymentType>(PaymentType.CASH)
    val imgUrl = MutableLiveData<String>("")
    val content = MutableLiveData<String>("")

    private val _closeEditBookViewEvent = MutableLiveData<Event<Boolean>>()
    val closeEditBookViewEvent: LiveData<Event<Boolean>>
        get() = _closeEditBookViewEvent

    private val _openDatePickerEvent = MutableLiveData<Event<String>>()
    val openDatePickerEvent: LiveData<Event<String>>
        get() = _openDatePickerEvent

    init {
        bookRepository.bookModel?.let {
            Log.e("SAfsdf", "$it")
            recordId = it.id
            recordType.value = it.type
            recordDate.value = it.date
            title.value = it.title
            amount.value = it.amount.toString()
            category.value = it.category
            paymentType.value = it.paymentMethod
            imgUrl.value = it.imgUrl
            content.value = it.content
        } ?: kotlin.run {
            _closeEditBookViewEvent.value = Event(true)
        }
    }

    fun onBackButtonClick() {
        bookRepository.bookModel = null
        _closeEditBookViewEvent.value = Event(true)
    }

    fun onDateClick() {
        _openDatePickerEvent.value = Event(recordDate.value!!)
    }

    fun changeDate(year: Int, month: Int, day: Int) {
        recordDate.value = "$year-$month-$day"
    }

    fun onPaymentTypeClick(type: PaymentType) {
        paymentType.value = type
    }

    fun onCategoryClick(type: String) {
        if (type == category.value) {
            category.value = ""
        } else {
            category.value = type
        }
    }
}