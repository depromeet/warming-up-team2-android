package com.depromeet.android.childcare.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Record(
    val id: Int,
    val writer: User,
    var type: RecordType,
    var date: String,
    var title: String,
    var amount: Int,
    var category: String,
    var paymentMethod: PaymentType,
    var imgUrl: String?,
    var content: String?
): Parcelable