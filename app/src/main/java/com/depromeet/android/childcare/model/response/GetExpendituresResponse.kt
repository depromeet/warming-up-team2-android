package com.depromeet.android.childcare.model.response

import com.depromeet.android.childcare.model.PaymentType
import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.model.RecordType
import com.depromeet.android.childcare.model.User

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

    fun toRecords() = data.map {
        Record(
            it.id,
            User(it.member.id, it.member.profileImageUrl, it.member.name, it.member.connectionCode),
            RecordType.PAYMENT,
            it.expendedAt,
            it.title,
            it.amountOfMoney,
            it.category,
            it.paymentMethod,
            it.imageUrl,
            it.description
        )
    }
}