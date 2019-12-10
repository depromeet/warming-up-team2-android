package com.depromeet.android.childcare.editbook

import android.widget.Button
import androidx.databinding.BindingAdapter
import com.depromeet.android.childcare.R

@BindingAdapter("bind_editbook_category_background")
fun Button.setEditBookCategory(category: String) {
    if (category == text) {
        setBackgroundResource(R.drawable.btn_bg_round_blue)
    } else {
        setBackgroundResource(R.drawable.btn_bg_round_gray)
    }
}