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

    fun toStatisticsData(): Triple<List<String>, List<Float>, Pair<String, Float>> {
        val sortedCategories = data.categoryMap
            .toList()
            .sortedBy { (_, value) -> value }
            .toMap()

        val categories = mutableListOf<String>()
        val consumptions = mutableListOf<Float>()
        var mostCategoryData = Pair("", 0f)
        sortedCategories.map {
            categories.add(it.key)
            consumptions.add(it.value)
            mostCategoryData = it.toPair()
        }

        // Todo 데이터가 5개로 잘 오면 삭제하도록 하자
        if (categories.size < 5) {
            for (i in 0 until 5 - categories.size) {
                categories.add(0, "미분류 $i")
                consumptions.add(0, 0f)
            }
        }

        return Triple(categories, consumptions, mostCategoryData)
    }
}