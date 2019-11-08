package com.depromeet.android.childcare.model.response


import com.google.gson.annotations.SerializedName

// TODO: Record 클래스로 통일하도록 리팩토링
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