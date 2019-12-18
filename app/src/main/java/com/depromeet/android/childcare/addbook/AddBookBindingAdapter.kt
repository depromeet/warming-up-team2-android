package com.depromeet.android.childcare.addbook

import android.widget.Button
import androidx.databinding.BindingAdapter
import com.depromeet.android.childcare.R

@BindingAdapter("bind_addbook_category_background")
fun Button.setAddBookCategory(category: String) {
    if (category == text) {
        setBackgroundResource(R.drawable.btn_bg_round_blue)
    } else {
        setBackgroundResource(R.drawable.btn_bg_round_gray)
    }
}