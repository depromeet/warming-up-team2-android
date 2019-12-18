package com.depromeet.android.childcare.addbook

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.Event
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.data.BookDataSource
import com.depromeet.android.childcare.model.PaymentType
import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.model.RecordType
import com.depromeet.android.childcare.model.User
import com.depromeet.android.childcare.util.ToastProvider
import com.depromeet.android.childcare.util.convertToString
import com.depromeet.android.childcare.util.getDateString
import java.util.*


class AddItemViewModel(
    val repository: BookDataSource,
    private val toastProvider: ToastProvider
) : ViewModel() {

    val title = MutableLiveData<String>("")
    val amount = MutableLiveData<String>("")
    var imgUri = MutableLiveData<Uri>()
    var content = MutableLiveData<String>("")

    private val _category = MutableLiveData<String>("미등록")
    val category: LiveData<String>
        get() = _category

    private val _method =MutableLiveData<PaymentType>(PaymentType.CARD)
    val method: LiveData<PaymentType>
        get() = _method

    private val _date = MutableLiveData<String>(getDateString())
    val date: LiveData<String>
        get() = _date

    private val _openAddBookSecondEvent = MutableLiveData<Event<Unit>>()
    val openAddBookSecondEvent: LiveData<Event<Unit>>
        get() = _openAddBookSecondEvent

    private val _goBackAddBookEvent = MutableLiveData<Event<Unit>>()
    val goBackAddBookEvent: LiveData<Event<Unit>>
        get() = _goBackAddBookEvent

    private val _openDatePickerEvent = MutableLiveData<Event<String>>()
    val openDatePickerEvent: LiveData<Event<String>>
        get() = _openDatePickerEvent

    private val _openGalleryEvent = MutableLiveData<Event<Unit>>()
    val openGalleryEvent: LiveData<Event<Unit>>
        get() = _openGalleryEvent

    private val _successAddBookEvent = MutableLiveData<Event<Unit>>()
    val successAddBookEvent: LiveData<Event<Unit>>
        get() = _successAddBookEvent

    fun onDateClick() {
        _openDatePickerEvent.value = Event(date.value!!)
    }

    fun changeDate(year: Int, month: Int, day: Int) {
        _date.value = getDateString(year, month, day)
    }

    fun onPaymentMethodClick(type: PaymentType) {
        _method.value = type
    }

    fun onNextClick() {
        _openAddBookSecondEvent.value = Event(Unit)
    }

    fun onCategoryClick(type: String) {
        if (type == category.value) {
            _category.value = ""
        } else {
            _category.value = type
        }
    }

    fun onGalleryClick() {
        _openGalleryEvent.value = Event(Unit)
    }


    fun changeImgUrl(uri: Uri?) {
        uri?.let {
            imgUri.value = it
        }
    }

    fun onBackClick() {
        _goBackAddBookEvent.value = Event(Unit)
    }

    fun onCreateItemClick() {
        val record = Record(
            -1,
            User(-1, "", "", ""),
            RecordType.PAYMENT,
            date.value ?: Date().convertToString("yyyy-MM-dd"),
            title.value ?: "제목을 입력해 주세요",
            amount.value?.toInt() ?: 0,
            category.value ?: "미등록",
            method.value ?: PaymentType.CARD,
            imgUri.value?.toString(),
            content.value
        )

        Log.e("click!!", "record: $record")


//        repository.createNewRecord(
//            CreateRecordRequest(
//                amount,
//                _category.value!!,
//                if (disc.isNullOrEmpty()) "" else disc,
//                _date.value!!,
//                _method.value!!,
//                if (title.isNullOrEmpty()) "" else title
//            ), {
//                uri?.let { uri ->
//                    repository.uploadImage(
//                        it.data.id,
//                        uri, {
//                        }, { msg, reason ->
//                            onDataNotAvailable(msg, reason)
//                        }
//                    )
//                }
//            }, { msg, reason ->
//                onDataNotAvailable(msg, reason)
//            }
//        )
    }

    private fun onDataNotAvailable(msg: String, reason: String?) {
        Log.e(msg, reason ?: "No error msg")
        toastProvider.makeToast(R.string.msg_network_err)
    }
}