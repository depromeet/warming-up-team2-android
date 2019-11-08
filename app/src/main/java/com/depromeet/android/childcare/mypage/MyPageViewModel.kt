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

    private val _isProgressed = MutableLiveData<Boolean>(false)
    val isProgressed: LiveData<Boolean>
        get() = _isProgressed

    private val _spouseName = MutableLiveData<String>()
    val spouseName: LiveData<String>
        get() = _spouseName

    private val _myCode = MutableLiveData<String>("")
    val myCode: LiveData<String>
        get() = _myCode

    val spouseCode = MutableLiveData<String>()

    private val _monthAvgConsumptionList = MutableLiveData<List<Float>>(listOf(0f, 0f, 0f, 0f, 0f, 0f))
    val monthAvgConsumptionList: LiveData<List<Float>>
        get() = _monthAvgConsumptionList

    private val _monthList = MutableLiveData<List<String>>(listOf("01", "02", "03", "04", "05", "06"))
    val monthList: LiveData<List<String>>
        get() = _monthList

    private val _totalAvgConsumption = MutableLiveData<Float>(0f)
    val totalAvgConsumption: LiveData<Float>
        get() = _totalAvgConsumption

    private val _categoryList = MutableLiveData<List<String>>(listOf("생활용품", "육아용품", "문화", "건강", "미등록"))
    val categoryList: LiveData<List<String>>
        get() = _categoryList

    private val _categoryConsumptionList = MutableLiveData<List<Float>>(listOf(0f, 0f, 0f, 0f, 0f, 0f))
    val categoryConsumptionList: LiveData<List<Float>>
        get() = _categoryConsumptionList

    private val _mostCategoryName = MutableLiveData<String>("")
    val mostCategoryName: LiveData<String>
        get() = _mostCategoryName

    private val _mostCategoryAmount = MutableLiveData<Float>(0f)
    val mostCategoryAmount: LiveData<Float>
        get() = _mostCategoryAmount


    init {
        getMyInfo()
        getExpenditureStatistics()
        getCategoriesStatistics()
    }

    private fun getExpenditureStatistics() {
        bookRepository.getExpenditureStatistics({ months, consumptions, avgConsumption ->
            _monthList.value = months
            _monthAvgConsumptionList.value = consumptions
            _totalAvgConsumption.value = avgConsumption

        }, { reason ->
            Log.e("GetExpenditureError", "error: $reason" )
            toastProvider.makeToast("월별 소비 지출 통계 데이터를 가져오는 데 실패했습니다. 다시 실행해주세요")

        })
    }

    private fun getCategoriesStatistics() {
        bookRepository.getCategoriesStatistics({ categories, consumptions, mostCategory, mostConsumption ->
            _categoryList.value = categories
            _categoryConsumptionList.value = consumptions
            _mostCategoryName.value = mostCategory
            _mostCategoryAmount.value = mostConsumption

        }, { reason ->
            Log.e("GetCategoriesError", "error: $reason" )
            toastProvider.makeToast("카테고리별 소비 지출 통계 데이터를 가져오는 데 실패했습니다. 다시 실행해주세요")
        })
    }

    private fun getMyInfo() {
        bookRepository.getMyInfo({myInfo, spouseInfo ->
            _myCode.value = myInfo.connectCode
            spouseInfo?.let {
                _spouseName.value = spouseInfo.name
            }
        }, { reason ->
            Log.e("GetMyInfoError", "error: $reason" )
            toastProvider.makeToast("내정보를 가져오는 데 실패했습니다. 다시 실행해주세요")
        })
    }

    fun onConnectClick() {
        _isProgressed.value = true
        spouseCode.value?.let {
            bookRepository.connectSpouse(it, {spouseInfo ->
                _isProgressed.value = false
                spouseInfo?.let {
                    _spouseName.value = spouseInfo.name
                }
            }, {reason ->
                Log.e("LoginError", "error: $reason" )
                toastProvider.makeToast("연동에 실패했습니다. 다시 시도해주세요")
                _isProgressed.value = false
            })
        } ?: run {
            toastProvider.makeToast("배우자 코드를 입력해주세요")
            _isProgressed.value = false
        }
    }
}