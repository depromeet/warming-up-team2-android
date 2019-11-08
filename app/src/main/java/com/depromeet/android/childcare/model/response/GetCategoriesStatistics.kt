package com.depromeet.android.childcare.model.response

import com.google.gson.annotations.SerializedName

data class GetCategoriesStatistics(
    val `data`: Data,
    val message: String,
    val totalCount: Int
) {
    data class Data(
        @SerializedName("categoryMap")
        val categoryMap: Map<String, Float>
    )
}