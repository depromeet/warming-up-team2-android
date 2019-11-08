package com.depromeet.android.childcare.data

import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.model.Summary
import com.depromeet.android.childcare.model.User
import com.depromeet.android.childcare.model.request.CreateRecordRequest
import com.depromeet.android.childcare.model.response.CreateRecordResponse
import com.kakao.network.storage.ImageUploadRequest
import java.io.File

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

    fun createNewRecord(
        data: CreateRecordRequest,
        success: (CreateRecordResponse) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun editRecord(
        id: Int,
        data: CreateRecordRequest,
        success: (CreateRecordResponse) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun uploadImage(
        id: Int,
        file: File,
        data: ImageUploadRequest,
        success: (CreateRecordResponse) -> Unit,
        failed: (String, String?) -> Unit
    )
}