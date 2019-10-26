package com.depromeet.android.childcare.feed

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.android.childcare.model.Record

@BindingAdapter("bind_feed_type")
fun RecyclerView.setBookDetailsItems(feedType: FeedType) {
    val feedLayoutManager = layoutManager as GridLayoutManager
    when(feedType) {
        FeedType.DETAIL -> {
            feedLayoutManager.spanCount = 1
        }
        FeedType.SUMMARY -> {
            feedLayoutManager.spanCount = 2
        }
    }
    (adapter as FeedRecyclerViewAdapter).run {
        this.feedType = feedType
        notifyDataSetChanged()
    }
}

@BindingAdapter("bind_feed_items")
fun RecyclerView.setFeedItems(items : List<Record>) {
    (adapter as FeedRecyclerViewAdapter).setItems(items)
}