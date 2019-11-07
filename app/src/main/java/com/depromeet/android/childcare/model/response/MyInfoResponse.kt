package com.depromeet.android.childcare.model.response

data class MyInfoResponse(
    val `data`: Data,
    val message: String,
    val totalCount: Int
) {
    data class Data(
        val id: Int,
        val name: String,
        val profileImageUrl: String,
        val status: Status,
        val connectionCode: String
    )

    enum class Status {
        SOLO, COUPLE
    }
}