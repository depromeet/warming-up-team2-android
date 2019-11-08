package com.depromeet.android.childcare.model.response


import com.google.gson.annotations.SerializedName

data class CreateRecordResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("totalCount")
    val totalCount: Int
)