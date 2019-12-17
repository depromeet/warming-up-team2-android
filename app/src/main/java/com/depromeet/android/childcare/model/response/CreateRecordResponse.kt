package com.depromeet.android.childcare.model.response


import com.depromeet.android.childcare.model.PaymentType
import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.model.RecordType
import com.depromeet.android.childcare.model.User
import com.google.gson.annotations.SerializedName

data class CreateRecordResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("totalCount")
    val totalCount: Int
) {
    data class Data(
        @SerializedName("amountOfMoney")
        val amountOfMoney: Int,
        @SerializedName("category")
        val category: String,
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("expendedAt")
        val expendedAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("imageUrl")
        val imageUrl: String,
        @SerializedName("member")
        val member: Member,
        @SerializedName("paymentMethod")
        val paymentMethod: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("updatedAt")
        val updatedAt: String
    )

    data class Member(
        @SerializedName("connectionCode")
        val connectionCode: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("profileImageUrl")
        val profileImageUrl: String,
        @SerializedName("spouseName")
        val spouseName: String,
        @SerializedName("status")
        val status: String
    )

    fun toRecord() = data.let {
        Record(
            it.id,
            User(it.member.id, it.member.profileImageUrl, it.member.name, it.member.connectionCode),
            RecordType.PAYMENT,
            it.expendedAt,
            it.title,
            it.amountOfMoney,
            it.category,
            PaymentType.valueOf(it.paymentMethod),
            it.imageUrl,
            it.description
        )
    }
}