package com.depromeet.android.childcare.book.adapter

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.depromeet.android.childcare.R

@BindingAdapter("category_state_color")
fun setBookItemColor(textView: TextView, content: String) {
    textView.setTextColor(
        if (content.isNotEmpty()) {
            R.color.colorPrimary
        } else {
            Color.BLACK
        }
    )
}

@BindingAdapter("view_state_color")
fun setBookItemColor(view: View, content: String) {
    view.setBackgroundColor(
        if (content.isNotEmpty()) {
            R.color.colorPrimary
        } else {
            Color.BLACK
        }
    )
}