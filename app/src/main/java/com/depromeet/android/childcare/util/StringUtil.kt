package com.depromeet.android.childcare.util

import android.content.Context
import android.graphics.Typeface
import android.net.Uri
import android.provider.MediaStore
import android.text.Spannable
import android.text.SpannableStringBuilder
import java.io.File
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

// 숫자에 3자리 단위로 comma 삽입
fun insertComma(num: Int): String = DecimalFormat("#,###").format(num)

fun insertComma(num: Float): String = DecimalFormat("#,###").format(num)

fun getBoldText(text: String, name: String): SpannableStringBuilder {
    val str = SpannableStringBuilder(text)
    val textPosition = text.indexOf(name)
    str.setSpan(
        android.text.style.StyleSpan(Typeface.BOLD),
        textPosition, textPosition + name.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return str
}

fun getDateString(): String = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(Date())

fun getDateString(year: Int, month: Int, day: Int) = "$year-$month-$day"


fun getGalleryImageFullPath(uri: Uri?, context: Context): Uri? {
    uri?.let {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(
            uri,
            filePathColumn,
            null,
            null,
            null
        )
        cursor!!.moveToFirst()
        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        val picturePath = cursor.getString(columnIndex)
        cursor.close()
        return Uri.fromFile(File(picturePath))
    }
    return null
}