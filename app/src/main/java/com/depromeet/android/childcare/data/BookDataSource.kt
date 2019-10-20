package com.depromeet.android.childcare.data

import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.model.Summary

interface BookDataSource {

    fun getAllRecords(
        success: (List<Record>) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun getRecordsByCategory(
        category: String,
        success: (List<Record>) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun getSummaries(
        success: (List<Summary>) -> Unit,
        failed: (String, String?) -> Unit
    )
}