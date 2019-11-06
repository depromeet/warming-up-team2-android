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

    private val _monthAvgConsumptionList = MutableLiveData<List<Float>>()
    val monthAvgConsumptionList: LiveData<List<Float>>
        get() = _monthAvgConsumptionList

    private val _monthList = MutableLiveData<List<String>>()
    val monthList: LiveData<List<String>>
        get() = _monthList

    private val _totalAvgConsumption = MutableLiveData<Float>()
    val totalAvgConsumption: LiveData<Float>
        get() = _totalAvgConsumption

    private val _categoryList = MutableLiveData<List<String>>()
    val categoryList: LiveData<List<String>>
        get() = _categoryList

    private val _categoryConsumptionList = MutableLiveData<List<Float>>()
    val categoryConsumptionList: LiveData<List<Float>>
        get() = _categoryConsumptionList

    private val _mostCategoryName = MutableLiveData<String>()
    val mostCategoryName: LiveData<String>
        get() = _mostCategoryName


    init {
        _monthAvgConsumptionList.value = mutableListOf(
            1000000f,
            1200000f,
            1300000f,
            700000f,
            900000f,
            800000f
        )
        _monthList.value = mutableListOf("3","4","5","6","7","8")
        _totalAvgConsumption.value = 983333.333f

        _categoryList.value = listOf("육아용품", "육아용품", "육아용품", "육아용품", "육아용품")
        _categoryConsumptionList.value = mutableListOf(
            800000f,
            900000f,
            1000000f,
            1200000f,
            1300000f
        )
        _mostCategoryName.value = "육아용품"

    }
}