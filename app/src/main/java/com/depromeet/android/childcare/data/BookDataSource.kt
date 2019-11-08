package com.depromeet.android.childcare.data

import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.model.Summary
import com.depromeet.android.childcare.model.User

interface BookDataSource {

    fun connectSpouse(
        myCode: String,
        success: () -> Unit,
        failed: (String?) -> Unit
    )

    fun getMyInfo(
        success: (User, User?) -> Unit,
        failed: (String?) -> Unit
    )


    fun getAllRecords(
        success: (List<Record>) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun getRecordsByMonth(
        month: Int,
        success: (List<Record>) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun getRecordsByMonthAndCategory(
        month: Int,
        category: String,
        success: (List<Record>) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun getSummaries(
        success: (List<Summary>) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun getCategories(
        success: (List<String>) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun getExpenditureStatistics(
        success: (List<String>, List<Float>, Float) -> Unit,
        failed: (String?) -> Unit
    )
}