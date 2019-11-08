package com.depromeet.android.childcare.model.response

import com.google.gson.annotations.SerializedName

data class GetExpenditureStatistics(
    val `data`: Data,
    val message: String,
    val totalCount: Int
) {
    data class Data(
        @SerializedName("monthlyTotalExpenditures")
        val monthlyTotalExpenditures: Map<String, Float>
    )
}