package com.depromeet.android.childcare.bookdetail

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.android.childcare.model.Record

@BindingAdapter("bind_book_details")
fun RecyclerView.setBookDetailsItems(items : List<Record>) {
    (adapter as BookDetailListAdapter).setWrapperItems(items)
}