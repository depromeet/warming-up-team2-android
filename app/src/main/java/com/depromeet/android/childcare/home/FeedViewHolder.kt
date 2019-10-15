package com.depromeet.android.childcare.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ItemFeedBinding
import com.depromeet.android.childcare.model.Record

class FeedViewHolder(
    parent: ViewGroup?,
    private val navigator: FeedNavigator
): RecyclerView.ViewHolder(
    LayoutInflater.from(parent?.context).inflate(R.layout.item_feed, parent, false)
) {

    private val binding = ItemFeedBinding.bind(itemView).apply {
        this.navigator = this@FeedViewHolder.navigator
    }

    fun bind(feed: Record) {
        try {
            binding.run {
                setVariable(BR.feed, feed)
                executePendingBindings()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}