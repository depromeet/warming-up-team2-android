package com.depromeet.android.childcare.data

import android.net.Uri
import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.model.Summary
import com.depromeet.android.childcare.model.User
import com.depromeet.android.childcare.model.request.CreateRecordRequest
import com.depromeet.android.childcare.model.response.CreateRecordResponse

interface BookDataSource {

    var editBookModel: Record?

    fun connectSpouse(
        myCode: String,
        success: (User?) -> Unit,
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

    fun createNewRecord(
        data: CreateRecordRequest,
        success: (CreateRecordResponse) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun editRecord(
        record: Record,
        success: () -> Unit,
        failed: (String, String?) -> Unit
    )

    fun uploadImage(
        id: Int,
        imageUri: Uri,
        success: () -> Unit,
        failed: (String, String?) -> Unit
    )

    fun getExpenditureStatistics(
        success: (List<String>, List<Float>, Float) -> Unit,
        failed: (String?) -> Unit
    )

    fun getCategoriesStatistics(
        success: (List<String>, List<Float>, String, Float) -> Unit,
        failed: (String?) -> Unit
    )
}