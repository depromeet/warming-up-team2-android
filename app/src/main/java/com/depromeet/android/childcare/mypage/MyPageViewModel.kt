package com.depromeet.android.childcare.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.data.BookDataSource

class MyPageViewModel(
    private val bookRepository: BookDataSource
) : ViewModel() {

    private val _spouseName = MutableLiveData<String>("여해주")
    val spouseName: LiveData<String>
        get() = _spouseName

    private val _avgConsumption = MutableLiveData<Int>(1000000)
    val avgConsumption: LiveData<Int>
        get() = _avgConsumption

    private val _monthAvgConsumptionList = MutableLiveData<List<Float>>()
    val monthAvgConsumptionList: LiveData<List<Float>>
        get() = _monthAvgConsumptionList

    private val _monthList= MutableLiveData<List<String>>()
    val monthList: LiveData<List<String>>
        get() = _monthList


    init {
        _monthAvgConsumptionList.value = mutableListOf(
            1000000f,
            1200000f,
            1300000f,
            700000f,
            900000f,
            800000f
        )

        _monthList.value = mutableListOf("1","2","3","4","5","6")
    }
}