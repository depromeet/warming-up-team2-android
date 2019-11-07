package com.depromeet.android.childcare.model.response

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
        val status: String
    )

    data class Spouse(
        val id: Int,
        val name: String,
        val profileImageUrl: String,
        val status: String
    )
}