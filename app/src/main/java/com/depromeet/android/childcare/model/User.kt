package com.depromeet.android.childcare.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Int,
    var profileUrl: String?,
    var name: String,
    var connectCode: String
): Parcelable