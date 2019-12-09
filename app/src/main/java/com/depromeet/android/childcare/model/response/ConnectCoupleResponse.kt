package com.depromeet.android.childcare.model.response

import com.depromeet.android.childcare.model.User

data class ConnectCoupleResponse(
    val `data`: Data,
    val message: String,
    val totalCount: Int
) {
    data class Data(
        val id: Int,
        val me: Me,
        val spouse: Spouse
    )

    data class Me(
        val id: Int,
        val name: String,
        val profileImageUrl: String,
        val status: String,
        val connectionCode: String
    )

    data class Spouse(
        val id: Int,
        val name: String,
        val profileImageUrl: String,
        val status: String,
        val connectionCode: String
    )

    fun meToUser() = User(
        data.me.id,
        data.me.profileImageUrl,
        data.me.name,
        data.me.connectionCode
    )

    fun spouseToUser() = User(
        data.spouse.id,
        data.spouse.profileImageUrl,
        data.spouse.name,
        data.spouse.connectionCode
    )
}