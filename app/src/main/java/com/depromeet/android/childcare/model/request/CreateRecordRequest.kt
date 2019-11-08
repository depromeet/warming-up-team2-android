package com.depromeet.android.childcare.model.request


import com.google.gson.annotations.SerializedName

data class CreateRecordRequest(
    @SerializedName("amountOfMoney")
    val amountOfMoney: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("expendedAt")
    val expendedAt: String,
    @SerializedName("paymentMethod")
    val paymentMethod: String,
    @SerializedName("title")
    val title: String
)