package com.depromeet.android.childcare.editbook

import android.net.Uri
import android.util.Log
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
    var imgUri = MutableLiveData<Uri>()
    val content = MutableLiveData<String>("")

    private val _closeEditBookViewEvent = MutableLiveData<Event<Boolean>>()
    val closeEditBookViewEvent: LiveData<Event<Boolean>>
        get() = _closeEditBookViewEvent

    private val _openDatePickerEvent = MutableLiveData<Event<String>>()
    val openDatePickerEvent: LiveData<Event<String>>
        get() = _openDatePickerEvent

    private val _openGalleryEvent = MutableLiveData<Event<Boolean>>()
    val openGalleryEvent: LiveData<Event<Boolean>>
        get() = _openGalleryEvent

    // Event 에 position 을 반환
    private val _successEditBookEvent = MutableLiveData<Event<Boolean>>()
    val successEditBookEvent: LiveData<Event<Boolean>>
        get() = _successEditBookEvent

    init {
        bookRepository.editBookModel?.let {
            recordId = it.id
            recordType.value = it.type
            recordDate.value = it.date
            title.value = it.title
            amount.value = it.amount.toString()
            category.value = it.category
            paymentType.value = it.paymentMethod
            imgUri.value = if (it.imgUrl != null) Uri.parse(it.imgUrl) else null
            content.value = it.content
        } ?: kotlin.run {
            _closeEditBookViewEvent.value = Event(true)
        }
    }

    fun onBackButtonClick() {
        bookRepository.editBookModel = null
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

    fun onGalleryClick() {
        _openGalleryEvent.value = Event(true)
    }

    fun changeImgUrl(uri: Uri?) {
        uri?.let {
            imgUri.value = it
        }
    }

    fun onEditClick() {
        val record = Record(
            recordId,
            User(-1, "", "", ""),
            recordType.value ?: RecordType.PAYMENT,
            recordDate.value ?: Date().convertToString("yyyy-MM-dd"),
            title.value ?: "제목을 입력해 주세요",
            amount.value?.toInt() ?: 0,
            category.value ?: "미등록",
            paymentType.value ?: PaymentType.CARD,
            imgUri.value?.toString(),
            content.value
        )

        bookRepository.editRecord(record, {

            // img 업로드
            imgUri.value?.let {
                bookRepository.uploadImage(recordId, it, {
                    _successEditBookEvent.value = Event(true)
                }, { msg, reason ->
                    // 이미지는 업로드 안되어도 수정이 되도록 설정했다.
                    Log.e("EditBookError", "upload Image Error: $reason" )
                    toastProvider.makeToast(msg)
                    _successEditBookEvent.value = Event(true)
                })
            } ?: kotlin.run {
                _successEditBookEvent.value = Event(true)
            }
        },{msg, reason ->
            Log.e("EditBookError", "error: $reason" )
            toastProvider.makeToast(msg)
        })
    }
}