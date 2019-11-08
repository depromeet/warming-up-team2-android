package com.depromeet.android.childcare.model.response

import com.depromeet.android.childcare.model.PaymentType

data class GetExpendituresResponse(
    val `data`: List<Data>,
    val message: String,
    val totalCount: Int
) {

    data class Data(
        val amountOfMoney: Int,
        val createdAt: String,
        val expendedAt: String,
        val description: String,
        val id: Int,
        val imageUrl: String,
        val member: Member,
        val paymentMethod: PaymentType,
        val category: String,
        val title: String,
        val updatedAt: String
    )

    data class Member(
        val connectionCode: String,
        val id: Int,
        val name: String,
        val profileImageUrl: String,
        val status: String
    )
}