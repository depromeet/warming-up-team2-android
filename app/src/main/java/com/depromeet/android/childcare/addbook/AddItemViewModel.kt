package com.depromeet.android.childcare.addbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.data.BookDataSource
import com.depromeet.android.childcare.util.ToastProvider
import com.depromeet.android.childcare.util.getDateString

class AddItemViewModel(
    val repository: BookDataSource,
    val toastProvider: ToastProvider
) : ViewModel() {
    private val _title = MutableLiveData<String>()
    private val _amount = MutableLiveData<Int>()
    private val _method = MutableLiveData<String>("카드")
    private val _date = MutableLiveData<String>(getDateString())

    val title: LiveData<String>
        get() = _title

    val amount: LiveData<Int>
        get() = _amount

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
}