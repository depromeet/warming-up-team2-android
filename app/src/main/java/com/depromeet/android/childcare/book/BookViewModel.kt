package com.depromeet.android.childcare.book

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.data.BookDataSource
import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.model.Summary
import com.depromeet.android.childcare.util.ResourcesProvider

class BookViewModel(
    private val resourcesProvider: ResourcesProvider,
    private val repository: BookDataSource
) : ViewModel() {
    private val _records = MutableLiveData<List<Record>>()
    private val _summaries = MutableLiveData<List<Summary>>()
    private val _errorMsg = MutableLiveData<String>()

    val records: LiveData<List<Record>>
        get() = _records

    val summaries: LiveData<List<Summary>>
        get() = _summaries

    val errorMsg: LiveData<String>
        get() = _errorMsg

    init {
        _records.value = mutableListOf()
        _summaries.value = mutableListOf()

        getAllRecords()
        getSummaries()
    }

    private fun getAllRecords() {
        repository.getAllRecords(
            success = {
                _records.value = it
            }, failed = { msg, reason ->
                onDataNotAvailable(msg, reason)
            }
        )
    }

    private fun getRecordsByCategory(category: String) {
        repository.getRecordsByCategory(category,
            success = {
                _records.value = it
            }, failed = { msg, reason ->
                onDataNotAvailable(msg, reason)
            }
        )
    }

    private fun getSummaries() {
        repository.getSummaries(
            success = {
                _summaries.value = it
            }, failed = { msg, reason ->
                onDataNotAvailable(msg, reason)
            }
        )
    }

    private fun onDataNotAvailable(msg: String, reason: String?) {
        Log.e(msg, reason ?: "No error msg")
        _errorMsg.value = resourcesProvider.getString(R.string.msg_network_err)
    }
}