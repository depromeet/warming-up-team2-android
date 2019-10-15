package com.depromeet.android.childcare.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

interface ToastProvider {
    fun makeToast(charSequence: CharSequence)
    fun makeToast(@StringRes resId: Int)
}

class ToastProviderImpl(private val context: Context) : ToastProvider {
    override fun makeToast(charSequence: CharSequence)  = Toast.makeText(context, charSequence, Toast.LENGTH_SHORT).show()

    override fun makeToast(@StringRes resId: Int) = Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()

}