package com.depromeet.android.childcare.model.response


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
}