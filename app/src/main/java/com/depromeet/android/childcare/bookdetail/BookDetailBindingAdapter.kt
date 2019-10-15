package com.depromeet.android.childcare.bookdetail

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.android.childcare.model.Record

@BindingAdapter("bind_book_details", "book_detail_navigator")
fun RecyclerView.setFeedItems(items : List<Record>, navigator: BookDetailNavigator) {
    adapter as? BookDetailListAdapter ?: BookDetailListAdapter(navigator).apply {
        adapter = this
    }.apply {
        setItems(items)
    }
}