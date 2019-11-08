package com.depromeet.android.childcare.feed.feedsummary

import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ItemFeedSummaryBinding
import com.depromeet.android.childcare.feed.FeedNavigator
import com.depromeet.android.childcare.model.Record
import com.studyfirstproject.base.BaseViewHolder

class FeedSummaryViewHolder(
    parent: ViewGroup?,
    navigator: FeedNavigator
) : BaseViewHolder<ItemFeedSummaryBinding, Record>(R.layout.item_feed_summary, parent, BR.feed) {
    init {
        binding.navigator = navigator
    }
}