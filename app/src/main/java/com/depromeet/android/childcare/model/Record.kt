package com.depromeet.android.childcare.model

data class Record(
    val id: Int,
    val writer: User,
    var type: String,
    var date: String,
    var title: String,
    var amount: Int,
    var category: String,
    var paymentMethod: PaymentType,
    var imgUrl: String?,
    var content: String?
)