package com.depromeet.android.childcare.feed.feeddetail

import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ItemFeedBinding
import com.depromeet.android.childcare.feed.FeedNavigator
import com.depromeet.android.childcare.model.Record
import com.studyfirstproject.base.BaseViewHolder

class FeedDetailViewHolder(
    parent: ViewGroup?,
    navigator: FeedNavigator
): BaseViewHolder<ItemFeedBinding, Record>(R.layout.item_feed, parent, BR.feed) {

    init {
        binding.navigator = navigator
    }
}