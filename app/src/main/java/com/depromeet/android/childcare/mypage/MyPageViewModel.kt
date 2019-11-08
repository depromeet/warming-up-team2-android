package com.depromeet.android.childcare.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.data.BookDataSource
import com.depromeet.android.childcare.util.ToastProvider

class MyPageViewModel(
    private val bookRepository: BookDataSource,
    private val toastProvider: ToastProvider
) : ViewModel() {

    private val _spouseName = MutableLiveData<String>()
    val spouseName: LiveData<String>
        get() = _spouseName

    private val _myCode = MutableLiveData<String>()
    val myCode: LiveData<String>
        get() = _myCode

    private val _monthAvgConsumptionList = MutableLiveData<List<Float>>(listOf(0f, 0f, 0f, 0f, 0f, 0f))
    val monthAvgConsumptionList: LiveData<List<Float>>
        get() = _monthAvgConsumptionList

    private val _monthList = MutableLiveData<List<String>>(listOf("01", "02", "03", "04", "05", "06"))
    val monthList: LiveData<List<String>>
        get() = _monthList

    private val _totalAvgConsumption = MutableLiveData<Float>(0f)
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

    private val _mostCategoryAmount = MutableLiveData<Float>()
    val mostCategoryAmount: LiveData<Float>
        get() = _mostCategoryAmount


    init {
        getExpenditureStatistics()
        _spouseName.value = "여해주"
        _myCode.value = "A123456"

//        _monthAvgConsumptionList.value = mutableListOf(
//            1000000f,
//            1200000f,
//            1300000f,
//            700000f,
//            900000f,
//            800000f
//        )
//        _monthList.value = mutableListOf("3","4","5","6","7","8")
//        _totalAvgConsumption.value = 983333.333f

        _categoryList.value = listOf("육아용품", "육아용품", "육아용품", "육아용품", "육아용품")
        _categoryConsumptionList.value = mutableListOf(
            800000f,
            900000f,
            1000000f,
            1200000f,
            1300000f
        )
        _mostCategoryName.value = "육아용품"
        _mostCategoryAmount.value = 983333.333f

    }

    private fun getExpenditureStatistics() {
        bookRepository.getExpenditureStatistics({ months, consumptions, avgConsumption ->
            _monthList.value = months
            _monthAvgConsumptionList.value = consumptions
            _totalAvgConsumption.value = avgConsumption

        }, { reason ->
            Log.e("GetExpenditureError", "error: $reason" )
            toastProvider.makeToast("소비 지출 통계 데이터를 가져오는 데 실패했습니다. 다시 실행해주세요")

        })
    }

    fun onConnectClick() {

    }
}