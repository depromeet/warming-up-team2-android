package com.depromeet.android.childcare.home

import android.view.LayoutInflater
import android.view.ViewGroup
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
    private var feed: Record? = null

    fun bind(feed: Record) {
        binding.run {
            this@FeedViewHolder.feed = feed
            this.feed = feed
        }
    }
}