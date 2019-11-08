package com.depromeet.android.childcare.model.response


import com.google.gson.annotations.SerializedName

// TODO: User 클래스로 통일하도록 리팩토링
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