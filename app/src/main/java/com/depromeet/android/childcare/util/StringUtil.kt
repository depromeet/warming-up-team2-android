package com.depromeet.android.childcare.util

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import java.text.DecimalFormat

// 숫자에 3자리 단위로 comma 삽입
fun insertComma(num: Int): String = DecimalFormat("#,###").format(num)
fun insertComma(num: Float): String = DecimalFormat("#,###").format(num)

fun getBoldText(text: String, name: String): SpannableStringBuilder {
    val str = SpannableStringBuilder(text)
    val textPosition = text.indexOf(name)
    str.setSpan(android.text.style.StyleSpan(Typeface.BOLD),
        textPosition, textPosition + name.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return str
}