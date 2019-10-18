package com.depromeet.android.childcare.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.studyfirstproject.base.BaseRecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("bind_item")
fun bindItems(rv: RecyclerView, data: List<Any>) {
    (rv.adapter as BaseRecyclerView<*, Any>).run {
        setItems(data)
    }
}