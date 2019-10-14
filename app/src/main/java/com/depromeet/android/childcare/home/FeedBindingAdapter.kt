package com.depromeet.android.childcare.home

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.android.childcare.model.Record

@BindingAdapter("bind_feeds", "feed_navigator")
fun setFeedItems(view : RecyclerView, items : List<Record>, feedNavigator: FeedNavigator) {
    view.adapter as? FeedRecyclerViewAdapter ?: FeedRecyclerViewAdapter(feedNavigator).apply {
        view.adapter = this
    }.apply {
        setItems(items)
    }
}