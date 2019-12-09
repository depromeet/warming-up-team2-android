package com.depromeet.android.childcare.model.response

import com.depromeet.android.childcare.model.User

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
        val connectionCode: String,
        val spouseName: String?
    )

    enum class Status {
        SOLO, COUPLE
    }

    fun meToUser() = User(
        data.id,
        data.profileImageUrl,
        data.name,
        data.connectionCode
    )

    fun spouseToUser() = data.spouseName?.let {
        User(
            -1,
            null,
            it,
            ""
        )
    }
}