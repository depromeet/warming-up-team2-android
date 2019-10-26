package com.depromeet.android.childcare.util

import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.android.childcare.R
import com.studyfirstproject.base.BaseRecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("bind_item")
fun bindItems(rv: RecyclerView, data: List<Any>) {
    (rv.adapter as BaseRecyclerView<*, Any>).run {
        setItems(data)
    }
}

@BindingAdapter("change_color_by_focus")
fun changeItemColorByPosition(cardview: CardView, isFocused: Boolean) {
    cardview.setCardBackgroundColor(
        if (isFocused) {
            R.color.colorPrimary
        } else {
            R.color.colorGray
        }
    )
}
