package com.depromeet.android.childcare.addbook

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.data.BookDataSource
import com.depromeet.android.childcare.model.request.CreateRecordRequest
import com.depromeet.android.childcare.util.ToastProvider
import com.depromeet.android.childcare.util.getDateString


class AddItemViewModel(
    val repository: BookDataSource,
    private val toastProvider: ToastProvider
) : ViewModel() {
    private val _category = MutableLiveData<String>("미등록")
    private val _method = MutableLiveData<String>("카드")
    private val _date = MutableLiveData<String>(getDateString())

    val category: LiveData<String>
        get() = _category

    val method: LiveData<String>
        get() = _method

    val date: LiveData<String>
        get() = _date

    fun changeDate(year: Int, month: Int, day: Int) {
        _date.value = getDateString(year, month, day)
    }

    fun setPaymentMethod(type: String) {
        _method.value = type
    }

    fun setCategory(type: String) {
        _method.value = type
    }

    fun createItem(amount: Int, title: String?, disc: String?, uri: Uri?) {
        repository.createNewRecord(
            CreateRecordRequest(
                amount,
                _category.value!!,
                if (disc.isNullOrEmpty()) "" else disc,
                _date.value!!,
                _method.value!!,
                if (title.isNullOrEmpty()) "" else title
            ), {
                uri?.let { uri ->
                    repository.uploadImage(
                        it.data.id,
                        uri, {
                        }, { msg, reason ->
                            onDataNotAvailable(msg, reason)
                        }
                    )
                }
            }, { msg, reason ->
                onDataNotAvailable(msg, reason)
            }
        )
    }

    private fun onDataNotAvailable(msg: String, reason: String?) {
        Log.e(msg, reason ?: "No error msg")
        toastProvider.makeToast(R.string.msg_network_err)
    }
}