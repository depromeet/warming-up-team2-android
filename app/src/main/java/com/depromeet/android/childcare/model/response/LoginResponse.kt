package com.depromeet.android.childcare.model.response

data class LoginResponse(
    val `data`: Data,
    val message: String,
    val totalCount: Int
) {
    data class Data(
        val accessToken: String
    )
}