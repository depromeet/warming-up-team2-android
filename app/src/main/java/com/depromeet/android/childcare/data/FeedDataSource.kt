package com.depromeet.android.childcare.data

import com.depromeet.android.childcare.model.Record

interface FeedDataSource {
    fun getAllFeeds(
        success: (MutableList<Record>) -> Unit,
        failed: () -> Unit
    )
}